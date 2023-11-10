package com.geekaca.thread.safe;

public class ThreadDemo {
    public static void main(String[] args) {
        Account account = new Account(100000,"665");
        DrawThread a = new DrawThread(account,"a");
        a.setName("a");
        DrawThread b = new DrawThread(account,"b");
        b.setName("b");
        DrawThread c = new DrawThread(account,"c");
        c.setName("c");
        b.start();
        a.start();
        c.start();
    }
}
