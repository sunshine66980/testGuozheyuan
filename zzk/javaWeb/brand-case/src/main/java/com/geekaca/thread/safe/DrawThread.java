package com.geekaca.thread.safe;

public class DrawThread  extends Thread{

    private Account account;
    private String name;

    public DrawThread(Account account, String name) {
        this.account = account;
        this.name = name;
    }

    @Override
    public void run() {

            account.drawMoney(100000);


    }
}
