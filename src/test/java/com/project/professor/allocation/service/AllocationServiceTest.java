package com.project.professor.allocation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.service.exception.HasCollissionException;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	AllocationService allocationService;

	@Test
	public void findAll() {
		List<Allocation> allocations = allocationService.findAll();
		System.out.println(allocations);
	}

	@Test
	public void findById() {
		Allocation allocations = allocationService.findById(1l);
		System.out.println(allocations);
	}

	@Test
	public void findByProfessorId() {
		List<Allocation> allocations = allocationService.findByProfessorId(2l);
		System.out.println(allocations);
	}

	@Test
	public void findByCourseId() {
		List<Allocation> allocations = allocationService.findByCourseId(2l);
		System.out.println(allocations);
	}

	@Test
	public void create() throws ParseException, HasCollissionException {
		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setDay(DayOfWeek.WEDNESDAY);
		allocation.setStart(sdf.parse("19:00-0300"));
		allocation.setEnd(sdf.parse("20:00-0300"));
		allocation.setProfessorId(1L);
		allocation.setCourseId(1L);
		allocation = allocationService.create(allocation);

		System.out.println(allocation);
	}

	@Test
	public void update() throws ParseException, HasCollissionException {
		Allocation allocation = new Allocation();
		allocation.setId(1L);
		allocation.setDay(DayOfWeek.MONDAY);
		allocation.setStart(sdf.parse("19:00-0300"));
		allocation.setEnd(sdf.parse("20:00-0300"));
		allocation.setProfessorId(1L);
		allocation.setCourseId(1L);
		allocation = allocationService.update(allocation);

		System.out.println(allocation);
	}

	@Test
	public void deleteById() {
		Long id = 1L;
		allocationService.deleteById(id);
	}

	@Test
	public void deleteAll() {
		allocationService.deleteAll();
	}
}
