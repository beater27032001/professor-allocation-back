package com.project.professor.allocation.service;

import java.util.List;

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

	public List<Course> findByNameContaining(String name) {
		List<Course> courses = courseRepository.findByNameContaining(name);
		return courses;
	}

	public Course findById(Long id) {
		Course Course = courseRepository.findById(id).orElse(null);
		return Course;
	}

	public Course create(Course course) {
		course.setId(null);
		course = courseRepository.save(course);
		return course;
	}

	public Course update(Course course) {
		Long id = course.getId();
		if (id != null && courseRepository.existsById(id)) {
			return courseRepository.save(course);
		} else {
			return null;
		}
	}

	public void deleteById(Long id) {
		if (id != null && courseRepository.existsById(id)) {
			courseRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		courseRepository.deleteAllInBatch();
	}
}
