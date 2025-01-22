package org.example.module;

import com.google.gson.Gson;
import org.example.SocketMain;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class SocketServer extends WebSocketServer {
    protected final Set<String> msg_list = Set.of();

    public SocketServer(int port) {
        super(new InetSocketAddress(port));
    }

    //连接上
    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        SocketMain.CommJson commJson = new SocketMain.CommJson();
        commJson.name = "服务器";
        commJson.msg = "欢迎来到聊天室";
        webSocket.send(new Gson().toJson(commJson));
        System.out.println(webSocket.getRemoteSocketAddress().getAddress().getHostAddress() + " 连接上服务器;");
    }

    //关闭
    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        String msg = webSocket + "退出了房间!";
        broadcast(msg);
        System.out.println(msg);
    }

    //错误
    @Override
    public void onError(WebSocket webSocket, Exception e) {
        e.printStackTrace();
    }

    //启动
    @Override
    public void onStart() {
        System.out.println("Server started!\thttp://localhost:" + getPort());
        System.out.println("input exit quit...");
        setConnectionLostTimeout(0);
        setConnectionLostTimeout(100);
    }
}
