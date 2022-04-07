package com.belhard.io;

import com.belhard.io.util.FileUtil;

import java.io.File;

public class Task2 {
    public static void main(String[] args) {
        int[] arr = FileUtil.getBytesContentFromFile("resources\\in\\my-code-dog-standing-on-cans-indian-guys-on-youtube-stack-overflow-luck-online-forums-from-2008.jpg");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
            if (i % 30 == 0 && i != 0) {
                System.out.println();
            }
        }
        FileUtil.saveToFile(arr, "resources\\out\\my-code-dog-standing-on-cans-indian-guys-on-youtube-stack-overflow-luck-online-forums-from-2008.jpg");
    }
}
