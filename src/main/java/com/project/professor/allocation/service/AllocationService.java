package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.service.exception.HasCollissionException;

@Service
public class AllocationService {
	private final AllocationRepository allocationRepository;
	private final ProfessorService professorService;
	private final CourseService courseService;

	public AllocationService(AllocationRepository allocationRepository, ProfessorService professorService,
			CourseService courseService) {
		super();
		this.allocationRepository = allocationRepository;
		this.professorService = professorService;
		this.courseService = courseService;
	}

	public List<Allocation> findAll() {
		List<Allocation> allocations = allocationRepository.findAll();
		return allocations;
	}

	public Allocation findById(Long id) {
		Allocation allocation = allocationRepository.findById(id).orElse(null);
		return allocation;
	}

	public List<Allocation> findByProfessorId(Long professorId) {
		List<Allocation> profAllocation = allocationRepository.findByProfessorId(professorId);
		return profAllocation;
	}

	public List<Allocation> findByCourseId(Long courseId) {
		List<Allocation> courseAllocation = allocationRepository.findByCourseId(courseId);
		return courseAllocation;
	}

	public Allocation create(Allocation allocation) throws HasCollissionException {
		allocation.setId(null);
		return saveInternal(allocation);
	}

	public Allocation update(Allocation allocation) throws HasCollissionException {
		Long id = allocation.getId();
		if (id != null && allocationRepository.existsById(id)) {
			return saveInternal(allocation);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		if (id != null && allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}

	private Allocation saveInternal(Allocation allocation) throws HasCollissionException {
		if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
			throw new HasCollissionException();
		} else {

			Allocation allocationSaved = allocationRepository.save(allocation);

			Long professorId = allocation.getProfessorId();
			Professor professor = professorService.findById(professorId);

			Long courseId = allocation.getCourseId();
			Course course = courseService.findById(courseId);

			allocationSaved.setProfessor(professor);
			allocationSaved.setCourse(course);

			return allocationSaved;
		}
	}

	boolean isEndHourGreaterThanStartHour(Allocation allocation) {
		return allocation != null && allocation.getStart() != null && allocation.getEnd() != null
				&& allocation.getEnd().compareTo(allocation.getStart()) > 0;
	}

	boolean hasCollision(Allocation newAllocation) {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				break;
			}
		}

		return hasCollision;
	}

	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
		return !currentAllocation.getId().equals(newAllocation.getId())
				&& currentAllocation.getDay() == newAllocation.getDay()
				&& currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
				&& newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
	}
}
