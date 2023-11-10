package com.geekaca.thread;

public class ShowThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            try {
                Thread.sleep(2000);
                System.out.println("休息2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
