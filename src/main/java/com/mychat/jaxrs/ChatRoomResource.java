package com.mychat.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mychat.dto.ChatUsersDoNotExistsDTO;
import com.mychat.dto.NewChatRoomDTO;
import com.mychat.exception.ChatUsersnameNotExistsException;
import com.mychat.service.ChatRoomService;

@Path("chatRoom")
public class ChatRoomResource {

	@Inject
	private ChatRoomService service;

	@POST
	public Response persist(NewChatRoomDTO room) {
		try {

			return Response.status(201).entity(service.persist(room)).build();
		} catch (ChatUsersnameNotExistsException e) {
			return Response.status(Status.NOT_FOUND).entity(new ChatUsersDoNotExistsDTO(e))
					.type(MediaType.APPLICATION_JSON).build();
		}
	}

}
