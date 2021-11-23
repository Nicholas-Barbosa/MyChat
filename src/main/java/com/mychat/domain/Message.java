package com.mychat.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Message extends BaseEntity {

	@Column(length = 200, nullable = false)
	private String content;
	@Column(nullable = false)
	private LocalDateTime sendAt;
	@ManyToOne
	private User from;
	@ManyToOne
	private ChatRoom room;

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String content, LocalDateTime sendAt, User from, ChatRoom room) {
		super();
		this.content = content;
		this.sendAt = sendAt;
		this.from = from;
		this.room = room;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getSendAt() {
		return sendAt;
	}

	public User getFrom() {
		return from;
	}

	public ChatRoom getRoom() {
		return room;
	}

}
