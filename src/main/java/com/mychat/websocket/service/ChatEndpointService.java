package com.mychat.websocket.service;

import javax.websocket.Session;

import com.mychat.domain.User;

public interface ChatEndpointService {

	void addActiveSession(String email,Session session);
	void removeActiveSession(Session session);
}
