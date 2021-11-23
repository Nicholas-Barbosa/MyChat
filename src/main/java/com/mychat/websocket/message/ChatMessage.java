package com.mychat.websocket.message;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;

import com.mychat.domain.User;

public class ChatMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7655905362519242283L;
	private String content, roomId;
	private User from;
	private MessageType type;
	private LocalDateTime sendAt;

	@JsonbCreator
	public ChatMessage(@JsonbProperty("content") String content, @JsonbProperty("roomId") String roomId,
			@JsonbProperty("send_at") @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ss") LocalDateTime sendAt) {
		super();
		this.content = content;
		this.roomId = roomId;
		this.sendAt = sendAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getContent() {
		return content;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
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

	@Override
	public String toString() {
		return "ChatMessage [content=" + content + ", roomId=" + roomId + ", from=" + from + ", type=" + type
				+ ", sendAt=" + sendAt + "]";
	}

}
