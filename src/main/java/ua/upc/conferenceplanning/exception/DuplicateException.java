package ua.upc.conferenceplanning.exception;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DuplicateException extends RuntimeException {
    public DuplicateException(String msg) {
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        System.out.println(now);
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(now2);
    }
}
