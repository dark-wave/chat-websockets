var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
	$("#clearMessages").prop("disabled", !connected);
	$("#inputName").prop("disabled", !connected);
	$("#inputMsg").prop("disabled", !connected);
	$("#btnSend").prop("disabled", !connected);
}

function clearMessages(){
	$("#messages").html("");
}

function connect() {
    var socket = new SockJS('http://192.168.1.90:3000/websocket-chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Conectado: ' + frame);
        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Desconectado");
}

function sendMsg(){
	stompClient.send("/app/chat", 
		{}, 
		JSON.stringify({
			'from': $("#inputName").val() != '' ? $("#inputName").val() : 'Anonymous',
			'content': $("#inputMsg").val() 
		}));
	$("#inputMsg").val("");
}


function showMessage(message) {
    $("#messages").append("<tr><td><span style='color:red'>" + message.from + "</span>:  <span>" + message.content+"</span></td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
	$( "#clearMessages" ).click(function() { clearMessages(); });
	$( "#btnSend" ).click(function() { sendMsg(); });
});