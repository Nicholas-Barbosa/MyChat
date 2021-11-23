package com.mychat.jsf;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import com.mychat.domain.User;
import com.mychat.service.UserService;
import com.mychat.servlet.SessionUser;

@RequestScoped
@Named
public class LoginController {

	@NotBlank
	private String email, password;

	@Inject
	private UserService userService;

	@Inject
	private SessionUser session;

	public String login() {
		Optional<User> op = userService.findByEmailAndPassword(email, password);
		if (op.isPresent()) {
			session.setUser(op.get());
			return "/faces/landing?faces-redirect=true";
		}
		return null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
