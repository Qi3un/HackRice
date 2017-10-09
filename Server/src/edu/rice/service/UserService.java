package edu.rice.service;

import java.util.List;

import edu.rice.model.Course;
import edu.rice.model.User;

public interface UserService {
	/**
     * Validate the login operation using given email and password. If the validation fails, 
     * returns null; otherwise returns the User object specified by the email.
	 * @param email
	 * @param password
	 * @return
	 */
	User login(String email, String password);

	/**
	 * Enroll a specific course for this user identified by email.
	 * @param term term for this course
	 * @param crn crn for this course
	 * @return whether or not add the course
	 */
	boolean addCourse(String email, String term, String crn);
	
	/**
	 * Drop a specific course for this user identified by the email
	 * @param email
	 * @param term
	 * @param crn
	 */
	void dropCourse(String email, String term, String crn);
	
	/**
	 * Get all courses enrolled by the user identified by the email
	 * @param email
	 * @return
	 */
	List<Course> allCourses(String email);
	
	List<Course> getCourses(String termCode, String subj);

	void enroll(String email);
}
