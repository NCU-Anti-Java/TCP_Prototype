package com.github.tjjh89017.TCP_Prototype;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

    private String host = "127.0.0.1";
    private int port = 5566;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workGroup;
    private ServerBootstrap serverBootstrap;

    private ServerEvent serverEvent = new ServerEvent();


    public Server(){


    }

    public Server(String host, int port){

        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {

        bossGroup = new NioEventLoopGroup();
        workGroup = new NioEventLoopGroup();

        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);

        serverBootstrap.childHandler(new RPGServerInitializer(serverEvent));

        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
        channelFuture.channel().closeFuture().sync();
    }

    public void close( ){

        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();

    }
}
