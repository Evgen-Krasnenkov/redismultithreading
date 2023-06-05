package org.kras.redismultithreading.exception;

public class GlobalException extends Exception{
    public GlobalException(String e, Throwable throwable){
        super(e, throwable);
    }
    public GlobalException(String e){
        super(e);
    }
}
