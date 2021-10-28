package com.mychat.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.mychat.dto.NewChatUserDTO;
import com.mychat.service.ChatUserService;

@Path("chatUser")
public class ChatUserResource {

	@Inject
	private ChatUserService service;

	@POST
	public Response persist(NewChatUserDTO user) {
		service.persist(user);
		return Response.status(201).build();
	}
}
