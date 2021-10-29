package com.mychat.websocket.service;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mychat.domain.ChatRoom;
import com.mychat.domain.ChatUser;
import com.mychat.service.ChatRoomService;
import com.mychat.websocket.message.ChatMessage;
import com.mychat.websocket.message.MessageType;
import com.mychat.websocket.utils.SessionUtils;

@ApplicationScoped
public class ChatEndpointServiceImpl implements ChatEndpointService {

	@Inject
	private ChatRoomService chatRoomService;

	private Set<ChatUser> broadcastWelcomeMessageIfUserIsNotPresentInRoom(ChatRoom room, ChatUser user) {
		Set<ChatUser> users = room.getUsers();

		if (users.add(user)) {
			ChatMessage messageToUsersInRoom = new ChatMessage("Server", user.getUsername() + " entrou na sala!",
					room.getId(), MessageType.NORMAL);
			users.stream().filter(chatUser -> chatUser.getCurrentSession() != null).forEach(chatUser -> {
				SessionUtils.sendAsyncMessage(messageToUsersInRoom, chatUser.getCurrentSession());
				room.addUser(user);
				room.addMessage(messageToUsersInRoom);
			});
		}

		return users;
	}

	@Override
	public void broadcastToRoom(ChatMessage message, ChatUser fromUser) {
		String roomId = message.getRoomId();
		message.setAuthor(fromUser.getUsername());
		chatRoomService.findById(roomId).ifPresentOrElse(room -> {
			this.broadcastWelcomeMessageIfUserIsNotPresentInRoom(room, fromUser).parallelStream()
					.filter(user -> !user.equals(fromUser) && user.getCurrentSession() != null).forEach(user -> {
						SessionUtils.sendMessage(message, user.getCurrentSession());
						room.addMessage(message);
					});
		}, () -> {

			SessionUtils.sendMessage(
					new ChatMessage("Room ID " + roomId + " não existe!", message.getRoomId(), MessageType.NORMAL),
					fromUser.getCurrentSession());
		});

	}

	@Override
	public void flushAllChatMessages(ChatUser toUser, String roomId) {
		toUser.getRooms().stream().filter(room -> room.getId().equals(roomId)).findAny().ifPresent(room -> {
			room.getMessages().parallelStream().forEach(message -> {
				SessionUtils.sendMessage(message, toUser.getCurrentSession());
			});
		});
	}

	@Override
	public void onMessage(ChatMessage message, ChatUser user) {
		// TODO Auto-generated method stub
		switch (message.getType()) {
		case NORMAL:
			broadcastToRoom(message, user);
			break;
		case RECEIVE_OLD_MESSAGES:
			this.flushAllChatMessages(user, message.getRoomId());

			break;
		case CLOSING:
			break;
		}
	}

	@Override
	public void broadcastMembershipMessage(ChatMessage message, ChatUser newUser) {
		// TODO Auto-generated method stub
		chatRoomService.findById(message.getRoomId()).ifPresent(room -> {
			room.getUsers().parallelStream().forEach(user -> {
				SessionUtils.sendMessage(message, user.getCurrentSession());
			});
			room.addUser(newUser);
		});

	}

}
