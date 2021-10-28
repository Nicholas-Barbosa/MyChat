package com.mychat.exception;

import java.util.List;

public class ChatUsersnameNotExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4777078964952744695L;

	private List<String>usernames;

	public ChatUsersnameNotExistsException(List<String> usernames) {
		super();
		this.usernames = usernames;
	}
	
	public List<String> getUsernames() {
		return usernames;
	}
}
