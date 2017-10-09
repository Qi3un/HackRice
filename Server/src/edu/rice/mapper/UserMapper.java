package edu.rice.mapper;

import java.util.List;
import java.util.Map;
import edu.rice.model.Course;
import edu.rice.model.User;

public interface UserMapper {
	
	User login(Map<String, Object> map);

	/**
	 * Enroll a specific course for this user identified by email.
	 */
	boolean addCourse(Map<String, Object> map);
	
	/**
	 * Drop a specific course for this user identified by the email
	 */
	void dropCourse(Map<String, Object> map);
	
	/**
	 * Get all courses enrolled by the user identified by the email
	 * @param email
	 * @return
	 */
	List<Course> allCourses(String email);
	
	List<Course> getCourses(Map<String, Object> map);
	
	void enroll(Map<String, String> map);
}
