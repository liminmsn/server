package org.example;

import org.example.module.SocketServer;
import org.java_websocket.WebSocket;
import com.google.gson.Gson;

import java.net.UnknownHostException;


public class SocketMain extends SocketServer {
    public SocketMain(int port) throws UnknownHostException {
        super(port);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        Gson gson = new Gson();
        try {
            CommJson commJson = gson.fromJson(s, CommJson.class);
            broadcast(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class CommJson {
        public String name;
        public String msg;
    }
}