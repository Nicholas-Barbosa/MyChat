package com.mychat.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Message extends BaseEntity{

	@Column(length = 200,nullable = false)
	private String content;
	@Column(nullable = false)
	private LocalDateTime sendAt;
	@ManyToOne
	private User from;
	@ManyToOne
	private User to;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(String content, User from, User to) {
		super();
		this.content = content;
		this.sendAt = LocalDateTime.now();
		this.from = from;
		this.to = to;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public LocalDateTime getSendAt() {
		return sendAt;
	}
	
	
}
