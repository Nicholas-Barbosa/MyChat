package com.mychat.repository;

import java.util.Optional;

import com.mychat.domain.ChatRoom;

public interface ChatRoomRepository {

	void persist(ChatRoom room);

	boolean contains(ChatRoom room);

	Optional<ChatRoom> findById(String id);
	
}
