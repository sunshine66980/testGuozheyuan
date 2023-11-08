package test;

import javax.swing.*;
import java.awt.*;

public class TestSwing {
    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
    }

    public static void init(){
        JFrame jFrame = new JFrame("窗口");
        jFrame.setBackground(Color.black);
        jFrame.setVisible(true);
        jFrame.setBounds(300,317,800,400);

        JLabel myLable = new JLabel("你好");
        jFrame.add(myLable);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
