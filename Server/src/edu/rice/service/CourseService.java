package edu.rice.service;

import java.util.List;

import edu.rice.model.Course;

public interface CourseService {
	
	void addCourses(List<Course> courses);
	
	int deleteCourses();
}
