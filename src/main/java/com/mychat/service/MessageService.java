package com.mychat.service;

import java.util.List;

import com.mychat.domain.Message;
import com.mychat.websocket.message.ChatMessage;
import com.mychat.websocket.pojo.RoomChatMessage;

public interface MessageService {

	void persist(List<Message> messages);

	void persistRoomChatMessages(List<RoomChatMessage> messages);
}
