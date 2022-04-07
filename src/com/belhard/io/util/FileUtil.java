package com.belhard.io.util;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
        long startTime = System.currentTimeMillis();
        List<Integer> bytesList = new ArrayList<>(0);
        try (FileInputStream input = new FileInputStream(fileName)) {
            int readByte;
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
        System.out.printf("File size: %d bytes. \n", bytesArray.length);
        System.out.printf("\tReading time: %.3g seconds. \n", (System.currentTimeMillis() - startTime) / 1000.0);
        return bytesArray;
    }

    public static int defineFileSize(String fileName) {
        List<Integer> bytesList = new ArrayList<>(0);
        try (FileInputStream input = new FileInputStream(fileName)) {
            int readByte;
            while ((readByte = input.read()) != -1) {
                bytesList.add(readByte);
            }
        } catch (IOException openingException) {
            openingException.printStackTrace();
        }
        return bytesList.size();
    }

    public static void saveToFile(int[] content, String fileName) {
        long startTime = System.currentTimeMillis();
        File file = new File(fileName);
        Path path = Paths.get(fileName).getParent();
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            FileOutputStream output = new FileOutputStream(file);
            for (int j : content) {
                output.write(j);
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("\tWriting time: %.3g seconds. \n", (System.currentTimeMillis() - startTime) / 1000.0);
    }

    public static void copyFile(String fileName, String to) {
        int[] content = getBytesContentFromFile(fileName);
        Path newPath = Paths.get(to);
        if (!Files.exists(newPath)) {
            System.out.println("Directory will be created: " + newPath);
        }
        String targetFileName = to + Paths.get(fileName).getFileName();
        saveToFile(content, targetFileName);
    }

    public static void copyFiles(String fileNameRegexp, String from, String to) {
        List<File> allFiles = getFiles(from, fileNameRegexp);
        for (File file : allFiles) {
            System.out.println("Copying file: " + file.toString());
            copyFile(file.toString(), to);
        }
        System.out.println("Copying completed!");
    }

    public static List<File> getFiles(String directory, String regexp) {
        List<File> outputList = new ArrayList<>(0);
        File path = new File(directory);
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.matches(regexp)) {
                    return true;
                }
                    return false;
            }
        };
        List<File> allFilesList = Arrays.asList(path.listFiles(filter));
        for (File file : allFilesList) {
            if (file.isFile()) {
                outputList.add(file);
            }
        }
        System.out.println("files cnt in folder '" + path + "': " + outputList.size());
        return outputList;
    }
}