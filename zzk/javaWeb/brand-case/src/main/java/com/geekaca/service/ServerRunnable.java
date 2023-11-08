package com.geekaca.service;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;
import java.net.Socket;
import java.util.Collection;

public class ServerRunnable implements Runnable {
    private Socket clientSocket;

    public ServerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (InputStream ips = clientSocket.getInputStream();
             DataInputStream dis = new DataInputStream(ips);
        ) {
            while (true) {
                int msgType = dis.readInt();
                switch (msgType) {
                    case ChatConstants.MSG_TYPE_LOGIN:
                        String nickName = dis.readUTF();
                        System.out.println("login:" + clientSocket.getRemoteSocketAddress() + " nick:" + nickName);
                        ServerChat.onlineSocketMap.put(clientSocket, nickName);
//                        广播
                        StringBuilder stringBuilder = new StringBuilder();
                        Collection<String> userNickNames = ServerChat.onlineSocketMap.values();
                        for (String name : userNickNames) {
                            stringBuilder.append(name + ChatConstants.SPILIT);
                        }
//                        去掉末尾分隔符
                        String str = stringBuilder.substring(0, stringBuilder.lastIndexOf(ChatConstants.SPILIT));
                        sendMsgToAll(str);
                        break;
                    case ChatConstants.MSG_TYPE_CHAT:
                        String msg = dis.readUTF();
                        String userNickName = ServerChat.onlineSocketMap.get(clientSocket);
                        System.out.println("昵称" + userNickName + ":" + msg);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("异常" + e.getMessage());
            e.printStackTrace();
        }

    }

    void sendMsgToAll(String str) {
        ServerChat.onlineSocketMap.forEach((socket, nickName) -> {
            try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream())) {
                System.out.println("服务端准备发送广播");
                dos.writeInt(ChatConstants.MSG_TYPE_LOGIN);
                dos.writeUTF(str);
                dos.flush();
                System.out.println("服务端发送广播完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
