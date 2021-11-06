package com.mychat.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class User extends BaseEntity {

	@Column(length = 20, nullable = false)
	private String name;
	@Column(length = 20, nullable = false)
	private String lastName;
	@Column(length = 20, nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "from")
	private List<Message> sendMessages;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "to")
	private List<Message> incommingMessages;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	
	public User(String name, String lastName, String email, String password, List<Message> sendMessages,
			List<Message> incommingMessages) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.sendMessages = sendMessages;
		this.incommingMessages = incommingMessages;
	}


	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
