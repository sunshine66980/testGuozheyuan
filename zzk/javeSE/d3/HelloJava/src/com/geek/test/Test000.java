package com.geek.test;

public class Test000 {

    public static void main(String[] args) {
//        有无空调
        boolean airConditioner = false;
//        有无wifi
        boolean wifi = false;
//        没空调就走
        if (airConditioner && true) {
            System.out.println("留下");
        }else {
            System.out.println("走了");

        }
//        要么有空调或者有wifi
        if (airConditioner || wifi) {
            System.out.println("留下");
        }else {
            System.out.println("走了");
        }
    }
}
