package com.mychat.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ChatRoom extends BaseEntity {

	@OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
	private List<Message> messages;
	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ChatRoomUser> users;
	private LocalDateTime createdAt;
	@ManyToOne(fetch = FetchType.LAZY)
	private User createdBy;

	@Column(unique = true, nullable = false, length = 20, name = "hash_name")
	private String hashName;

	public ChatRoom() {
		// TODO Auto-generated constructor stub
	}

	public ChatRoom(List<Message> messages, List<ChatRoomUser> users, LocalDateTime createdAt, User createdBy) {
		super();
		this.messages = messages;
		this.users = users;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.hashName = hashGenerator();
	}

	public List<Message> getMessages() {
		return messages;
	}

	public List<ChatRoomUser> getUsers() {
		return users;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public String getHashName() {
		return hashName;
	}

	private static synchronized String hashGenerator() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss"));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashName == null) ? 0 : hashName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatRoom other = (ChatRoom) obj;
		if (hashName == null) {
			if (other.hashName != null)
				return false;
		} else if (!hashName.equals(other.hashName))
			return false;
		return true;
	}

}
