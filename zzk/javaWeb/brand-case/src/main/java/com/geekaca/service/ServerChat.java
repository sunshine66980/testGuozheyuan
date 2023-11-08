package com.geekaca.service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

//服务端
public class ServerChat {
    public static Map<Socket,String> onlineSocketMap = new HashMap<>();
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(ChatConstants.PORT);
            System.out.println(ChatConstants.PORT);
            while (true) {
//                程序停在这一行
                Socket socket = serverSocket.accept();
                ServerRunnable serverRunnable = new ServerRunnable(socket);
                new Thread(serverRunnable).start();
            }
        }catch (IOException e){
            System.out.println("发生异常"+e.getMessage());
        }
    }
}
