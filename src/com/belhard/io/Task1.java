package com.belhard.io;

import com.belhard.io.util.FileUtil;
import com.belhard.io.util.StringUtil;

public class Task1 {
    public static void main(String[] args) {
        System.out.println("File content: ");
        String content = FileUtil.getContentFromFile("resources\\in\\text.txt");
        System.out.println(content);

        System.out.println("Filtered file content: ");
        content = StringUtil.cleanSpaces(content);
        System.out.println(content);

        FileUtil.saveToFile(content,"resources\\out\\formattedText.txt");
    }

}
