package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {

	@Autowired
	DepartmentService departmentService;

	@Test
	public void findAll() {
		List<Department> departments = departmentService.findAll();
		System.out.println(departments);
	}

	@Test
	public void findByNameContaining() {
		List<Department> department = departmentService.findByNameContaining("Bio");
		System.out.println(department);
	}

	@Test
	public void findById() {
		Department department = departmentService.findById(1l);
		System.out.println(department);
	}

	@Test
	public void create() {
		Department department = new Department();
		department.setId(null);
		department.setName("Test3 Department");
		department = departmentService.create(department);

		System.out.println(department);
	}

	@Test
	public void upadte() {
		Department department = new Department();
		department.setId(3L);
		department.setName("Test2 Department");
		department = departmentService.update(department);

		System.out.println(department);
	}

	@Test
	public void deleteById() {
		departmentService.deleteById(3l);
	}
	
	@Test
	public void deleteAll() {
		departmentService.deleteAll();
	}

}
