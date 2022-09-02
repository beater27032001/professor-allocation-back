package com.project.professor.allocation.service;

import java.util.List;

import javax.management.ServiceNotFoundException;

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
		List<Professor> departments = professorRepository.findAll();
		return departments;
	}

	// ex
	public List<Professor> findByNameContaining(String name) throws ServiceNotFoundException {
		List<Professor> departments = professorRepository.findByNameContaining(name);
		if (departments.size() != 0) {
			return departments;
		} else {
			throw new ServiceNotFoundException("Professor does not exist.");
		}

	}
	// ex
	public Professor findByCpf(String cpf) throws ServiceNotFoundException {
		Professor professor = professorRepository.findByCpf(cpf).orElse(null);
		if(professor != null) {
			return professor;
		}else {
			throw new ServiceNotFoundException("Cpf does not exist.");
		}
	}
	// ex
	public List<Professor> findByDepartmentId(Long departmentId) throws ServiceNotFoundException {
		List<Professor> departments = professorRepository.findByDepartmentId(departmentId);
		if (departments.size() != 0) {
			return departments;
		} else {
			throw new ServiceNotFoundException("Department does not exist.");
		}
	}
	// ex
	public Professor findById(Long id) throws ServiceNotFoundException {
		Professor professor = professorRepository.findById(id).orElse(null);
		if (professor != null) {
			return professor;
		} else {
			throw new ServiceNotFoundException("Professor does not exist.");
		}
	}

	public Professor create(Professor professor) throws ServiceNotFoundException {
		professor.setId(null);
		Professor prof = saveInternal(professor);
		return prof;
	}

	public Professor update(Professor professor) throws ServiceNotFoundException {
		Long id = professor.getId();
		if (id != null && professorRepository.existsById(id)) {
			return saveInternal(professor);
		} else {
			throw new ServiceNotFoundException("Professor does not exist.");
		}
	}

	public void deleteById(Long id) throws ServiceNotFoundException {
		if (id != null && professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}else {
			throw new ServiceNotFoundException("Professor does not exist.");
		}
	}

	public void deleteAll() {
		professorRepository.deleteAllInBatch();
	}

	public Professor saveInternal(Professor professor) throws ServiceNotFoundException {

		Long dptId = professor.getDepartmentId();
		Department dpt = departmentService.findById(dptId);

		Professor prof = professorRepository.save(professor);
		prof.setDepartment(dpt);

		return prof;
	}
}
