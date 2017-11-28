package com.webSocket;

import com.domain.UsersRobots;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.repository.UserRobotResopitory;
import com.services.Server;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ServerEndpoint(value = "/websocket/{user}/{password}", decoders = {UsersRobotsDecoder.class}, encoders = {UsersRobotsEncoder.class})
@Component
public class MyWebSocket {

    private static int onlineCount = 0;
    private static ArrayList<MyWebSocket> webSocketSet = new ArrayList<>();
    private Session session;
    private UserRobotResopitory repository = new UserRobotResopitory();

    //TODO modifier pour que appler ce methode quand le client se connecter
    @OnOpen
    public void onOpen (@PathParam("user") String user,
                        @PathParam("password")String password,
                        Session session){
        this.session = session;
        System.out.println("user="+user+"&password="+password);
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("Un nouveau client vient de se connecter! " + getOnlineCount());
    }

    @OnClose
    public void onClose (){
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("Fermer une connextion" + getOnlineCount());
    }

    @OnMessage
    public void onMessage (UsersRobots usersRobots, Session session) throws IOException {
        System.out.println("user=="+ usersRobots.getIdUser()+"&"+usersRobots.getIdRobot());

        repository.save(usersRobots);

        //TODO findAll(int userId)
        List<UsersRobots> all = repository.findByUserId(usersRobots.getIdUser());
        sendMessage("validID", all);

        Server.out.print("VALID/TRUE\r");
        Server.out.flush();
        System.out.println("Envoyer");
        /*}else{ //TODO renvoie false why ?
            Server.out.print("VALID/FALSE\r");
            Server.out.flush();
        }*/
    }

    public void sendMessage (String id, int idUser, int idRobot, String message) throws IOException {
        System.out.println("Envoi d'une nouvelle requete <<"+id+">> au client");

        JsonObject json = new JsonObject();
        json.addProperty("id", id);

        JsonObject content = new JsonObject();
        content.addProperty("idUser", idUser);
        content.addProperty("idRobot", idRobot);
        content.addProperty("message", message);
        content.addProperty("confirm", false);

        json.add("content", content);
        this.session.getBasicRemote().sendText(json.toString());
    }

    public void sendMessage (String id, List<UsersRobots> list) throws IOException { // TODO : pas besoin de list
        System.out.println("Envoi de la list des associations de user et client");

        JsonObject json = new JsonObject();
        json.addProperty("id", id);

        JsonArray content = new JsonArray();

        for(UsersRobots usersRobots: list){
            JsonObject object = new JsonObject();
            object.addProperty("idUser", usersRobots.getIdUser());
            object.addProperty("idRobot", usersRobots.getIdRobot());
            content.add(object);
        }
        json.add("content", content);
        this.session.getBasicRemote().sendText(json.toString());
    }

    public static synchronized  int getOnlineCount (){
        return MyWebSocket.onlineCount;
    }

    public static synchronized void addOnlineCount (){
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount (){
        MyWebSocket.onlineCount--;
    }

    public static ArrayList<MyWebSocket> getWebSocketSet() {
        return webSocketSet;
    }
}
