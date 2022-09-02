package com.project.professor.allocation.service;

import java.util.List;

import javax.management.ServiceNotFoundException;

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

	// ex
	public List<Department> findByNameContaining(String name) throws ServiceNotFoundException {
		List<Department> departments = departmentRepository.findByNameContaining(name);
		if (departments.size() != 0) {
			return departments;
		} else {
			throw new ServiceNotFoundException("Department does not exist.");
		}
	}
	// ex
	public Department findById(Long id) throws ServiceNotFoundException {
		Department department = departmentRepository.findById(id).orElse(null);
		if (department != null) {
			return department;
		} else {
			throw new ServiceNotFoundException("Department does not exist.");
		}
	}

	public Department create(Department department) {
		department.setId(null);
		department = departmentRepository.save(department);
		return department;
	}
	// ex
	public Department update(Department department) throws ServiceNotFoundException {
		Long id = department.getId();
		if (id != null && departmentRepository.existsById(id)) {
			department = departmentRepository.save(department);
			return department;
		} else {
			throw new ServiceNotFoundException("Department does not exist.");
		}
	}
	// ex
	public void deleteById(Long id) throws ServiceNotFoundException {
		if (id != null && departmentRepository.existsById(id)) {
			departmentRepository.deleteById(id);
		} else {
			throw new ServiceNotFoundException("Department does not exist.");
		}
	}

	public void deleteAll() {
		departmentRepository.deleteAllInBatch();
	}
}
