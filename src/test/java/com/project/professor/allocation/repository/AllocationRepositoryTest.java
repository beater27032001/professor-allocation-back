package com.project.professor.allocation.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationRepositoryTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");

	@Autowired
	AllocationRepository allocationRepository;

	@Test
	public void findAll() {
		List<Allocation> AllAllocations = allocationRepository.findAll();
		System.out.println(AllAllocations);
	}

	@Test
	public void findById() {
		Long id = 1L;
		Allocation allocation = allocationRepository.findById(id).orElse(null);
		System.out.println(allocation);
	}

	@Test
	public void create() throws ParseException {

		Allocation allocation = new Allocation();
		allocation.setId(null);
		allocation.setDay(DayOfWeek.SUNDAY);
		allocation.setStart(sdf.parse("17:00-0300"));
		allocation.setEnd(sdf.parse("18:00-0300"));
		allocation.setProfessorId(2L);
		allocation.setCourseId(1L);

		allocation = allocationRepository.save(allocation);

		System.out.println(allocation);
	}

	@Test
	public void update() throws ParseException {
		Allocation allocation = new Allocation();
		allocation.setId(3L);
		allocation.setDay(DayOfWeek.MONDAY);
		allocation.setStart(sdf.parse("19:00-0300"));
		allocation.setEnd(sdf.parse("20:00-0300"));
		allocation.setProfessorId(3L);
		allocation.setCourseId(3L);

		allocation = allocationRepository.save(allocation);

		System.out.println(allocation);
	}

	@Test
	public void deleteById() {
		Long id = 1L;
		allocationRepository.deleteById(id);
	}

	@Test
	public void deleteAll() {
		allocationRepository.deleteAllInBatch();
	}
}
