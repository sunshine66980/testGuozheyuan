package com.geek.test;

public class WaterFlower {
    public static void main(String[] args) {
        System.out.println("水仙花数:");
        int count = 0;
        for (int i = 1; i <9 ; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if (Math.pow(i,3) + Math.pow(j,3) + Math.pow(k,3) == i+j*10+k*100){
                        System.out.println(i+j*10+k*100);
                        count++;
                    }
                }
            }
        }
        System.out.println("水仙花数有"+ count + "个");
    }
}
