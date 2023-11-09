package com.geekaca.chat.io;

import java.io.*;

public class ReviewReadWriter {
    public static void main(String[] args) {
        File file = new File("D:\\tomcatLog.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file));) {
            String line =null;
            while ((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
