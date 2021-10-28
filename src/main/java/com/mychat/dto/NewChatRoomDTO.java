package com.mychat.dto;

import java.util.Set;

public class NewChatRoomDTO {

	private String name, chatUsername;
	private Set<NewChatUserDTO> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChatUsername() {
		return chatUsername;
	}

	public void setChatUsername(String chatUsername) {
		this.chatUsername = chatUsername;
	}

	public Set<NewChatUserDTO> getUsers() {
		return users;
	}

	public void setUsers(Set<NewChatUserDTO> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "NewChatRoomDTO [name=" + name + ", chatUsername=" + chatUsername + ", users=" + users + "]";
	}

	
}
