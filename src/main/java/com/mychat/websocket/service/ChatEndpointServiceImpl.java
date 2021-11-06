package com.mychat.websocket.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.Session;

import com.mychat.domain.User;
import com.mychat.service.UserService;
import com.mychat.websocket.utils.CloseReasonBuilder;
import com.mychat.websocket.utils.SessionUtils;

@ApplicationScoped
public class ChatEndpointServiceImpl implements ChatEndpointService {

	private final Map<User, Session> sessions = new ConcurrentHashMap<>();

	@Inject
	private UserService userService;

	@Override
	public void addActiveSession(String email, Session session) {
		userService.findByEmail(email).ifPresentOrElse(user -> {
			if (sessions.containsKey(user)) {
				SessionUtils.closeSession(session, CloseReasonBuilder.code(CloseCodes.VIOLATED_POLICY)
						.withReason("There is a session with this user on another client.").build());
				return;
			}
			sessions.put(user, session);
		}, () -> {
			SessionUtils.closeSession(session,
					CloseReasonBuilder.code(CloseCodes.UNEXPECTED_CONDITION).withReason("User not found").build());
		});

	}

	@Override
	public void removeActiveSession(Session session) {
		if (session != null && sessions.containsValue(session)) {
			sessions.entrySet().removeIf(entry -> entry.getValue().equals(session));
		}
	}

}
