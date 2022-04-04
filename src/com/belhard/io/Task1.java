package com.belhard.io;

import com.belhard.util.StringUtil;

public class Task1 {
    public static void main(String[] args) {
        String content = FileUtil.getContentFromFile("resources\\in\\text.txt");
        System.out.println("File content: ");
        System.out.println(content);

        System.out.println("Filtered file content: ");
        content = StringUtil.cleanSpaces(content);
        System.out.println(content);
    }

}
