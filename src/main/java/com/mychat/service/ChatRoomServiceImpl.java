package com.mychat.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mychat.domain.ChatRoom;
import com.mychat.domain.ChatUser;
import com.mychat.dto.ManagedChatRoom;
import com.mychat.dto.ManagedChatUserDTO;
import com.mychat.dto.NewChatRoomDTO;
import com.mychat.exception.ChatUsersnameNotExistsException;
import com.mychat.repository.ChatRoomRepository;

@ApplicationScoped
public class ChatRoomServiceImpl implements ChatRoomService {

	@Inject
	private ChatRoomRepository repository;

	@Inject
	private ChatUserService chatUserService;

	@Override
	public ManagedChatRoom persist(NewChatRoomDTO newRoom) {
		List<String> usernamesNotFound = new CopyOnWriteArrayList<>();
		Set<ChatUser> usersFound = new CopyOnWriteArraySet<>();

		ChatUser createdBy = chatUserService.findByUsername(newRoom.getChatUsername()).orElse(null);
		if (createdBy == null)
			usernamesNotFound.add(newRoom.getChatUsername());

		boolean allUsersExists = createdBy != null && (newRoom.getUsers() == null || newRoom.getUsers().isEmpty() ? true
				: newRoom.getUsers().parallelStream().map(n -> n.getName()).allMatch(username -> {
					Optional<ChatUser> user = chatUserService.findByUsername(username);

					if (user.isPresent()) {
						usersFound.add(user.get());
						return true;
					}
					usernamesNotFound.add(username);

					return false;
				}));

		if (allUsersExists) {
			ChatRoom room = new ChatRoom(null, newRoom.getName(), usersFound, createdBy);
			usersFound.add(createdBy);
			repository.persist(room);
			return new ManagedChatRoom(room.getId(),
					usersFound.stream().map(ManagedChatUserDTO::fromDomain).collect(Collectors.toSet()));
		}

		throw new ChatUsersnameNotExistsException(usernamesNotFound);

	}

	@Override
	public Optional<ChatRoom> findById(String id) {
		return repository.findById(id);
	}

}
