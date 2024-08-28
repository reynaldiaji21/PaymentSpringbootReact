package com.rad.spring.react.exception;

public class FileStorageException extends RuntimeException{
	 public FileStorageException(String message) {
	        super(message);
	    }

	    public FileStorageException(String message, Throwable throwable) {
	        super(message, throwable);
	    }
}
