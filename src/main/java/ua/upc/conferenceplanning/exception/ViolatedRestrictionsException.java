package ua.upc.conferenceplanning.exception;

public class ViolatedRestrictionsException extends RuntimeException{
    public ViolatedRestrictionsException(String message) {
        super(message);
    }
}
