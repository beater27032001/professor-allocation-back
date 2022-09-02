package com.project.professor.allocation.service;

import java.util.List;

import javax.management.ServiceNotFoundException;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;
import com.project.professor.allocation.service.exception.HasCollissionException;
import com.project.professor.allocation.service.exception.TimeException;

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

	// ex
	public Allocation findById(Long id) throws ServiceNotFoundException {
		Allocation allocation = allocationRepository.findById(id).orElse(null);
		if (allocation != null) {
			return allocation;
		} else {
			throw new ServiceNotFoundException("Allocation does not exist.");
		}
	}

	// ex
	public List<Allocation> findByProfessorId(Long professorId) throws ServiceNotFoundException {
		List<Allocation> allocation = allocationRepository.findByProfessorId(professorId);
		if (allocation != null) {
			return allocation;
		} else {
			throw new ServiceNotFoundException("Professor does not exist.");
		}
	}

	// ex
	public List<Allocation> findByCourseId(Long courseId) throws ServiceNotFoundException {
		List<Allocation> allocation = allocationRepository.findByCourseId(courseId);
		if (allocation != null) {
			return allocation;
		} else {
			throw new ServiceNotFoundException("Course does not exist.");
		}
	}

	public Allocation create(Allocation allocation) throws TimeException, HasCollissionException, ServiceNotFoundException {
		allocation.setId(null);
		return saveInternal(allocation);
	}

	// ex
	public Allocation update(Allocation allocation)
			throws ServiceNotFoundException, TimeException, HasCollissionException {
		Long id = allocation.getId();
		if (id != null && allocationRepository.existsById(id)) {
			return saveInternal(allocation);
		} else {
			throw new ServiceNotFoundException("Allocation does not exist.");
		}
	}

	// ex
	public void deleteById(Long id) throws ServiceNotFoundException {
		if (id != null && allocationRepository.existsById(id)) {
			allocationRepository.deleteById(id);
		} else {
			throw new ServiceNotFoundException("Allocation does not exist.");
		}
	}

	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}

	// ex
	private Allocation saveInternal(Allocation allocation) throws TimeException, HasCollissionException, ServiceNotFoundException {
		if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
			throw new RuntimeException();
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

	// ex
	boolean isEndHourGreaterThanStartHour(Allocation allocation) throws TimeException {
		boolean isEndHourGreaterThanStartHour = true;
		if (allocation != null && allocation.getStart() != null && allocation.getEnd() != null
				&& allocation.getEnd().compareTo(allocation.getStart()) < 0) {
			throw new TimeException("The start time must be less than the end time.");
		} else {
			return isEndHourGreaterThanStartHour;
		}
		/*
		 * return allocation != null && allocation.getStart() != null &&
		 * allocation.getEnd() != null &&
		 * allocation.getEnd().compareTo(allocation.getStart()) > 0;
		 */
	}

	boolean hasCollision(Allocation newAllocation) throws HasCollissionException {
		boolean hasCollision = false;

		List<Allocation> currentAllocations = allocationRepository.findByProfessorId(newAllocation.getProfessorId());

		for (Allocation currentAllocation : currentAllocations) {
			hasCollision = hasCollision(currentAllocation, newAllocation);
			if (hasCollision) {
				throw new HasCollissionException("The professor is already allocated at this time of day.");
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
