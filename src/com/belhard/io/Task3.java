package com.belhard.io;

import com.belhard.io.util.FileUtil;

public class Task3 {
    public static void main (String args[]) {
        FileUtil.copyFiles(".*\\.jpeg|.*\\.jpg", "resources\\in\\", "resources\\out\\nio\\");
    }
}
