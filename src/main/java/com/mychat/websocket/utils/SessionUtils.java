package com.mychat.websocket.utils;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

import com.mychat.domain.ChatUser;
import com.mychat.websocket.message.ChatMessage;

public class SessionUtils {

	public static void sendMessage(ChatMessage message, Session session) {
		Basic basicRemote = session.getBasicRemote();
		try {
			basicRemote.sendObject(message);
		} catch (IOException | EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sendAsyncMessage(ChatMessage message, Session session) {
		Async asyncRemote = session.getAsyncRemote();
		asyncRemote.sendObject(message);
	}
	
	public static void closeSession(ChatUser user, CloseReason reason) {
		closeSession(user.getCurrentSession(), reason);
	}
	
	public static void closeSession(Session session, CloseReason reason) {
		try {
			session.close(reason);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
