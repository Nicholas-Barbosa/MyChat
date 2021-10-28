package com.mychat.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mychat.domain.ChatUser;
import com.mychat.dto.NewChatUserDTO;
import com.mychat.repository.ChatUserRepository;

@ApplicationScoped
public class ChatUserServiceImpl implements ChatUserService {

	@Inject
	private ChatUserRepository repository;

	@Override
	public void persist(NewChatUserDTO dto) {
		repository.persist(dto.toDomain());
	}

	@Override
	public Optional<ChatUser> findByUsername(String username) {
		return repository.findByUsername(username);
	}

}
