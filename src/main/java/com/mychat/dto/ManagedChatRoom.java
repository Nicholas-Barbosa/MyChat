package com.mychat.dto;

import java.util.Set;

public class ManagedChatRoom {

	private String id;
	private Set<ManagedChatUserDTO> users;

	public ManagedChatRoom(String id, Set<ManagedChatUserDTO> users) {
		super();
		this.id = id;
		this.users = users;
	}

	public String getId() {
		return id;
	}

	public Set<ManagedChatUserDTO> getUsers() {
		return users;
	}
}
