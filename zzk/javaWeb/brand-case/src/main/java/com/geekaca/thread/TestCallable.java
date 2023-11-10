package com.geekaca.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


public class TestCallable {
    public static void main(String[] args) throws Exception {
        Callable<String> mycall = new TestCallable2(100);
        FutureTask<String> futureTask = new FutureTask(mycall);
        Thread t1 = new Thread(futureTask);
        t1.start();
        String result = null;
        result = futureTask.get();
        System.out.println(result);
    }

}

class TestCallable2 implements Callable {

    private int num;

    public TestCallable2(int num) {
        this.num = num;
    }

    @Override
    public String call() throws Exception {
        for (int i = 0; i < num; i++) {
            System.out.println(i);
            Thread.sleep(1000);
        }
       String str = "启动: " + this.num;
        return str;
    }
}

