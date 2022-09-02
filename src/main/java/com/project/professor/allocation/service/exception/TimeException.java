package com.project.professor.allocation.service.exception;

public class TimeException extends Exception {

	private static final long serialVersionUID = 1L;
	private String timeException;

	public TimeException(String timeException) {
		super("The start time must be less than the end time");
		this.timeException = timeException;
	}

	public TimeException() {
		super();
	}

	public String getTimeException() {
		return timeException;
	}

	public void setTimeException(String timeException) {
		this.timeException = timeException;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
