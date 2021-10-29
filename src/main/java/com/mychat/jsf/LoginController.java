package com.mychat.jsf;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mychat.WebsocketUser;
import com.mychat.service.ChatUserService;

@RequestScoped
@Named
public class LoginController {

	private String username;
	
	@Inject
	private ChatUserService service;
	
	@Inject
	private WebsocketUser webscoketUser;
	
	public String doLogin() {
		boolean exists = service.findByUsername(username).isPresent();
		if(exists) {
			webscoketUser.setUsername(username);
			return "/faces/room?faces-redirect=true";
		}
		return null;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
