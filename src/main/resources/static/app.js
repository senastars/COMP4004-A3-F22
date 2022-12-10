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
    stompClient.send("/app/playTurn"); //Gets current player
}

function updateGame(){
    console.log("IN update game");
    stompClient.send("/app/updateGame", {}, JSON.stringify({'card': $("#send").val()}));
    $("#hand").empty();
    stompClient.send("/app/playTurn");
}

function playCard(){
    console.log("IN playcard");
    stompClient.send("/app/playCard", {}, $("#card").val());
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
            if(!id){ // set id if not set already
                id = res.body[0];
            }
            $("#greetings").append("<tr><td> Player " + res.body[0] + " has joined </td></tr>");
            //console.log(res.body)
            if(res.body.length > 2){
                if(res.body[2] == "b"){
                    setStartGame(true);
                }
            }
        });


        stompClient.subscribe('/player/startGame', function(res){
             console.log("startingGame");
             $("#game").show();
             getHand();
             playGame();
        });

        stompClient.subscribe("/player/receiveHand", function(res){
            //console.log("recvied hand "+id +" "+ res.body);
            console.log("recieved hand "+id )
            if(id == res.body[0]){
                $("#hand").html("<p>" + res.body + "</p>");
            }

        });

        stompClient.subscribe("/player/receiveTurn", function(res){
                    //console.log("recvied turn "+id +" "+ res);
                    console.log("recieved turn" + id)
                    if(id == res.body){
                        console.log("SETTING BUTTON")
                        $("#sendCard").prop("disabled", false)
                    }
                    else{
                        console.log("CAN'T PLAY NOT YOUR TURN")
                        $('#sendCard').prop("disabled", true)
                    }

                    $("#currPlayer").html("<p id=" +"cp" +">"  +"Current Player " + res.body + "</p>")

                });
        stompClient.subscribe("/player/receiveHand", function(res){
                    console.log("recvied hand "+id +" "+ res);
                    if(id == res.body[0]){
                        $("#hand").html("<p>" + res.body + "</p>");
                    }

                });

        stompClient.subscribe("/player/receiveCard", function (res){
            console.log("RECEIEVED CARD " +res.body)
            if(id == res.body[0]){
                let place = res.body.indexOf(":");
                let hand = res.body.substring(place+1);
                $("#hand").html("<p>" + hand + "</p>");
            }
            //const split = res.split(",");
            $("#greetings").append("<tr><td> Player " + res.body[0] +" has played "+ res.body[6]+ res.body[7] + res.body[8]  + "</td></tr>");

            if(res.body[4] == "1"){ //If 1 then up
                console.log("Switching direction to UP")
                $("#order").html("<p>Turn Order: Up</p>")
                $("#order").prop("name", "up");
            }
            else {
                console.log("Switching direction to DOWN")
                $("#order").html("<p>Turn Order: Down</p>")
                $("#order").prop("name", "down");
            }

            $("#greetings").append("<tr><td> Next player is" + res.body[2]+ "</td></tr>");
            //GAME LOGIC GOES HERE
            getHand();
            playGame();

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
    $("#tempForm").on('submit', function (e) {
            e.preventDefault();
        });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#startGame" ).click(function() { startGame(); });
    //$("#send").click(function() {updateGame();});
    //$( "#send" ).click(function() {playCard(); });
    //$( "#send" ).click(function() { sendName(); });
});