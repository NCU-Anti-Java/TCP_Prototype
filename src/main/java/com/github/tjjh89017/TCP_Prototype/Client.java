package com.github.tjjh89017.TCP_Prototype;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.Channel;


public class Client {

    private int id = 0;
    private String name = null;
    private String host = "127.0.0.1";
    private int port = 5566;

    private EventLoopGroup eventLoopGroup;
    private Channel channel;
    private Bootstrap bootstrap;

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

        eventLoopGroup = new NioEventLoopGroup();

        try{
            bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new PRGClientInitializer());

            channel = bootstrap.connect(host, port).sync().channel();

            // send here
            channel.writeAndFlush("Here is A\n");

            // wait server msg recv
            Thread.sleep(100);
        }
        finally {
            eventLoopGroup.shutdownGracefully();
        }
    }


}
