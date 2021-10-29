package com.mychat.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.Session;

public class ChatUser implements Comparable<ChatUser> {

	private String username;
	private Set<ChatRoom> rooms;
	private LocalDateTime createdAt;
	private Session currentSession;

	public ChatUser(String username, Set<ChatRoom> rooms) {
		super();
		this.username = username;
		this.rooms = rooms;
		this.createdAt = LocalDateTime.now();
	}

	public String getUsername() {
		return username;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Set<ChatRoom> getRooms() {
		return rooms;
	}
	public boolean addChatRoom(ChatRoom chat) {
		this.instantiateRooms();
		return rooms.add(chat);
	}

	public boolean removeChatRoom(ChatRoom chat) {
		this.instantiateRooms();
		return rooms.add(chat);
	}

	private void instantiateRooms() {
		if (rooms == null)
			rooms = new HashSet<>();
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	@Override
	public int compareTo(ChatUser o) {
		// TODO Auto-generated method stub
		return username.compareTo(o.username);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		ChatUser other = (ChatUser) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
