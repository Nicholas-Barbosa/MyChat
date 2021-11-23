package com.mychat.websocket;

import java.io.IOException;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.mychat.websocket.message.ChatMessage;
import com.mychat.websocket.message.ChatMessageDecoder;
import com.mychat.websocket.message.ChatMessageEncoder;
import com.mychat.websocket.service.ChatEndpointService;

@ServerEndpoint(value = "/chat/{email}", decoders = ChatMessageDecoder.class, encoders = ChatMessageEncoder.class)
public class ChatEndpoint {

	@Inject
	private ChatEndpointService service;

	private Session currentSession;

	@OnOpen
	public void onOpen(Session session, @PathParam("email") String email) throws IOException {
		service.addActiveSession(email, session);
		this.currentSession = session;
	}

	@OnMessage
	public void myOnMessage(ChatMessage message) {
//		message.setFrom(currentSession.getUser());
		service.onmessage(currentSession, message);

	}

	@OnClose
	public void myOnClose(CloseReason reason) throws IOException {
		service.removeActiveSession(currentSession);
	}

}
