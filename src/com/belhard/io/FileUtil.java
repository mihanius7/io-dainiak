package com.belhard.io;

import java.io.*;

public class FileUtil {
    public static String getContentFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileName)) {
            char[] buffer = new char[256];
            int charNumber;
            while ((charNumber = fileReader.read(buffer)) != -1) {
                content.append(new String(buffer, 0, charNumber));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void saveToFile(String content, String fileName) {
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
