package com.mychat.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mychat.domain.Message;
import com.mychat.reporitory.MessageRepository;
import com.mychat.websocket.message.ChatMessage;
import com.mychat.websocket.pojo.RoomChatMessage;

@ApplicationScoped
public class MessageServiceImpl implements MessageService {

	@Inject
	private MessageRepository repository;

	@Inject
	private ChatRoomService roomService;

	@Override
	public void persist(List<Message> messages) {
		repository.persist(messages);

	}

	@Override
	public void persistRoomChatMessages(List<RoomChatMessage> messages) {
		System.out.println("Batch Persist room messages!");
		this.persist(messages.stream().map(this::toMessage).collect(Collectors.toList()));

	}

	private Message toMessage(RoomChatMessage roomChatMessage) {
		ChatMessage chatMessage = roomChatMessage.getMessage();
		return new Message(chatMessage.getContent(), chatMessage.getSendAt(), chatMessage.getFrom(),
				roomChatMessage.getRoom());
	}

}
