package com.mychat.websocket.message;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

	private final Jsonb jsonb;

	public ChatMessageDecoder() {
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
		try {
			jsonb.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ChatMessage decode(String s) throws DecodeException {
		return jsonb.fromJson(s, ChatMessage.class);
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}

}
