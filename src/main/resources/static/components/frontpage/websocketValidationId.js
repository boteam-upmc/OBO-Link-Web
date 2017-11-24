var websocket = null;

//distinger le navigateur qui support WebSocket ou pas
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8080/websocket/foo/bar");
}
else {
    alert('Not support websocket')
}


websocket.onerror = function () {
    setMessageInnerHTML("error");
};


websocket.onopen = function (event) {
    //setMessageInnerHTML("open");
}


websocket.onmessage = function (event) {
    var objData = event.data;
    var jsonIdRobot = JSON.parse(objData);
    switch(jsonIdRobot.id) {
        case "validID":
            //TODO affichage tableau id par userId
            var test ="";
            jsonIdRobot.content.forEach(function(element) {
                test += "<tr>\n";
                test += "<td>\n";
                test += element.idUser;
                test += "\n</td>\n";
                test += "<td>\n";
                test += element.idRobot;
                test += "\n</td>\n";
                test += "<td>\n";
                test += " <button class=\"btn btn-danger\" type=\"button\"><i class=\"icon-warning-sign\"></i> Delete </button> \n";
                test += "</td>\n";
                test += "</tr>\n";
            });

            document.getElementById('message').innerHTML += test + '<br/>';
            break;

        case "ASSOC":
            var messages = jsonIdRobot.content.message;
            var idUsers = jsonIdRobot.content.idUser;
            var idRobots = jsonIdRobot.content.idRobot;

            setMessageInnerHTML(jsonIdRobot);
            break;
    }
}


websocket.onclose = function () {
    setMessageInnerHTML("close");
}


window.onbeforeunload = function () {
    websocket.close();
}


function setMessageInnerHTML(innerHTML) {
    var newJsonIdRobot = innerHTML;
    bootbox.confirm({
        title: "Add Robot ?",
        message: newJsonIdRobot.content.message,
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> NO'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> YES'
            }
        },
        callback: function (result) {
            if(result === true) {
                var userRobot = {
                    idUser: newJsonIdRobot.content.idUser,
                    idRobot: newJsonIdRobot.content.idRobot
                }

                websocket.send(JSON.stringify(userRobot));
            }else {
                console.log("Non ");
            }
        }
    });
}
function setArrayId(innerHTML) {

}


function closeWebSocket() {
    websocket.close();
}


function send() {
    var message = document.getElementById('text').value;
    websocket.send(message);
}

function send_message(message) {
    websocket.send(message);
}
