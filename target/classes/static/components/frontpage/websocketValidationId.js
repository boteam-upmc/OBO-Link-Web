var websocket = null;

//distinger le navigateur qui supoport WebSocket ou pas
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8080/websocket");
}
else {
    alert('Not support websocket')
}

//连接发生错误的回调方法
websocket.onerror = function () {
    setMessageInnerHTML("error");
};

//连接成功建立的回调方法
websocket.onopen = function (event) {
    //setMessageInnerHTML("open");
}

//接收到消息的回调方法
websocket.onmessage = function (event) {

    var tab = event.data.split("/");
    var test ="";
    switch(tab[0]) {
        case "assoc":
            //TODO affichage tableau id



            for (var i = 1; i < tab.length-1; i++) {
              //  test += "<a href=\"#\" class=\"list-group-item list-group-item-action list-group-item-success\">\n";

                test += "<tr>\n";
                //test += "<a href=\"#\" class=\"list-group-item list-group-item-action flex-column align-items-start active\">\n";
                test += "<td>\n";
                test += tab[i];
                test += "\n</td>\n";
                test += "<td>\n";
                test += " <button class=\"btn btn-danger\" type=\"button\"><i class=\"icon-warning-sign\"></i> Delete </button> \n";
                test += "</td>\n";
                //test += "</a>";
                test += "</tr>\n";

            }


            document.getElementById('message').innerHTML += test + '<br/>';
            break;
        case "validID":
            setMessageInnerHTML(tab[1]);
            break;
    }
}

//连接关闭的回调方法
websocket.onclose = function () {
    setMessageInnerHTML("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    websocket.close();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
   //document.getElementById('message').innerHTML += innerHTML + '<br/>';
    //confirm(innerHTML);
    bootbox.confirm({
        title: "Add Robot?",
        message: innerHTML,
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> Cancel'
            },
            confirm: {
                label: '<i class="fa fa-check"></i> Confirm'
            }
        },
        callback: function (result) {
            console.log("message :"+result);
            websocket.send(result);
        }
    });
}
function setArrayId(innerHTML) {

}

//关闭连接
function closeWebSocket() {
    websocket.close();
}

//发送消息
function send() {
    var message = document.getElementById('text').value;
    websocket.send(message);
}
