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
import com.mychat.websocket.message.ChatMessage;
import com.mychat.websocket.message.ChatMessageDecoder;
import com.mychat.websocket.message.ChatMessageEncoder;
import com.mychat.websocket.service.ChatEndpointService;
import com.mychat.websocket.utils.CloseReasonBuilder;
import com.mychat.websocket.utils.SessionUtils;

@ServerEndpoint(value = "/chat/{username}", decoders = ChatMessageDecoder.class, encoders = ChatMessageEncoder.class)
public class ChatEndpoint {

	private ChatUser currentUser;

	@Inject
	private ChatUserService chatUserService;

	@Inject
	private ChatEndpointService endpointService;

	@OnOpen
	public void onOpen(Session session, @PathParam("username") String username) throws IOException {
		chatUserService.findByUsername(username).ifPresentOrElse(user -> {
			if (user.getCurrentSession() == null) {
				this.currentUser = user;
				currentUser.setCurrentSession(session);
				return;
			}
			SessionUtils.closeSession(session, CloseReasonBuilder.code(CloseCodes.VIOLATED_POLICY)
					.withReason("Há uma sessão ativa para este usuário em outro client.").build());
		}, () -> {
			SessionUtils.closeSession(session, new CloseReason(CloseCodes.NO_STATUS_CODE,
					"Usuário \"" + username + "\" não foi encontrado. Cadastre-o em POST \"http://api/chatUser\""));
		});
	}

	@OnMessage
	public void myOnMessage(ChatMessage message) {
		endpointService.onMessage(message, currentUser);
	}

	@OnClose
	public void myOnClose(CloseReason reason) throws IOException {
		System.out.println("onClose! ");
		System.out.println(reason);
		if (currentUser != null)
			this.currentUser.setCurrentSession(null);

	}

}
