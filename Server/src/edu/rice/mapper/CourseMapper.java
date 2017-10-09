package edu.rice.mapper;

import java.util.Map;

public interface CourseMapper {
	
	void addCourses(Map<String, Object> map);

	int deleteCourses();
	
}
