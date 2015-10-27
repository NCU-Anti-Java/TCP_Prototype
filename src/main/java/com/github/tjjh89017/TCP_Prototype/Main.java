package com.github.tjjh89017.TCP_Prototype;


import java.io.IOException;

public class Main{

    public static void main(String[] args) throws IOException{

        //System.out.println("Hello World!");

        if(args.length < 1)
            return;


        Server server = null;
        Client client = null;
        try {
            if (args[0].charAt(0) == 's') {
                server = new Server();
                server.start();
            }
            else if (args[0].charAt(0) == 'c') {
                client = new Client();
                client.connect();
            }
        }
        catch (InterruptedException e){
            if(server != null){

            }
            else if(client != null){
                client.close();
            }
        }

    }
}
