package com.geekaca.chat;

import java.io.*;
import java.net.Socket;

public class ClientReader implements Runnable {
    private Socket socket;
    private ClinetChat clinetChat;

    public ClientReader(Socket socket, ClinetChat clinetChat) {
        this.socket = socket;
        this.clinetChat = clinetChat;
    }

    @Override
    public void run() {

        try  {
            InputStream ips = socket.getInputStream();
            DataInputStream dis = new DataInputStream(ips);
            while (true) {
                int msgType = dis.readInt();
                switch (msgType) {
                    case ChatConstants.MSG_TYPE_LOGIN:
//                        广播
                        String allNickName = dis.readUTF();
                        String[] allNickNames = allNickName.split(ChatConstants.SPILIT);
                        clinetChat.onlineUser.setListData(allNickNames);
                        break;
                    case ChatConstants.MSG_TYPE_CHAT:
                        String userMessage = dis.readUTF();
                        clinetChat.setSmsContent(userMessage);
                        break;
                    case ChatConstants.MSG_TYPE_PRIVATE:
                        String msg = dis.readUTF();
                        clinetChat.setSmsContent(msg);
                        break;
                    default:
                        break;
                }
            }
        }  catch (IOException e){
            e.printStackTrace();
            System.out.println("异常" + e.getMessage());
        }

    }
}
