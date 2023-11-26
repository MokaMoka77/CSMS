package com.khoabug.coffeshop.common.repository.exception;

/**
 * @author : khoabug
 * @created : 7/21/23, Friday
 **/
public class NotCreatException extends RuntimeException{
    public NotCreatException() {
    }

    public NotCreatException(String message) {
        super(message);
    }
}
