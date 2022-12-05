var stompClient = null;
var id = null;

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

function setStartGame(show){
    $("#startGame").prop("disabled", !show);
}

function startGame(){
    //console.log(id); - do this to only update thier side
    //$("#startGame").prop("disabled", true);
    //$("#game").append("<div id='discardPile'> <p>Discard pile</p> </div>" +
     //                  "<div id='hand'> <p>hand</p> <button id='play' class='btn btn-default' type='submit'>Play</button></div>");
    stompClient.send("/app/startGame")

}

function getHand(){
    console.log("IN GET HAND" + id);
    stompClient.send("/app/getHand");
}

function playGame(){
    console.log("In player turn");
    stompClient.send("/app/playTurn");
}

function updateGame(){
    console.log("IN update game");
    stompClient.send("/app/updateGame", {}, JSON.stringify({'card': $("#send").val()}));
    $("#hand").empty();
    stompClient.send("/app/playTurn");
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
            //console.log(res.body[0]);
            $("#greetings").append("<tr><td> Player " + res.body[0] + " has joined </td></tr>");
            if(!id){ // set id if not set already
                id = res.body[0];
            }
            if(res.body.length > 2){
                if(res.body[2] == "b"){
                    setStartGame(true);
                }
                else{
                    setStartGame(false);
                    startGame();
                }
            }
        });
        stompClient.send("/app/newPlayer")

        stompClient.subscribe('/player/startGame', function(res){
            console.log("startingGame");
            $("#game").append("<div id='discardPile'> <p>Discard pile</p> </div>" +
            "<div id='stockPile'> <p>Stock Pile</p>" + res.body + "</div>" +
            "<div id='hand'> <p>hand</p> <div id='play'></div> </div>");
            getHand();
            playGame();
        });

        stompClient.subscribe("/player/receiveHand", function(res){
            console.log("recvied hand "+id +" "+ res);
            if(id == res.body[0]){
                $("#hand").append("<p>" + res.body + "</p>");
            }

        });

        stompClient.subscribe("/player/receiveTurn", function(res){
                    console.log("recvied hand "+id +" "+ res);
                    if(id == res.body[0]){
                        $("#hand").append(' <form class="form-inline">' +
                              '<div class="form-group"> <label for="card">What card do you want to play</label>' +
                              '<input type="text" id="card" class="form-control" >' +
                              '</div> <button id="send" class="btn btn-default" type="submit">Send</button> </form>');
                    }

                });
        stompClient.subscribe("/player/receiveHand", function(res){
                    console.log("recvied hand "+id +" "+ res);
                    if(id == res.body[0]){
                        $("#hand").append("<p>" + res.body + "</p>");
                    }

                });




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
    $( "#startGame" ).click(function() { startGame(); });
    $("#send").click(function() {updateGame();});
    //$( "#send" ).click(function() { sendName(); });
});