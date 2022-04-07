package com.belhard.io.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static String getTextContentFromFile(String fileName) {
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
        Path path = Paths.get(fileName).getParent();
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int[] getBytesContentFromFile(String fileName) {
        List<Integer> bytesList = new ArrayList<>();
        try (FileInputStream input = new FileInputStream(fileName)) {
            int readByte = 0;
            while ((readByte = input.read()) != -1) {
                bytesList.add(readByte);
            }
        } catch (IOException openingException) {
            openingException.printStackTrace();
        }
        int[] bytesArray = new int[bytesList.size()];
        for (int i = 0; i < bytesArray.length; i++) {
            bytesArray[i] = bytesList.get(i);
        }
        System.out.println("file size: " + bytesArray.length);
        return bytesArray;
    }

    public static void saveToFile(int[] content, String fileName) {
        File file = new File(fileName);
        Path path = Paths.get(fileName).getParent();
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            FileOutputStream output = new FileOutputStream(file);
            for (int i = 0; i < content.length; i++) {
                output.write(content[i]);
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
