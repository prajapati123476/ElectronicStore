package com.lcwd.electronics.store.exceptions;

public class BadApiRequest extends RuntimeException{


    public BadApiRequest() {
        super("Bad Request !!!");
    }
    public BadApiRequest(String message) {
        super(message);
    }

}
