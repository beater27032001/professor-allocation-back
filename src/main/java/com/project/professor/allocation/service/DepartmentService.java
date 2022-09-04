package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private final DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		super();
		this.departmentRepository = departmentRepository;
	}

	public List<Department> findAll() {
		List<Department> departments = departmentRepository.findAll();
		return departments;
	}

	public List<Department> findByNameContaining(String name) {
		List<Department> department = departmentRepository.findByNameContaining(name);
		return department;
	}

	public Department findById(Long id) {
		Department department = departmentRepository.findById(id).orElse(null);
		return department;
	}

	public Department create(Department department) {
		department.setId(null);
		department = departmentRepository.save(department);
		return department;
	}

	public Department update(Department department) {
		Long id = department.getId();
		if (id != null && departmentRepository.existsById(id)) {
			department.setId(id);
			return departmentRepository.save(department);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		if (id != null && departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}
}
