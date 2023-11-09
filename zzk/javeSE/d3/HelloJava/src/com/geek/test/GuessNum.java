package com.geek.test;

import java.util.Random;
import java.util.Scanner;

public class GuessNum {
    private static int maxGuess = 3;
    public static void main(String[] args) {
        Random random = new Random();
        int num = random.nextInt(100);
        System.out.println(num);
        Scanner sc = new Scanner(System.in);
        int count = 0;
        do {
            if (count == maxGuess){
                System.out.println("猜测次数用完.");
                break;
            }
            System.out.println("输入一个数");
            int guessNum = sc.nextInt();
            count++;
            if (guessNum == num){
                System.out.println("猜对了!");
                break;
            }else {
                System.out.println("猜错了!");
            }
        }while (true);

    }
}
