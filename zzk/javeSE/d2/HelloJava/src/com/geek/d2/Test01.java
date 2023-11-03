package com.geek.d2;

public class Test01 {

    public static void main(String[] args) {
        double sonHeigh;
        double daughterHeigh;

        double fatherHeigh = 177;
        double motherHeigh = 165;

        sonHeigh = (fatherHeigh + motherHeigh) * 1.08 / 2;
        daughterHeigh = (fatherHeigh * 0.923 + motherHeigh) / 2;

        System.out.println("儿子身高:"+sonHeigh);
        System.out.println("女儿身高:"+daughterHeigh);

    }


}
