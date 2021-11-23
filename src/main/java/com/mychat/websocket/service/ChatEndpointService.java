package com.mychat.websocket.service;

import javax.websocket.Session;

import com.mychat.websocket.message.ChatMessage;

public interface ChatEndpointService {

	void addActiveSession(String email, Session session);

	void removeActiveSession(Session session);

	void onmessage(Session session, ChatMessage message);
	
	void broadcast(Session session, ChatMessage message);
}
