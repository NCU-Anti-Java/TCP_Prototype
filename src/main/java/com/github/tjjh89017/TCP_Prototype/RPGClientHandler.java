package com.github.tjjh89017.TCP_Prototype;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RPGClientHandler extends SimpleChannelInboundHandler<String> {

    private ClientEvent clientEvent;

    public RPGClientHandler(ClientEvent clientEvent){

        this.clientEvent = clientEvent;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("Client Active");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("Client Inactive");
        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        clientEvent.processing(ctx, msg);
    }
}
