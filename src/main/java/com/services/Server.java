package com.services;

import com.webSocket.MyWebSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread{
    private final int PORT = 60372;



    public static Socket androidSocket;
    private ServerSocket webSocket;
    private BufferedReader in;
    public static PrintWriter out;


    public Server() { this.start(); }

    /************************************************************************
     *	        		                  RUN               				*
     ************************************************************************/

    public void run() {

        try {

            webSocket = new ServerSocket(PORT);
            System.out.println("Serveur: en attente de connexion d'un joueur sur le port " + PORT);

            androidSocket = webSocket.accept();
            System.out.println("Nouvelle connexion au serveur.");

            this.in = new BufferedReader(new InputStreamReader(androidSocket.getInputStream()));
            this.out = new PrintWriter(androidSocket.getOutputStream());


            while (true){
                String[] req = {};
                String msg;

                msg = in.readLine();
                System.out.println("msg : " + msg);

                if (msg != null) { // Vérifie qu'on recoit pas null en cas de deconnection.

                    req = msg.split("/");
                    if (req.length != 0) {

                        switch (req[0]) {

                            case "ASSOC":
                                if (!MyWebSocket.getWebSocketSet().isEmpty()) {
                                    //MyWebSocket.getWebSocketSet().get(0).//TODO plusieurs clients
                                    ArrayList<MyWebSocket> webSockets = MyWebSocket.getWebSocketSet();
                                    for(MyWebSocket webSocket: webSockets) {

                                        webSocket.sendMessage(req[0], Integer.valueOf(req[1]).intValue(), Integer.valueOf(req[2]).intValue(), "Do you want to associate this robot (ID : " + req[2] + " )");
                                    }
                                }
                                break;

                            default:

                                System.out.println("TraitementMessage: COMMANDE IGNOREE");
                                break;
                        }

                    }
                } else {
                    System.out.println("arrêt de l' autre serveur");
                    break;
                }

            }

        }catch (IOException e) {

            try {
                webSocket.close();
                System.out.println("Arret client");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }finally {

            try {
                webSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    public int getPORT() { return PORT; }

}
