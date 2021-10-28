package com.mychat.repository;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.enterprise.context.ApplicationScoped;

import com.mychat.domain.ChatRoom;

@ApplicationScoped
public class ChatRoomRepositoryImpl implements ChatRoomRepository {

	private final Set<ChatRoom> chatRooms = new ConcurrentSkipListSet<>();

	@Override
	public void persist(ChatRoom room) {
		room.setId(ChatRoomIDGenerator.generate(room));
		chatRooms.add(room);
	}

	@Override
	public boolean contains(ChatRoom room) {
		return chatRooms.contains(room);
	}

	@Override
	public Optional<ChatRoom> findById(String id) {
		// TODO Auto-generated method stub
		return chatRooms.parallelStream().filter(room -> room.getId().equals(id)).findAny();
	}

}
