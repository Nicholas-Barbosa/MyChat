package com.mychat.websocket.message;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage>{

	private final Jsonb jsonb;
	
	
	public ChatMessageEncoder() {
		super();
		this.jsonb = JsonbBuilder.create();
	}

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(ChatMessage object) throws EncodeException {
		return jsonb.toJson(object);
	}

}
