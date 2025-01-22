package org.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class Socket {
    final int portNumber = 8080;
    volatile boolean run = true;
    ServerSocket serverSocket;

    public Socket() {

    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("http://localhost:" + portNumber);
            while (run) {
                java.net.Socket clientSocket = serverSocket.accept();

                try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader((new InputStreamReader(clientSocket.getInputStream())))) {
                    String inputLine;
                    while((inputLine = in.readLine()) != null){
                        System.out.print("msg:");
                        System.out.println(inputLine);

                        out.println("Server received your message: " + inputLine);
                        if(inputLine.equals("exit")){
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error handling client connection: " + e.getMessage());
                    Thread.onSpinWait();
                }
            }
        } catch (IOException e) {
            System.out.println("Error starting the server: " + e.getMessage());
        }
    }
}
