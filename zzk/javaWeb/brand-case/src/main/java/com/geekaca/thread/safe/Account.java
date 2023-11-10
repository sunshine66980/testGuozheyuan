package com.geekaca.thread.safe;

public class Account {
    public double money;
    private String cardId;

    public Account(double money, String cardId) {
        this.money = money;
        this.cardId = cardId;
    }

//    public void drawMoney(double money) {
//        synchronized (this) {//开始同步块
//            String name = Thread.currentThread().getName();
//            if (this.money < money) {
//                System.out.println("余额不足");
//            } else {
//                this.money -= money;
//                System.out.println(name + "取钱");
//                System.out.println("剩余" + this.money);
//            }
//        }
//    }


    public synchronized void drawMoney(double money) {
        String name = Thread.currentThread().getName();
        if (this.money < money) {
            System.out.println("余额不足");
        } else {
            this.money -= money;
            System.out.println(name + "取钱");
            System.out.println("剩余" + this.money);

        }
    }

}
