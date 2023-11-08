package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MyFrame extends JFrame {
    public MyFrame() {
//        URL imgUrl = MyFrame.class.getResource("backImg.png");
//        ImageIcon imageIcon = new ImageIcon();
//        JLabel label = new JLabel("确认");
//        label.setIcon(imageIcon);
//        label.setHorizontalAlignment(SwingConstants.HORIZONTAL);
//        Container container = getContentPane();
//        container.add(label);

        JTextField textField = new JTextField();
        JButton btn = new JButton("确认");
        JPasswordField passwordField = new JPasswordField();
        passwordField.setEchoChar('*');

        Container container = getContentPane();
        container.add(btn,BorderLayout.SOUTH);
        container.add(textField,BorderLayout.NORTH);
        container.add(passwordField);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                点击事件
                String text = textField.getText();
                String passWord = passwordField.getText();
                System.out.println(text);
                System.out.println(passWord);
            }
        });
        setVisible(true);
        setBounds(277, 277, 500, 400);

    }
}
