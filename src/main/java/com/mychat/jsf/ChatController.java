package com.mychat.jsf;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.mychat.service.ChatRoomService;
import com.mychat.service.ChatUserService;

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
	private ChatUserService chatUserService;
	private String username, roomId;
	
	

}
