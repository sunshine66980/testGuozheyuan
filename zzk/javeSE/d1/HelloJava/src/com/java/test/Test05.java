package com.java.test;

public class Test05 {
    public static void main(String[] args) {
        int a = 10;
        {
            a = 20;
            System.out.println(a);
        }

        // 片段2
        int b = 20;
        {

            System.out.println(b);
        }

        b = 30;

        System.out.println(b);
    }
}
