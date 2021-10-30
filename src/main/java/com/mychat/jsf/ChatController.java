package com.mychat.jsf;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import com.mychat.WebsocketUser;
import com.mychat.domain.ChatRoom;
import com.mychat.domain.ChatUser;
import com.mychat.dto.ManagedChatRoom;
import com.mychat.dto.NewChatRoomDTO;
import com.mychat.dto.NewChatUserDTO;
import com.mychat.jsf.utils.FacesUtils;
import com.mychat.service.ChatRoomService;

@ViewScoped
@Named
public class ChatController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4148835992101653326L;

	@Inject
	private ChatRoomService chatRoomService;
	@Inject
	private WebsocketUser websocketUser;

	private String roomId;

	private NewChatRoomDTO newChatRoomDTO;

	private ChatRoom room;

	public ChatController() {
		// TODO Auto-generated constructor stub
		newChatRoomDTO = new NewChatRoomDTO();
	}

	public void checkRoomId() {
		chatRoomService.findById(roomId).ifPresentOrElse(room -> {
			this.room = room;
			FacesUtils.addHeaderForResponse("room-reason", "found");
			FacesUtils.addHeaderForResponse("chat-username", websocketUser.getUsername());
			FacesUtils.ajaxUpdate("roomName","roomId");
		}, () -> {
			FacesUtils.error(null, "Sala não encontrada", "Nenhuma sala encontrada com este ID", "growl");
		});
	}

	public void createRoom() {
		newChatRoomDTO.setChatUsername(websocketUser.getUsername());
		NewChatUserDTO user = new NewChatUserDTO();
		user.setName(websocketUser.getUsername());
		newChatRoomDTO.setUsers(new HashSet<>(Set.of(user)));

		ManagedChatRoom managedRoom = chatRoomService.persist(newChatRoomDTO);
		this.room = new ChatRoom(
				managedRoom.getId(), newChatRoomDTO.getName(), managedRoom.getUsers().stream()
						.map(managedUser -> new ChatUser(managedUser.getName(), null)).collect(Collectors.toSet()),
				user.toDomain());
		FacesUtils.addHeaderForResponse("room-reason", "found");

	}

	public String getRoomId() {
		return roomId;

	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public NewChatRoomDTO getNewChatRoomDTO() {
		return newChatRoomDTO;
	}

	public ChatRoom getRoom() {
		return room;
	}

	public void getChatUsername() {
		PrimeFaces.current().ajax().addCallbackParam("chatUsername", websocketUser.getUsername());
	}
}
