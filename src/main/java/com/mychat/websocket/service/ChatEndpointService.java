package com.mychat.websocket.service;

import com.mychat.domain.ChatUser;

public interface ChatEndpointService {

	void broadcastToRoom(String message, ChatUser fromUser);
	
	void flushIncomingOfflineMessages(ChatUser toUser);
}
