package com.ledgerflow.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RememberService {

    private static String remember;

    public static void CreateRemember() throws IOException {
        Path path = Paths.get("./remember.txt");

        if(Files.notExists(path)){
            Files.createFile(path);
        }

        Files.write(path, remember.getBytes());

    }

    public static String Remember() throws IOException {
        Path path = Paths.get("./remember.txt");

        if(Files.exists(path)) {
            return remember = Files.readString(path);
        }

        return null;
    }
}
