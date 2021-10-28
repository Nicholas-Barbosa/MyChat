package com.mychat.domain;

import java.time.LocalDateTime;
import java.util.Set;

public class ChatRoom implements Comparable<ChatRoom> {

	private String id;
	private LocalDateTime createdAt;
	private String name;
	private Set<ChatUser> users;
	private ChatUser createdBy;

	public ChatRoom(String id, String name, Set<ChatUser> users, ChatUser createdBy) {
		super();
		this.id = id;
		this.createdAt = LocalDateTime.now();
		this.name = name;
		this.users = users;
		users.stream().forEach(this::addUser);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public String getName() {
		return name;
	}

	public Set<ChatUser> getUsers() {
		return users;
	}

	public ChatUser getCreatedBy() {
		return createdBy;
	}

	public final boolean addUser(ChatUser user) {
		return this.users.stream().filter(u -> u.equals(user)).findAny().get().addChatRoom(this)
				&& this.users.add(user);
	}

	public final boolean removeUser(ChatUser user) {
		return this.users.stream().filter(u -> u.equals(user)).findAny().get().removeChatRoom(this)
				&& this.users.remove(user);
	}

	@Override
	public int compareTo(ChatRoom o) {
		// TODO Auto-generated method stub
		return createdAt.compareTo(o.createdAt);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ChatRoom other = (ChatRoom) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
