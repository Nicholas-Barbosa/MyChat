package com.mychat.websocket.message;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;

public class ChatMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7655905362519242283L;
	private String author;
	private String content;
	private String roomId;
	private MessageType type;
	private LocalDateTime sendAt;

	@JsonbCreator
	public ChatMessage(@JsonbProperty("content") String content, @JsonbProperty("roomId") String roomId,
			@JsonbProperty("type") MessageType type,
			@JsonbProperty("send_at") @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss") LocalDateTime sendAt) {
		super();
		this.content = content;
		this.roomId = roomId;
		this.type = type;
		this.sendAt = sendAt;
	}

	public ChatMessage(String author, String content, String roomId, MessageType type, LocalDateTime sendAt) {
		super();
		this.author = author;
		this.content = content;
		this.roomId = roomId;
		this.type = type;
		this.sendAt = sendAt;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public String getRoomId() {
		return roomId;
	}

	public MessageType getType() {
		return type;
	}

	public LocalDateTime getSendAt() {
		return sendAt;
	}

	public void setSendAt(LocalDateTime sendAt) {
		this.sendAt = sendAt;
	}

	@Override
	public String toString() {
		return "ChatMessage [author=" + author + ", content=" + content + ", roomId=" + roomId + ", type=" + type + "]";
	}

}
