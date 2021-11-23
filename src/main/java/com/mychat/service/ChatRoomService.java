package com.mychat.service;

import java.util.Optional;

import com.mychat.domain.ChatRoom;

public interface ChatRoomService {

	Optional<ChatRoom>findByName(String name);
	
	void persist(ChatRoom room);
}
