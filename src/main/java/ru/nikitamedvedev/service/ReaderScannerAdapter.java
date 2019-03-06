package ru.nikitamedvedev.service;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

@Component
public class ReaderScannerAdapter implements Reader {

    private final Scanner scanner;

    private ReaderScannerAdapter(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }


    @Override
    public String next() {
        return scanner.next();
    }

    @Override
    public Integer nextInt() {
        return scanner.nextInt();
    }
}
