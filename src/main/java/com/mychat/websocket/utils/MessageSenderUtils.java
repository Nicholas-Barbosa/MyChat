package com.mychat.websocket.utils;

import java.io.IOException;

import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;

public class MessageSenderUtils {

	public static void sendMessage(String message, Session session) {
		try {
			Basic basicRemote = session.getBasicRemote();
			basicRemote.sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sendAsyncMessage(String message, Session session) {
		Async asyncRemote = session.getAsyncRemote();
		asyncRemote.sendText(message);
	}
}
