package com.khoabug.coffeshop.user.exception;

/**
 * @author : khoabug
 * @created : 7/21/23, Friday
 **/
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
