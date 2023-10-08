package com.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {//throws IOException {
//        File file = new File("C:\\Users\\semen\\basejava\\.gitignore");
        String filePath = ".\\.gitignore";
        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch(IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("C:\\Users\\semen\\basejava\\src\\com\\basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : dir.list()) {
                System.out.println(name);
            }
        }
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(filePath);
//            System.out.println(fis.read());
//        } catch(IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                }catch(IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        try(FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        printDirectoryDeep(dir);
    }

    public static void printDirectoryDeep(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("     File : " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Directory : " + file.getName());
                    printDirectoryDeep(file);
                }
            }
        }
    }
}
