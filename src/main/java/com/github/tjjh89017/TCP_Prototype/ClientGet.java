package com.github.tjjh89017.TCP_Prototype;


import java.io.IOException;
import java.util.TimerTask;

public class ClientGet extends TimerTask{

    private final static String GET = "GET";
    private final static String RELEASE = "RELEASE";
    private final static String YES = "YES";
    private final static int TIMEOUT = 5;

    private Client client;
    private Treasure[] treasures;
    private int time = 0;

    public ClientGet(Client client){

        this.client = client;
        this.treasures = client.getTreasures();
    }

    @Override
    public void run() {

        System.out.println("Cleint GET " + time);
        try {
            //for (int i = 0; i < treasures.length; i++) {
            time = (time + 1) % 3;
            synchronized (treasures[time]) {
                String status = treasures[time].getStatus();
                if (status == null) {
                    // get
                    client.send(GET + " " + IntToString(time) + "\n");
                    if (client.read().contains(YES)) {
                        System.out.println("get");
                        treasures[time].setStatus(YES);
                        treasures[time].setTime(TIMEOUT);
                    }
                }
            }
        }
        catch (IOException e){
            // do nothing
        }
    }

    private String IntToString(int i){

        return "" + (char)('A' + i);
    }
}
