package com.mychat;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

@SessionScoped
public class WebsocketUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
