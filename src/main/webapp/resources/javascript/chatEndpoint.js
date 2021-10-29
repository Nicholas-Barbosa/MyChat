/**
 * 
 */
function connect(username) {
	let chatEndpoint = new WebSocket("ws://localhost:8080/MyChat/chat/" + username);
	chatEndpoint.onmessage = function(event) {
		var jsonObject = JSON.parse(event.data);
		addDivMessage(jsonObject);
	}
	chatEndpoint.onclose = function(event) {
		alert("Conexão com websocket fechada, "+event.reason)
	}

}

function addDivMessage(jsonObect){
	var message = jsonObect.content;
	
	var messagesContainer = document.getElementById("messagesContainer");
	var messageDivWrapper = document.createElement("div");
	var messageDiv = document.createElement("div");
	
	messageDivWrapper.className="chat-message";
	messageDiv.className="message"
	messageDiv.appendChild(document.createTextNode(message));
	messageDivWrapper.appendChild(messageDiv);
	messagesContainer.appendChild(messageDivWrapper);
}