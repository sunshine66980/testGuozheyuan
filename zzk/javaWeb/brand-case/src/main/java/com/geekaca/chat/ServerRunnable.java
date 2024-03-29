package com.geekaca.chat;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

public class ServerRunnable implements Runnable {
    private Socket clientSocket;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public ServerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            InputStream ips = clientSocket.getInputStream();
//            自定义协议，按顺序读写
            DataInputStream dis = new DataInputStream(ips);
            while (true) {
                int msgType = dis.readInt();
                switch (msgType) {
                    case ChatConstants.MSG_TYPE_LOGIN:
                        String nickName = dis.readUTF();
                        System.out.println("login:" + clientSocket.getRemoteSocketAddress() + " nick:" + nickName);
                        ServerChat.onlineSocketMap.put(clientSocket, nickName);
                        //                        给线程名字
                        Thread currentThread = getCurrentThread();
                        currentThread.setName(nickName);
                        System.out.println(currentThread.getName() + "启动");
//                        广播
                        StringBuilder stringBuilder = new StringBuilder();
                        Collection<String> userNickNames = ServerChat.onlineSocketMap.values();
                        for (String name : userNickNames) {
                            stringBuilder.append(name + ChatConstants.SPILIT);
                        }
//                        去掉末尾分隔符
                        String str = stringBuilder.substring(0, stringBuilder.lastIndexOf(ChatConstants.SPILIT));
                        sendMsgToAll(str, ChatConstants.MSG_TYPE_LOGIN);

                        break;
                    case ChatConstants.MSG_TYPE_CHAT:
                        String msg = dis.readUTF();
                        String userNickName = ServerChat.onlineSocketMap.get(clientSocket);
                        StringBuilder userMessage = new StringBuilder();
                        userMessage.append(LocalDateTime.now().format(dtf)).append(" ").append(userNickName).append("\n").append(msg).append("\n");
                        System.out.println(userMessage);
                        sendMsgToAll(userMessage.toString(), ChatConstants.MSG_TYPE_CHAT);
                        break;
                    case ChatConstants.MSG_TYPE_PRIVATE:
                        String toNickName = dis.readUTF();
                        String content = dis.readUTF();
                        sendToOne(toNickName, content);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("异常" + e.getMessage());
            e.printStackTrace();
        }

    }

    //私聊
    private void sendToOne(String toNickName, String content) {
        String sendNickName = ServerChat.onlineSocketMap.get(clientSocket);
        ServerChat.onlineSocketMap.forEach((socket, nickName) -> {
            if (nickName.equals(toNickName)) {
                try {
                    Thread currentThread = getCurrentThread();
                    System.out.println(currentThread.getName() + "发送私聊");
                    OutputStream ops = socket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(ops);
                    dos.writeInt(ChatConstants.MSG_TYPE_PRIVATE);
                    StringBuilder sb = new StringBuilder();
                    sb.append(LocalDateTime.now().format(dtf)).append(" ").append(sendNickName).append("对你说").append("\n").append(content).append("\n");
                    dos.writeUTF(sb.toString());
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void sendMsgToAll(String str, int type) {
        ServerChat.onlineSocketMap.forEach((socket, nickName) -> {
            try {
                Thread currentThread = getCurrentThread();
                System.out.println(currentThread.getName() + "发送消息");
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                System.out.println("服务端准备发送广播");
                dos.writeInt(type);
                dos.writeUTF(str);
                dos.flush();
                System.out.println("服务端发送广播完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Thread getCurrentThread(){
        return Thread.currentThread();
    }
}
