package com.mychat.websocket.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.Session;

import com.mychat.domain.User;
import com.mychat.websocket.message.ChatMessage;

public class UserSession {

	private User user;
	private Session session;
	private List<ChatMessage> messages;

	public static UserSession of(User user, Session session) {
		return new UserSession(user, session);
	}

	public UserSession() {
		// TODO Auto-generated constructor stub
		this.messages = new ArrayList<ChatMessage>();
	}

	public UserSession(User user, Session session) {
		super();
		this.user = user;
		this.session = session;
		this.messages = new ArrayList<ChatMessage>();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void addMessage(ChatMessage message) {
		this.messages.add(message);
	}

	public List<ChatMessage> getMessages() {
		return new ArrayList<>(messages);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserSession other = (UserSession) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
