package com.github.tjjh89017.TCP_Prototype;


import io.netty.channel.ChannelHandlerContext;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;


public class ServerEvent implements TimerTask{

    private final static String YES = "YES";
    private final static String NO = "NO";
    private final static String GET = "GET";
    private final static String RELEASE = "RELEASE";
    private final static int TREASURE_NUM = 3;
    private final static int DELAY = 3;

    private Treasure[] treasures = new Treasure[TREASURE_NUM];
    private Timer timer = new HashedWheelTimer();

    public ServerEvent(){

        for(int i = 0; i < TREASURE_NUM; i++)
            treasures[i] = new Treasure();

        timer.newTimeout(this, DELAY, TimeUnit.SECONDS);
    }

    @Override
    public void run(Timeout timeout) throws Exception {

        String status = null;
        for(int i = 0; i < TREASURE_NUM; i++){
            status = treasures[i].getStatus();
            System.out.print(IntToString(i) + " ");
            if(status != null)
                System.out.println(YES + " " + status);
            else
                System.out.println(NO + " 0");

        }
        timer.newTimeout(this, DELAY, TimeUnit.SECONDS);
    }

    public void processing(ChannelHandlerContext ctx, String msg){

        // TODO split processing and parsing
        String[] result = msg.split(" ");
        int id = StringToInt(result[1]);
        System.out.println(ctx.toString());


        synchronized (this) {
            if (result[0].equals(GET)) {
                // check if it could
                // TODO fix it
                if(treasures[id].getStatus() == null){
                    treasures[id].setStatus(ctx.toString());

                    ctx.writeAndFlush("YES " + result[1]);
                }
                else{
                    ctx.writeAndFlush("NO " + result[1]);
                }
            } else if(result[0].equals(RELEASE)) {
                treasures[id].setStatus(null);
            }
        }
    }

    private int StringToInt(String string){

        return string.charAt(0) - 'A';
    }

    private String IntToString(int i){

        return "" + (char)('A' + i);
    }


}
