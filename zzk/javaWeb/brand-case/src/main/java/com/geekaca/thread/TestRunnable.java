package com.geekaca.thread;

public class TestRunnable {
    public static void main(String[] args) {
        //TestRunnable2 testRunnable2 = new TestRunnable2();
        new Thread(new TestRunnable2(),"xx01").start();
    }

}



class TestRunnable2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis());
        }
    }
}
