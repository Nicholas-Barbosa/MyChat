package com.mychat.reporitory;

import java.util.List;

import com.mychat.domain.Message;

public interface MessageRepository {

	void persist(List<Message>messages);
}
