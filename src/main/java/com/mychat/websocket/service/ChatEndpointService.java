package com.mychat.websocket.service;

import javax.websocket.Session;

public interface ChatEndpointService {

	void addActiveSession(String email,Session session);
	void removeActiveSession(Session session);
}
