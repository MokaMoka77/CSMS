package com.khoabug.coffeshop.common.repository.exception;

/**
 * @author : khoabug
 * @created : 7/21/23, Friday
 **/
public class NotFoundException extends RuntimeException{
    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }
}
