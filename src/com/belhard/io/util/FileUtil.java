package com.belhard.io.util;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FileUtil {
    public static final String LOG_FILENAME = "LOG.txt";

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

    public static void copyFile(String fileNameString, String target) throws IOException {
        Path newPath = Paths.get(target);
        if (!Files.exists(newPath)) {
            System.out.println("Directory will be created: " + newPath);
        }
        Path sourcePathAndFile = Paths.get(fileNameString);
        Path sourceFile = Paths.get(fileNameString).getFileName();
        Path newPathAndFile = Paths.get(target + sourceFile);
        Files.copy(sourcePathAndFile, newPathAndFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
    }

    public static List<File> getFilesInDirectory(String directory, String regexp) {
        File path = new File(directory);
        if (!path.exists()) {
            throw new RuntimeException("No directory found - " + directory);
        }
        List<File> outputList = new ArrayList<>(0);
        FilenameFilter filter = (dir, name) -> name.matches(regexp);
        File[] allFilesArray = path.listFiles(filter);
        if (allFilesArray.length == 0) {
            throw new RuntimeException("There is no files with name regexp " + regexp);
        }
        for (File file : allFilesArray) {
            if (file.isFile()) {
                outputList.add(file);
            }
        }
        System.out.println("There is " + outputList.size() + " (" + regexp + ")-files in folder " + path + ". ");
        return outputList;
    }

    public static void copyFiles(String fileNameRegexp, String from, String to) {
        long startTime = System.currentTimeMillis();
        List<File> allFiles = getFilesInDirectory(from, fileNameRegexp);
        StringBuilder logContent = new StringBuilder("Copying progress: \n");
        for (File file : allFiles) {
            System.out.println("Copying file: " + file.toString());
            try {
                copyFile(file.toString(), to);
            } catch (IOException e) {
                System.out.println("Can not copy file " + file + ". \n\t" + e);
                logContent.append(String.format("ERROR: %25.25s (%.1f Kb) can not copy to [%s] at %s; \n", file, file.length() / 1024.0, to, LocalDateTime.now()));
            }
            logContent.append(String.format("%32.32s (%.1f Kb) copied to [%s] at %s; \n", file.getName(), file.length() / 1024.0, to, LocalDateTime.now()));
        }
        System.out.println("Copying finished!");
        logContent.append(String.format("Elapsed time %.3g seconds. \n", (System.currentTimeMillis() - startTime) / 1000.0));
        saveToFile(logContent.toString(), to + LOG_FILENAME);
    }
}