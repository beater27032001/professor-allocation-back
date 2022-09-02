package com.project.professor.allocation.service;

import java.util.List;

import javax.management.ServiceNotFoundException;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	public List<Course> findAll() {
		List<Course> courses = courseRepository.findAll();
		return courses;
	}
	// ex
	public List<Course> findByNameContaining(String name) throws ServiceNotFoundException {
		List<Course> courses = courseRepository.findByNameContaining(name);
		if (courses.size() != 0) {
			return courses;
		} else {
			throw new ServiceNotFoundException("Course does not exist.");
		}
	}
	// ex
	public Course findById(Long id) throws ServiceNotFoundException {
		Course course = courseRepository.findById(id).orElse(null);
		if (course != null) {
			return course;
		} else {
			throw new ServiceNotFoundException("Course does not exist.");
		}
	}

	public Course create(Course course) {
		course.setId(null);
		course = courseRepository.save(course);
		return course;
	}
	// ex
	public Course update(Course course) throws ServiceNotFoundException {
		Long id = course.getId();
		if (id != null && courseRepository.existsById(id)) {
			course = courseRepository.save(course);
			return course;
		} else {
			throw new ServiceNotFoundException("Course does not exist.");
		}
	}
	// ex
	public void deleteById(Long id) throws ServiceNotFoundException {
		if (id != null && courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		} else {
			throw new ServiceNotFoundException("Course does not exist.");
		}
	}

	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}
}
