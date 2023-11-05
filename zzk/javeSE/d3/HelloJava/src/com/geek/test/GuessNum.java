package com.geek.test;

import java.util.Scanner;

public class GuessNum {
    public static void main(String[] args) {
        int num = 76;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("输入一个数");
            int guessNum = sc.nextInt();
            if (guessNum == num){
                System.out.println("猜对了!");
                break;
            }else {
                System.out.println("猜错了!");
            }
        }while (true);

    }
}
