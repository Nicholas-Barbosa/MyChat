package com.mychat.reporitory;

import java.util.Optional;

import com.mychat.domain.ChatRoom;

public interface ChatRoomRepository {

	Optional<ChatRoom>findByName(String name);
	
	void persist(ChatRoom room);
}
