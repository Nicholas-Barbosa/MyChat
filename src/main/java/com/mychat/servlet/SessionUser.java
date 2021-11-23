package com.mychat.servlet;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import com.mychat.domain.User;

@SessionScoped
public class SessionUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
