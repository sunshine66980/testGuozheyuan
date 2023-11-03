package com.geek.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Test00 {

    public static void main(String[] args) {

//
        int num1 = 100;
        int num2 = 101;
        int num3 = 10;
        int max = 0;
        max = num1 > num2 ? num1 : num2;
        max = num3 > max ? num3 : max;
        System.out.println("最大值="+max);



    }
}
