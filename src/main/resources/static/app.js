var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    //stompClient.send("/app/newPlayer", {}, JSON.stringify({'id' : $("#id".val())}));

    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);

        stompClient.subscribe('/player/id', function(res){
            console.log("RIGHT HERE" + res.body);
            $("#greetings").append("<tr><td> Player " + res.body + " has joined </td></tr>");
        });
        stompClient.send("/app/newPlayer")

    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

//function sendName() {
    //stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
//}

//function showGreeting(message) {
   // $("#greetings").append("<tr><td>" + message + "</td></tr>");
//}

function showConnection(message){
    $("player").append("<tr><td>" + message + "has joined </td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    //$( "#send" ).click(function() { sendName(); });
});