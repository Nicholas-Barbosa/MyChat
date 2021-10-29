/**
 * 
 */
function connect(username) {
	let chatEndpoint = new WebSocket("ws://localhost:8080/MyChat/chat/" + username);
	console.log(chatEndpoint);
}