package com.github.tjjh89017.TCP_Prototype;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class ClientEvent {

    private final int TREASURE_NUM = 3;
    private final String YES = "YES";
    private final String NO = "NO";

    private Treasure[] treasures = new Treasure[TREASURE_NUM];
    private Channel channel;

    public void processing(ChannelHandlerContext ctx, String msg){

        System.out.println("ClientEvent, msg: " + msg);
        // TODO split processing and parsing
        String[] result = msg.split(" ");
        if(result[0].equals(YES)){
            treasures[StringToInt(result[1])] = new Treasure();
        }
    }

    private int StringToInt(String string){

        return string.charAt(0) - 'A';
    }
}
