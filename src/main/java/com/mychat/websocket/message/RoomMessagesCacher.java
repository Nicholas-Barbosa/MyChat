package com.mychat.websocket.message;

import java.util.List;

import com.mychat.domain.ChatRoom;
import com.mychat.websocket.pojo.RoomChatMessage;

public interface RoomMessagesCacher {

	void addMessage(RoomChatMessage message);

	List<RoomChatMessage> getAll(ChatRoom room);

	void clean(ChatRoom room);

	void flush();
}
