package com.belhard.io;

import com.belhard.io.util.FileUtil;
import com.belhard.io.util.StringUtil;

public class Task1 {
    public static void main(String[] args) {
        String content = FileUtil.getContentFromFile("resources\\in\\text.txt");

        content = StringUtil.cleanSpaces(content);
        content = StringUtil.insertNewLines(content, 120, false);

        FileUtil.saveToFile(content,"resources\\out\\formattedText.txt");
    }

}
