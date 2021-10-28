package com.mychat.websocket;

import java.io.IOException;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.mychat.domain.ChatUser;
import com.mychat.service.ChatUserService;
import com.mychat.websocket.service.ChatEndpointService;

@ServerEndpoint("/chat/{username}")
public class ChatEndpoint {

	private ChatUser currentUser;

	@Inject
	private ChatUserService chatUserService;

	@Inject
	private ChatEndpointService endpointService;

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		chatUserService.findByUsername(username).ifPresentOrElse(user -> {
			this.currentUser = user;
			currentUser.setCurrentSession(session);
			endpointService.flushIncomingOfflineMessages(this.currentUser);
		}, () -> {
			this.closeSession(session, new CloseReason(CloseCodes.NO_STATUS_CODE,
					"Usuário \"" + username + "\" não foi encontrado. Cadastre-o em POST \"http://api/chatUser\""));
		});
	}

	@OnMessage
	public void myOnMessage(String txt) {
		endpointService.broadcastToRoom(txt, currentUser);

	}

	@OnClose
	public void myOnClose(CloseReason reason, Session session) throws IOException {

	}

	private void closeSession(Session session, CloseReason reason) {
		try {
			session.close(reason);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
