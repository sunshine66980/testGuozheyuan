package com.geekaca.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

//服务端
public class ServerChat {
//    socket：用户名 的Map
    public static Map<Socket, String> onlineSocketMap = new HashMap<>();
//    线程池存储序列
    private static ArrayBlockingQueue watiQueue = new ArrayBlockingQueue(1);

    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(1, 2, 10,
                TimeUnit.SECONDS, watiQueue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {
            ServerSocket serverSocket = new ServerSocket(ChatConstants.PORT);
            System.out.println("端口" + ChatConstants.PORT);
            while (true) {
//                程序停在这一行
                Socket socket = serverSocket.accept();
                ServerRunnable serverRunnable = new ServerRunnable(socket);
//                new Thread(serverRunnable).start();
//                线程池
                threadPool.execute(new Thread(serverRunnable));
            }
        } catch (IOException e) {
            System.out.println("发生异常" + e.getMessage());
        }
    }
}
