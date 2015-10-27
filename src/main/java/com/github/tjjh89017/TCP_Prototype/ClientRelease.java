package com.github.tjjh89017.TCP_Prototype;


import java.io.IOException;
import java.util.TimerTask;

public class ClientRelease extends TimerTask{

    private final static String RELEASE = "RELEASE";

    private Client client;
    private Treasure[] treasures;

    public ClientRelease(Client client){

        this.client = client;
        this.treasures = client.getTreasures();
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < treasures.length; i++) {
                synchronized (treasures[i]) {
                    // Release
                    int time = treasures[i].getTime() - 1;
                    treasures[i].setTime(time);
                    if (time == 0) {
                        System.out.println("re");
                        treasures[i].setStatus(null);
                        client.send(RELEASE + " " + IntToString(i) + "\n");
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
