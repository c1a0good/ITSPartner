window.addEventListener("load", connect, false);

var stompClient = null;
let roomObj;

function connect() {
    var socket = new SockJS('/roomBulbSwitch');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/roomBulbSwitch', function (room) {
            roomObj = JSON.parse(room.body);
            if (roomObj.id == $("#roomId").val()){
                showBulbState();
            }
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendId() {
    stompClient.send("/roomBulbSwitch", {}, $("#roomId").val());
}

function showBulbState() {
    if (roomObj.state) {
        $("#bulbState").html("Lights are on")
    }
    else {
        $("#bulbState").html("Lights are off")
    }
}

$(function () {
    $( "#mainForm" ).on('submit', function (e) {
        e.preventDefault();
    });
    $( "#deleteButton" ).click(function () { let form = $( "#deleteForm" ); document.body.append(form); form.submit() })
    $( "#switchButton" ).click(function () { sendId(); });
});