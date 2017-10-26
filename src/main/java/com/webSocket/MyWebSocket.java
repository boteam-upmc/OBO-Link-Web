package com.webSocket;

import com.domain.AssociationUsersRobots;
import com.repository.AssociationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
@Component
public class MyWebSocket {

    private static int onlineCount = 0;

    private static ArrayList<MyWebSocket> webSocketSet = new ArrayList<>();

    private Session session;

    @Autowired
    AssociationRepository repository;

    //TODO modifier pour que appler ce methode quand le client se connecter
    @OnOpen
    public void onOpen (Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("Nouvelle client se connecter! " + getOnlineCount());
    }

    @OnClose
    public void onClose (){
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("Fermer une connextion" + getOnlineCount());
    }

    //TODO recevoir le message qui vient de "android app"
    //Envoyer au client, et du cote de client, generer un popup
    @OnMessage
    public void onMessage (String message, Session session) throws IOException {
        System.out.println("From client:" + message);
        System.out.println("idu"+idu);
        System.out.println("idu"+idr);
        if(Boolean.valueOf(message).booleanValue()){
            System.out.println("aaaa");

        }else{
           System.out.println("qqq");
        }
        // chat
       // for ( MyWebSocket item : webSocketSet ){
         //   item.sendMessage(message);
        //}
    }

    int idu;
    int idr;

    HashMap<Integer, String>  user= new HashMap <Integer, String>();
    public void sendMessage (String message, int iduser, int idrobot) throws IOException {
        this.idu=iduser;
        this.idr=idrobot;
        System.out.println("idur"+idu);
        System.out.println("idr"+idr);
        this.session.getBasicRemote().sendText(message);
        System.out.println("Nouvelle connexion au serveur");
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
