package com.mychat.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mychat.domain.ChatRoom;
import com.mychat.reporitory.ChatRoomRepository;

@ApplicationScoped
public class ChatRoomServiceImpl implements ChatRoomService {

	@Inject
	private ChatRoomRepository repository;

	@Override
	public Optional<ChatRoom> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public void persist(ChatRoom room) {
		repository.persist(room);
	}

}
