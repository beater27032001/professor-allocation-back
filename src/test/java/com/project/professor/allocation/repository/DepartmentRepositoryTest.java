package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTest {

	@Autowired
	DepartmentRepository departmentRepository;

	@Test
	public void findAll() {
		List<Department> AllDepartments = departmentRepository.findAll();
		System.out.println(AllDepartments);
	}

	@Test
	public void findById() {
		Long id = 1L;
		Department department = departmentRepository.findById(id).orElse(null);
		System.out.println(department);
	}

	@Test
	public void create() {
		Department department = new Department();
		department.setName("Math Department");
		department = departmentRepository.save(department);

		System.out.println(department);
	}

	@Test
	public void update() {
		Department department = new Department();
		department.setName("History Department");
		department.setId(2L);
		department = departmentRepository.save(department);

		System.out.println(department);
	}

	@Test
	public void deleteById() {
		Long id = 3L;
		departmentRepository.deleteById(id);
	}

	@Test
	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}
}
