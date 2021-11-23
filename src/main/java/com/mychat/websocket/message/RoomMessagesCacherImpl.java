package com.mychat.websocket.message;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.mychat.domain.ChatRoom;
import com.mychat.service.MessageService;
import com.mychat.websocket.pojo.RoomChatMessage;

@ApplicationScoped
public class RoomMessagesCacherImpl implements RoomMessagesCacher {

	private final Map<ChatRoom, List<RoomChatMessage>> messages = new ConcurrentHashMap<>();

	@Inject
	private MessageService messageSerivce;

	@Resource
	private ManagedScheduledExecutorService executorService;

	@PostConstruct
	public void init() {
		executorService.scheduleWithFixedDelay(this::flush, 0, 5, TimeUnit.MINUTES);
	}

	@Override
	public void addMessage(RoomChatMessage message) {
		if (messages.putIfAbsent(message.getRoom(), Arrays.asList(message)) != null) {
			messages.get(message.getRoom()).add(message);
		}

	}

	@Override
	public List<RoomChatMessage> getAll(ChatRoom room) {
		// TODO Auto-generated method stub
		return messages.get(room);
	}

	@Override
	public void clean(ChatRoom room) {
		messages.remove(room);

	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		messages.entrySet().parallelStream().map(Entry::getValue).forEach(messageSerivce::persistRoomChatMessages);
		messages.clear();
	}

}
