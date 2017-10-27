package com.webSocket;

import com.domain.AssociationUsersRobots;
import com.repository.AssociationRepository;
import com.services.Server;
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
import java.io.PrintWriter;
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
    ArrayList<String> association = new ArrayList<>();
    @OnMessage
    public void onMessage (String message, Session session) throws IOException {
        System.out.println("From client:" + message);
        if(Boolean.valueOf(message).booleanValue()){ // TODO : a modif equals
            //System.out.println("TEST");
            association.add(String.valueOf(idr));
            String envoieID = "";
            for(int i = association.size()-1; 0<=i  ;i--)
               envoieID += association.get(i)+"/";
            this.session.getBasicRemote().sendText("assoc/"+envoieID);
            //this.session.getBasicRemote().sendText("assoc/"+association.toString());
            Server.out.print("VALID/TRUE\r");
            Server.out.flush();
            System.out.println("Envoyer");
        }else{
            Server.out.print("VALID/FALSE\r");
            Server.out.flush();
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
        this.session.getBasicRemote().sendText("validID/"+message);
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
