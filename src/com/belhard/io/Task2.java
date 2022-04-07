package com.belhard.io;

import com.belhard.io.util.FileUtil;

import java.io.File;

public class Task2 {
    public static void main(String[] args) {
        FileUtil.copyFiles(".*\\.jpeg|.*\\.jpg", "resources\\in\\", "resources\\out\\");
    }
}
