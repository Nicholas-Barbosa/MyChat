package com.mychat.repository;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.mychat.domain.ChatRoom;
import com.mychat.domain.ChatUser;

@Singleton
@Startup
public class ChatUserDataBootstrapper {

	@Inject
	private ChatUserRepository chatUserRepository;

	@Inject
	private ChatRoomRepository roomRepository;

	@PostConstruct
	public void init() {
		System.out.println("Saving chat users and chat room...");
		ChatUser nicholas = new ChatUser("nicholas.justo", null);
		ChatUser katia = new ChatUser("katia.justo", null);
		ChatUser paulo = new ChatUser("paulo", null);
		
		chatUserRepository.persist(nicholas);
		chatUserRepository.persist(katia);
		chatUserRepository.persist(paulo);
		
		ChatRoom room = new ChatRoom(null, "Chat do nicholas", new HashSet<ChatUser>(Set.of(nicholas)),
				nicholas);
		roomRepository.persist(room);
		System.out.println("roomID: " + room.getId());
	}
}
