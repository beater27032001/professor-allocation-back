package com.project.professor.allocation.service.exception;

public class ServiceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String ServiceNotFoundExpetion;

	public ServiceNotFoundException(String serviceNotFoundExpetion) {
		super("Does not exist.");
		ServiceNotFoundExpetion = serviceNotFoundExpetion;
	}

	public ServiceNotFoundException() {
		super();
	}

	public String getServiceNotFoundExpetion() {
		return ServiceNotFoundExpetion;
	}

	public void setServiceNotFoundExpetion(String serviceNotFoundExpetion) {
		ServiceNotFoundExpetion = serviceNotFoundExpetion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
