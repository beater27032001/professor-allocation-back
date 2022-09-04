package com.project.professor.allocation.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorRepositoryTest {

	@Autowired
	ProfessorRepository professorRepository;

	@Test
	public void findAll() {
		List<Professor> AllProfessors = professorRepository.findAll();
		System.out.println(AllProfessors);
	}

	@Test
	public void findById() {
		Long id = 2L;
		Professor professor = professorRepository.findById(id).orElse(null);

		System.out.println(professor);
	}

	@Test
	public void create() {
		Professor professor = new Professor();
		professor.setName("José Carlos");
		professor.setCpf("13337474462");
		professor.setDepartmentId(2L);
		professor = professorRepository.save(professor);

		System.out.println(professor);
	}

	@Test
	public void update() {
		Professor professor = new Professor();
		professor.setName("José Paiva");
		professor.setId(1L);
		professor.setCpf("12345678921");
		professor.setDepartmentId(1L);
		professor = professorRepository.save(professor);

		System.out.println(professor);
	}

	@Test
	public void deleteById() {
		Long id = 1L;
		professorRepository.deleteById(id);
	}

	@Test
	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}
}
