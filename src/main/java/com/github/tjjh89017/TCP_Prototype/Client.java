package com.github.tjjh89017.TCP_Prototype;



import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Client extends TimerTask{

    private final static int TREASURES_NUM = 3;
    private final static String YES = "YES";
    private final static String NO = "NO";
    private final static String GET = "GET";
    private final static String RELEASE = "RELEASE";
    private final static int TIMEOUT = 5;
    private final static int DELAY_GET = 1000;
    private final static int DELAY_RELEASE = 1000;
    private final static int DELAY_PRINT = 3000;
    private final static int DELAY = 1000;

    private int id = 0;
    private String name = null;
    private String host = "127.0.0.1";
    private int port = 5566;

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    private Treasure[] treasures = new Treasure[TREASURES_NUM];
    private ClientGet clientGet;
    private ClientRelease clientRelease;

    private int time = 0;
    private Timer timer = new Timer();
    private Timer timerGet = new Timer();
    private Timer timerRelease = new Timer();
    private Timer timerPrint = new Timer();

    public Client(){

        this(0, null);
    }

    public Client(int id, String name){

        this.id = id;
        this.name = name;

        for(int i = 0; i < TREASURES_NUM; i++){
            treasures[i] = new Treasure();
        }
    }

    /*@Override
    public void run() {

        for(int i = 0; i < TREASURES_NUM; i++){
            synchronized (treasures[i]){
                System.out.print(IntToString(i) + " ");
                if(treasures[i].getStatus() != null)
                    System.out.println(YES + " " + treasures[i].getTime());
                else
                    System.out.println("NO");
            }
        }
    }*/

    @Override
    public void run() {
        //System.out.println("Client RUN");

        for (int i = 0; i < TREASURES_NUM; i++) {
            System.out.print(IntToString(i) + " ");
            if (treasures[i].getStatus() != null)
                System.out.println(YES + " " + treasures[i].getTime());
            else
                System.out.println("NO");
        }

    }


    public void setHost(String host, int port){

        this.host = host;
        this.port = port;
    }

    public void connect() throws IOException{

        socket = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        clientGet = new ClientGet(this);
        clientRelease = new ClientRelease(this);

        //timer.schedule(this, 0, DELAY);
        timerPrint.schedule(this, 0, DELAY_PRINT);
        timerGet.schedule(clientGet, 0, DELAY_GET);
        timerRelease.schedule(clientRelease, 0, DELAY_RELEASE);
    }

    public synchronized void send(String msg) throws IOException{

        out.write(msg);
        out.flush();
    }

    public synchronized String read() throws IOException{

        return in.readLine();
    }

    public void close() throws IOException{

        socket.close();
    }

    public Treasure[] getTreasures(){

        return treasures;
    }

    private int StringToInt(String string){

        return string.charAt(0) - 'A';
    }

    private String IntToString(int i){

        return "" + (char)('A' + i);
    }
}
