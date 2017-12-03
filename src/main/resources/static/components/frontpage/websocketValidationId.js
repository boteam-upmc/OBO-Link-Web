
//1, apres que l'utilisateur se connecter, obtenir le parametre idUser
var idUser=window.location.href.split("=")[1];

var websocket = null;

//2, apres que l'utilisateur se connecter, afficher tous les associations par utilisateur
getAssociation();


//distinger le navigateur qui support WebSocket ou pas
if ('WebSocket' in window) {
    //3, apres que l'utilisateur se connecter, ouvrir websocket pour ecouter l'evenment
    websocket = new WebSocket("ws://localhost:8080/websocket/"+ idUser);
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
            writeHtml(jsonIdRobot.content)
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

function getAssociation() {
    $.get('http://localhost:8080/api/'+ idUser +'/users_robots'
    ).done(function (data, status) {
        writeHtml(data);
    })
}

function deleteVideo(idUser, idRobot) {
    //TODO add confirm
    //if(confirm('Are you sure you want to delete this?')){
    var userRobot = {
        idUser: idUser,
        idRobot: idRobot
    }

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/api/"+idUser+"/users_robots/delete",
        datatype: "application/json",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(userRobot),
        success: function (result) {
            writeHtml(result);
        },
        error: function (error) {
            alert(error)
        }
    })
}

// affichage tableau d'association par userId
function writeHtml(data) {
    $('#message').html('');
    var test ="";
    data.forEach(function(element) {
        test += "<tr>\n";
        test += "<td>\n";
        test += element.idUser;
        test += "\n</td>\n";
        test += "<td>\n";
        test += element.idRobot;
        test += "\n</td>\n";
        test += "<td>\n";
        test += "<a href=\"#\" onclick=\"deleteVideo("+element.idUser+","+ element.idRobot+")\">";
        test += "<button class=\"btn btn-danger\" type=\"button\"><i class=\"icon-warning-sign\"></i> Deleteeee</button>";
        test += "</a>";
        test += "</td>\n";
        test += "</tr>\n";
    });
    $('#message').append(test);

}
