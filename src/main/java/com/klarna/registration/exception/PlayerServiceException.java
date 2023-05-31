package com.klarna.registration.exception;

public class PlayerServiceException extends RuntimeException{
    public PlayerServiceException(String message) {
        super(message);
    }
}
