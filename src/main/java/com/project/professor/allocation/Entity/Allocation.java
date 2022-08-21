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
	
}
