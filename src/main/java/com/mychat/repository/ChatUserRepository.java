package com.mychat.repository;

import java.util.Optional;

import com.mychat.domain.ChatUser;

public interface ChatUserRepository {

	void persist(ChatUser user);

	Optional<ChatUser> findByUsername(String username);
}
