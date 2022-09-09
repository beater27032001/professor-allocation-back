package com.project.professor.allocation.service;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {

	@Autowired
	ProfessorService professorService;

	@Test
	public void findAll() {
		List<Professor> professors = professorService.findAll();
		System.out.println(professors);
	}

	@Test
	public void findByNameContaining() {
		List<Professor> professors = professorService.findByNameContaining("Carlos");
		System.out.println(professors);
	}

	@Test
	public void findByCpf() {
		Professor professor = professorService.findByCpf("13337474462");
		System.out.println(professor);
	}

	@Test
	public void findByDepartmentId() {
		List<Professor> professors = professorService.findByDepartmentId(1l);
		System.out.println(professors);
	}

	@Test
	public void findById() {
		Professor professor = professorService.findById(1l);
		System.out.println(professor);
	}

	@Test
	public void create() {
		Professor professor = new Professor();
		professor.setId(null);
		professor.setName("Henrique");
		professor.setCpf("12394847381");
		professor.setDepartmentId(1L);
		professor = professorService.create(professor);

		System.out.println(professor);
	}

	@Test
	public void update() {
		Professor professor = new Professor();
		professor.setId(3L);
		professor.setName("Professor 2");
		professor.setCpf("22222222222");
		professor.setDepartmentId(2L);

		professor = professorService.update(professor);

		System.out.println(professor);
	}

	@Test
	public void deleteById() {
		Long id = 3l;
		professorService.deleteById(id);

		Professor professor = professorService.findById(id);
		System.out.println(professor);
	}

	@Test
	public void deleteAll() {
		professorService.deleteAll();
		List<Professor> professors = professorService.findAll();
		System.out.println(professors);
	}
}
