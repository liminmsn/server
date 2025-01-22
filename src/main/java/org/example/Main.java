package org.example;

import org.example.module.SocketServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        //创建websocket实列
        SocketMain socket = new SocketMain(8088);
        //启动socket服务
        socket.start();

        BufferedReader sys_in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sys_in.readLine();
            socket.broadcast(in);
            if (in.equals("exit")) {
                socket.stop(1000);
                break;
            }
        }
    }
}