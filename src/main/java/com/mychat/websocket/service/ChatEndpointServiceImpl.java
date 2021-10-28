package com.mychat.websocket.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mychat.domain.ChatUser;
import com.mychat.service.ChatRoomService;
import com.mychat.websocket.utils.MessageSenderUtils;

@ApplicationScoped
public class ChatEndpointServiceImpl implements ChatEndpointService {

	private final Map<ChatUser, List<String>> incomingOfflineMessages = new ConcurrentHashMap<>();

	@Inject
	private ChatRoomService chatRoomService;

	@Override
	public void broadcastToRoom(String message, ChatUser fromUser) {
		int indexOf = message.indexOf(";");
		String roomId = message.substring(5, indexOf);
		chatRoomService.findById(roomId).ifPresentOrElse(room -> {
			room.getUsers().parallelStream().filter(user -> !user.equals(fromUser)).forEach(user -> {
				String formatedMessage = new StringBuilder(fromUser.getUsername() + ":")
						.append(message.substring(message.indexOf(";") + 1)).toString();

				if (user.getCurrentSession() == null) {
					this.saveIncomingMessage(user, formatedMessage);
					System.out.println("salvando mensagem pois " + user.getUsername() + " esta offline!");
					return;
				}
				MessageSenderUtils.sendMessage(formatedMessage, user.getCurrentSession());
			});
		}, () -> {

			MessageSenderUtils.sendMessage("Grupo ID " + roomId + " não existe!", fromUser.getCurrentSession());
		});

	}

	@Override
	public void flushIncomingOfflineMessages(ChatUser toUser) {
		if (incomingOfflineMessages.containsKey(toUser)) {
			incomingOfflineMessages.get(toUser).parallelStream().forEach(message -> {
				MessageSenderUtils.sendMessage(message, toUser.getCurrentSession());
			});
			incomingOfflineMessages.remove(toUser);
		}

	}

	private void saveIncomingMessage(ChatUser user, String message) {
		if (incomingOfflineMessages.containsKey(user)) {
			List<String> messages = incomingOfflineMessages.get(user);
			messages.add(message);
			return;
		}
		incomingOfflineMessages.put(user, Arrays.asList(message));
	}

}
