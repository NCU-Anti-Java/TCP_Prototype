package com.github.tjjh89017.TCP_Prototype;


public class Client {

    private int id = 0;
    private String name = null;
    private String host = "127.0.0.1";
    private int port = 5566;

    public Client(){


    }

    public Client(int id, String name){

        this.id = id;
        this.name = name;
    }

    public void setHost(String host, int port){

        this.host = host;
        this.port = port;
    }

    public void connect() throws InterruptedException{

       
    }

    public void close() {

    }
}
