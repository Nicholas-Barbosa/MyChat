package com.mychat.repository;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import javax.enterprise.context.ApplicationScoped;

import com.mychat.domain.ChatUser;

@ApplicationScoped
public class ChatUserRepositoryImpl implements ChatUserRepository {

	private Set<ChatUser> users = new ConcurrentSkipListSet<>();

	@Override
	public void persist(ChatUser user) {
		users.add(user);
	}

	@Override
	public Optional<ChatUser> findByUsername(String username) {
		return users.parallelStream().filter(u -> u.getUsername().equals(username)).findAny();

	}

}
