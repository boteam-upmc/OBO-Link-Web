package com.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server extends Thread{
    public final int PORT = 60370;

    private Socket androidSocket;
    private ServerSocket webSocket;
    private BufferedReader in;
    private PrintWriter out;


    public Server(){

        this.start();
    }

    /************************************************************************
     *	        		                  RUN               				*
     ************************************************************************/

    public void run(){

        try {
            webSocket = new ServerSocket(PORT);
            System.out.println("Serveur: en attente de connexion d'un joueur sur le port "+ PORT);

            androidSocket = webSocket.accept();
            System.out.println("Nouvelle connexion au serveur.");

            this.in = new BufferedReader(new InputStreamReader(androidSocket.getInputStream()));
            this.out = new PrintWriter(androidSocket.getOutputStream());

            while(true){

                String[] req = {};
                String msg;

                msg = in.readLine();
                //if(msg.endsWith("/")){ /* toutes les requetes se terminent par un / */
                req = msg.split("/");
                switch (req[0]) {
                    case "ValidationID":
                        // envoi client
                        out.println("ID-ROBOT:TRUE");
                        break;
                    default:
                        System.out.println("TraitementMessage: COMMANDE IGNOREE");
                        break;
                }
                        // }else System.out.println("run: REQUETE IGNOREE: une requete se termine par un /");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
