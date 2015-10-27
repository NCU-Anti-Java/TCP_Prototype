package com.github.tjjh89017.TCP_Prototype;

import java.net.InetAddress;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class RPGServerHandler extends SimpleChannelInboundHandler<String>{

    private ServerEvent serverEvent;

    public RPGServerHandler(ServerEvent serverEvent){

        this.serverEvent = serverEvent;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        //System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);

        serverEvent.processing(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //System.out.println("RemoteAddress : " + ctx.channel().remoteAddress() + " active !");
        //ctx.writeAndFlush( "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\n");

        super.channelActive(ctx);
    }
}
