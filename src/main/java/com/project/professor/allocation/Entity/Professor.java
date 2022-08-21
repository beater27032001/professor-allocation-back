package com.project.professor.allocation.Entity;

public class Professor {

	private Long id;
	private String name;
	private String cpf;
	private Long departmentId;
	public Professor(Long id, String name, String cpf, Long departmentId) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.departmentId = departmentId;
	}
public Professor () {
		
	}
}


