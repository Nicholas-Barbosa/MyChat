package com.mychat.service;

import java.util.Optional;

import com.mychat.domain.ChatRoom;
import com.mychat.dto.ManagedChatRoom;
import com.mychat.dto.NewChatRoomDTO;

public interface ChatRoomService {

	ManagedChatRoom persist(NewChatRoomDTO room);
	
	Optional<ChatRoom> findById(String id);
}
