package com.github.tjjh89017.TCP_Prototype;


public class Main{

    public static void main(String[] args) throws Exception{

        System.out.println("Hello World!");

        if(args.length < 1)
            return;

        if(args[0].charAt(0) == 's')
            new Server().start();
        else if(args[0].charAt(0) == 'c')
            new Client().connect();


    }
}
