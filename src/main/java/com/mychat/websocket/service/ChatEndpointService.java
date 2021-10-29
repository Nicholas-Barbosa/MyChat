package com.mychat.websocket.service;

import com.mychat.domain.ChatUser;
import com.mychat.websocket.message.ChatMessage;

public interface ChatEndpointService {

	void broadcastToRoom(ChatMessage message, ChatUser fromUser);
	
	void flushAllChatMessages(ChatUser toUser,String roomId);
	
	void onMessage(ChatMessage message,ChatUser user);
	
	void broadcastMembershipMessage(ChatMessage message,ChatUser newUser);
}
