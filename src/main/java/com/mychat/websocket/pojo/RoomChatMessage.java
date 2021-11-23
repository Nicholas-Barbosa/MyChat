package com.mychat.websocket.pojo;

import com.mychat.domain.ChatRoom;
import com.mychat.websocket.message.ChatMessage;

public class RoomChatMessage {

	private ChatRoom room;
	private ChatMessage message;

	public static RoomChatMessage of(ChatRoom room, ChatMessage message) {
		return new RoomChatMessage(room, message);
	}

	public RoomChatMessage(ChatRoom room, ChatMessage message) {
		super();
		this.room = room;
		this.message = message;
	}

	public ChatRoom getRoom() {
		return room;
	}

	public ChatMessage getMessage() {
		return message;
	}
}
