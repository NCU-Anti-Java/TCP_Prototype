package com.github.tjjh89017.TCP_Prototype;


import io.netty.channel.ChannelHandlerContext;

public class ServerEvent {

    private final String GET = "GET";
    private final String RELEASE = "RELEASE";
    private final int TREASURE_NUM = 3;

    private Treasure[] treasures = new Treasure[TREASURE_NUM];

    public ServerEvent(){

        for(int i = 0; i < TREASURE_NUM; i++)
            treasures[i] = new Treasure();
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
}
