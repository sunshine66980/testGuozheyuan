package com.geekaca.thread;

import java.util.Scanner;

public class HelloThread {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        ShowThread showThread = new ShowThread();
        myThread.start();
        showThread.start();
        Scanner scanner = new Scanner(System.in);
        scanner.next();
    }

}
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
                System.out.println("休息");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
