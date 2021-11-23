package com.mychat.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class ChatRoomUser {

	@EmbeddedId
	private ChatRoomUserId id;
	@ManyToOne
	@MapsId("userId")
	private User user;
	@ManyToOne
	@MapsId("roomId")
	private ChatRoom room;

	public ChatRoomUser() {
		// TODO Auto-generated constructor stub
	}

	public ChatRoomUser(ChatRoomUserId id, User user, ChatRoom room) {
		super();
		this.id = id;
		this.user = user;
		this.room = room;
	}

	public User getUser() {
		return user;
	}

	public ChatRoom getRoom() {
		return room;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		ChatRoomUser other = (ChatRoomUser) obj;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}
