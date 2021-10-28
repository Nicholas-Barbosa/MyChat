package com.mychat.service;

import java.util.Optional;

import com.mychat.domain.ChatUser;
import com.mychat.dto.NewChatUserDTO;

public interface ChatUserService {

	void persist(NewChatUserDTO dto);

	Optional<ChatUser> findByUsername(String username);
}
