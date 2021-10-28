package com.mychat.repository;

import java.time.format.DateTimeFormatter;

import com.mychat.domain.ChatRoom;

public class ChatRoomIDGenerator {

	public static String generate(ChatRoom chat) {
		return new StringBuilder(chat.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyyddMM_hhmmss"))).toString();
	}
}
