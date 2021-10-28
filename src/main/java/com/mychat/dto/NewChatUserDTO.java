package com.mychat.dto;

import com.mychat.domain.ChatUser;

public class NewChatUserDTO {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ChatUser toDomain() {
		return new ChatUser(name, null);
	}

	@Override
	public String toString() {
		return "NewChatUserDTO [name=" + name + "]";
	}

}
