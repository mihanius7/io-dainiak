package com.belhard.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLOutput;

public class Task1 {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("resources\\in\\text.txt");
            System.out.println("File is opened!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
