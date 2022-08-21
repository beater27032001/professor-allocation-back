package com.project.professor.allocation.Entity;

import java.time.DayOfWeek;
import java.util.Date;

public class Allocation {

	private Long id;
	private DayOfWeek day;
	private Date start;
	private Date end;
	private Long courseId;
	private Long professorId;
	public Allocation(Long id, DayOfWeek day, Date start, Date end, Long courseId, Long professorId) {
		super();
		this.id = id;
		this.day = day;
		this.start = start;
		this.end = end;
		this.courseId = courseId;
		this.professorId = professorId;
		
			
		}
public Allocation() {
		
	}
}

	

