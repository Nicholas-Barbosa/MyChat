package com.mychat.dto;

import com.mychat.domain.ChatUser;

public class ManagedChatUserDTO {

	private String name;

	public static ManagedChatUserDTO fromDomain(ChatUser chat) {
		return new ManagedChatUserDTO(chat.getUsername());
	}

	public ManagedChatUserDTO(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ManagedChatUserDTO other = (ManagedChatUserDTO) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
