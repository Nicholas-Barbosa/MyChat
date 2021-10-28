package com.mychat.dto;

import com.mychat.exception.ChatUsersnameNotExistsException;

public class ChatUsersDoNotExistsDTO {

	private String[] users;

	public ChatUsersDoNotExistsDTO(ChatUsersnameNotExistsException exception) {
		this.users = exception.getUsernames().toArray(new String[0]);
	}

	public String getReason() {
		return "The following users do not exist ";
	}

	public String[] getUsers() {
		return users;
	}
}
