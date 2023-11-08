package com.geekaca.service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClinetChat implements ActionListener {
    private static JFrame win = new JFrame();
    public JTextArea smsContent = new JTextArea(23, 50);
    public static JList<String> onlineUser = new JList<>();
    private JTextArea smsSend = new JTextArea(4, 40);
    private JCheckBox isPrivateBn = new JCheckBox("私聊");
    private JCheckBox sendBn = new JCheckBox("发送");
    private static JFrame loginView;
    private JTextField ipEt, nameEt;
    private Socket socket;

    public static void main(String[] args) {
        ClinetChat clinetChat = new ClinetChat();

        clinetChat.displayLoginView();
    }

    public void displayLoginView() {
        loginView = new JFrame("登录");
        loginView.setLayout(new GridLayout(3, 1));
        loginView.setSize(400, 230);


        JPanel ipPanel = new JPanel();
        JLabel label = new JLabel(" IP:");
        ipPanel.add(label);
        ipEt = new JTextField(20);
        ipPanel.add(ipEt);
        loginView.add(ipPanel);


        JPanel namePanel = new JPanel();
        JLabel label1 = new JLabel("姓名");
        namePanel.add(label1);
        nameEt = new JTextField(20);
        namePanel.add(nameEt);
        loginView.add(namePanel);

        JPanel btnPanel = new JPanel();
        JButton login = new JButton("登录");
        JButton cancel = new JButton("取消");
        btnPanel.add(login);
        btnPanel.add(cancel);
        loginView.add(btnPanel);

        loginView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setWindowCenter(loginView, 400, 260, true);


        login.addActionListener(this);
        cancel.addActionListener(this);
    }
    public void displayChatView() {
        win.setSize(650,300);
        JPanel bottomPanel = new JPanel(new BorderLayout());
        win.add(bottomPanel,BorderLayout.SOUTH);
        bottomPanel.add(smsSend);
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btns.add(sendBn);
        btns.add(isPrivateBn);
        bottomPanel.add(btns,BorderLayout.SOUTH);


        smsContent.setBackground(new Color(0xdd,0xdd,0xdd));
        win.add(new JScrollPane(smsContent),BorderLayout.CENTER);
        smsContent.setEditable(false);


        Box rightBox = new Box(BoxLayout.Y_AXIS);
        onlineUser.setFixedCellHeight(120);
        onlineUser.setVisibleRowCount(13);
        rightBox.add(new JScrollPane(onlineUser));
        win.add(rightBox, BorderLayout.EAST);
        win.setVisible(true);
//        关闭窗口就退出
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        可关闭
        win.pack();
    }

    private static void setWindowCenter(JFrame frame, int width, int height, boolean flag) {
        Dimension ds = frame.getToolkit().getScreenSize();
        int width1 = ds.width;
        int height1 = ds.height;
        frame.setLocation(width1 / 2 - width / 2, height1 / 2 - height / 2);
        frame.setVisible(flag);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        switch (btn.getText()) {
            case "登录":
                String ip = ipEt.getText();
                String name = nameEt.getText();
//                验证ip是否符合要求
//                \\d{1,3}
                win.setTitle(name);
                try {
                    socket = new Socket(ip, ChatConstants.PORT);
                    // 读取服务端信息,不能while true，单线程会卡死
                    new Thread(new ClientReader(socket,this)).start();
//                    发送用户名到服务端
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeInt(ChatConstants.MSG_TYPE_LOGIN);
                    dataOutputStream.writeUTF(name);
                    dataOutputStream.flush();
//                    销毁登录
                    loginView.dispose();
                    displayChatView();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } finally {
//
                }
                break;
            case "取消":
                //                程序退出
                System.exit(0);
                break;
            case "发送":
                String chatContent = smsSend.getText();
                if (chatContent == null || "".equals(chatContent.trim())){
                    System.out.println("内容为空！");
                    return;
                }
//                打开输出流
                if (socket!=null){
                    try {
//                        不关闭socket，保持连接
                     OutputStream ops = socket.getOutputStream();
                     DataOutputStream dos = new DataOutputStream(ops);
                     dos.writeInt(ChatConstants.MSG_TYPE_CHAT);
                     dos.writeUTF(chatContent);
                     dos.flush();

                    } catch (IOException ioException) {
                        System.out.println("发生异常" + ioException);
                        ioException.printStackTrace();
                    }
                }
                break;
            default:
                break;

        }
    }
}
