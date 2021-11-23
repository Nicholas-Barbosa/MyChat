package com.mychat.websocket.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.Session;

import com.mychat.domain.ChatRoomUser;
import com.mychat.domain.User;
import com.mychat.service.ChatRoomService;
import com.mychat.service.UserService;
import com.mychat.websocket.message.ChatMessage;
import com.mychat.websocket.message.RoomMessagesCacher;
import com.mychat.websocket.pojo.RoomChatMessage;
import com.mychat.websocket.utils.CloseReasonBuilder;
import com.mychat.websocket.utils.SessionUtils;

@ApplicationScoped
public class ChatEndpointServiceImpl implements ChatEndpointService {

	private final Map<User, Session> sessions = new ConcurrentHashMap<>();

	@Inject
	private UserService userService;

	@Inject
	private RoomMessagesCacher roomCacher;

	@Inject
	private ChatRoomService roomService;

	@Override
	public void addActiveSession(String email, Session session) {
		Optional<User> opDomainUser = userService.findByEmail(email);
		opDomainUser.ifPresentOrElse(domainUser -> {
			if (sessions.containsKey(domainUser)) {
				SessionUtils.closeSession(session, CloseReasonBuilder.code(CloseCodes.VIOLATED_POLICY)
						.withReason("There is a session with this user on another client.").build());
				return;
			}
			sessions.put(domainUser, session);
			System.out.println("Sessões ativas " + sessions.size());
		}, () -> {
			SessionUtils.closeSession(session,
					CloseReasonBuilder.code(CloseCodes.UNEXPECTED_CONDITION).withReason("User not found").build());
		});
	}

	@Override
	public void removeActiveSession(Session session) {
		if (session != null) {
			sessions.entrySet().removeIf(entry -> entry.getValue().equals(session));
//			this.batchSendMessages(session);
			System.out.println("Sessões ativa " + sessions.size());
		}
	}

	@Override
	public void onmessage(Session session, ChatMessage message) {
		this.broadcast(session, message);

	}

	@Override
	public void broadcast(Session session, ChatMessage message) {
		final String roomName = message.getRoomId();
		roomService.findByName(roomName).ifPresentOrElse(room -> {
			room.getUsers().parallelStream().map(ChatRoomUser::getUser).filter(sessions::containsKey).map(sessions::get)
					.filter(s -> !s.equals(session)).forEach(s -> {
						SessionUtils.sendMessage(message, s);
						roomCacher.addMessage(RoomChatMessage.of(room, message));
					});
		}, () -> {
		});
//		sessions.entrySet().stream().filter(e -> e.getKey().getUser().getEmail().equals(to)).map(Entry::getKey)
//				.findAny().ifPresent(userSession -> {
//					SessionUtils.sendMessage(message, userSession.getSession());
//				});
	}
}
