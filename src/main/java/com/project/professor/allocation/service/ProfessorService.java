package com.project.professor.allocation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	private final DepartmentService departmentService;

	public ProfessorService(ProfessorRepository professorRepository, DepartmentService departmentService) {
		super();
		this.professorRepository = professorRepository;
		this.departmentService = departmentService;
	}

	public List<Professor> findAll() {
		List<Professor> professor = professorRepository.findAll();
		return professor;
	}

	public List<Professor> findByNameContaining(String name) {
		List<Professor> professor = professorRepository.findByNameContaining(name);
		return professor;
	}

	public Optional<Professor> findByCpf(String cpf) {
		Optional<Professor> professor = professorRepository.findByCpf(cpf);
		return professor;
	}

	public List<Professor> findByDepartmentId(Long departmentId) {
		List<Professor> professors = professorRepository.findByDepartmentId(departmentId);
		return professors;
	}

	public Professor findById(Long id) {
		Professor professor = professorRepository.findById(id).orElse(null);
		return professor;
	}

	public Professor create(Professor professor) {
		professor.setId(null);
		Professor prof = saveInternal(professor);
		return prof;
	}

	public Professor update(Professor professor) {
		Long id = professor.getId();
		if (id != null && professorRepository.existsById(id)) {
			return saveInternal(professor);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		if (id != null && professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	public Professor saveInternal(Professor professor) {

		Long dptId = professor.getDepartmentId();
		Department dpt = departmentService.findById(dptId);

		Professor prof = professorRepository.save(professor);
		prof.setDepartment(dpt);

		return prof;
	}
}
