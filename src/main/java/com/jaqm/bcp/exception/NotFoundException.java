package com.jaqm.bcp.exception;

public final class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, boolean writeablestacktrace) {
        super(message, new Throwable(message, null, false, writeablestacktrace) {
        });
    }
}
