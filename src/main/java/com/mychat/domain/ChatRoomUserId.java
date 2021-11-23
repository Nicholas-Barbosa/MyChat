package com.mychat.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ChatRoomUserId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5095476369831455862L;

	private Long userId;
	private Long roomId;

	public ChatRoomUserId() {
		// TODO Auto-generated constructor stub
	}

	public ChatRoomUserId(Long userId, Long roomId) {
		super();
		this.userId = userId;
		this.roomId = roomId;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getRoomId() {
		return roomId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		ChatRoomUserId other = (ChatRoomUserId) obj;
		if (roomId == null) {
			if (other.roomId != null)
				return false;
		} else if (!roomId.equals(other.roomId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
