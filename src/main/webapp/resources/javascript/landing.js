/**
 * 
 */
var chatUsername;
const styleForIncomingMessages = "chat-message"
const styleForSendMessages = "chat-message send"
var chatEndpoint;
var currentRoom;
function connect() {
	$("#messages").hide();
	chatEndpoint = new WebSocket("ws://192.168.100.7:8080/MyChat/chat/" + chatUsername);
	$("#messages").show();

	chatEndpoint.onmessage = function(event) {
		var jsonObject = event.data;
		appendDivMessage(jsonObject, styleForIncomingMessages);
	}
	chatEndpoint.onclose = function(event) {
		console.log(event);
	}
	
}

function sendMessage(message, roomId) {
	console.log("send " + message + "to roomId " + roomId);
	var jsonMessage = JSON.stringify({
		content: message,
		roomId: roomId,
		type: 'NORMAL'
	});
	chatEndpoint.send(jsonMessage);
	appendDivMessage(jsonMessage, styleForSendMessages)
}

function appendDivMessage(jsonObect, style) {
	var objectFromJson = JSON.parse(jsonObect);
	
	var message = objectFromJson.content;
	var messagesContainer = document.getElementById("messagesContainer");
	var messageDivWrapper = document.createElement("div");
	var messageDiv = document.createElement("div");

	messageDivWrapper.className = style;
	messageDiv.className = "message"
	messageDiv.appendChild(document.createTextNode(message));
	messageDivWrapper.appendChild(messageDiv);
	messagesContainer.appendChild(messageDivWrapper);
}


function checkRoomExists(xhr) {
	if (xhr.getResponseHeader("room-reason") ==  "found") {
		$("#connectToRoom").hide();
		connect();

	}

}