package com.project.professor.allocation.service.exception;

public class HasCollissionException extends Exception {

	private static final long serialVersionUID = 1L;
	private String hasCollissionException;

	public HasCollissionException(String hasCollissionException) {
		super("Has collision");
		this.hasCollissionException = hasCollissionException;
	}

	public HasCollissionException() {
		super();
	}

	public String getHasCollissionException() {
		return hasCollissionException;
	}

	public void setHasCollissionException(String hasCollissionException) {
		this.hasCollissionException = hasCollissionException;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
