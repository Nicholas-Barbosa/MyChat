package com.mychat.websocket.message;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbCreator;
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

	@JsonbCreator
	public ChatMessage(@JsonbProperty("content") String content, @JsonbProperty("roomId") String roomId,
			@JsonbProperty("type") MessageType type) {
		super();
		this.content = content;
		this.roomId = roomId;
		this.type = type;
	}

	public ChatMessage(String author, String content, String roomId, MessageType type) {
		super();
		this.author = author;
		this.content = content;
		this.roomId = roomId;
		this.type = type;
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

	@Override
	public String toString() {
		return "ChatMessage [author=" + author + ", content=" + content + ", roomId=" + roomId + ", type=" + type + "]";
	}

}
