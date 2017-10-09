package edu.rice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.rice.mapper.UserMapper;
import edu.rice.model.Course;
import edu.rice.model.User;
import edu.rice.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper usermapper;
	
	
	@Override
	public User login(String email, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		return usermapper.login(map);
	}


	@Override
	public boolean addCourse(String email, String termCode, String crn) {
		if (email == null || termCode == null || crn == null)
			return false;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("termCode", termCode);
		map.put("crn", crn);
		return usermapper.addCourse(map);
	}


	@Override
	public void dropCourse(String email, String term, String crn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("termCode", term);
		map.put("crn", crn);
		usermapper.dropCourse(map);
	}


	@Override
	public List<Course> allCourses(String email) {
		return usermapper.allCourses(email);
	}


	@Override
	public List<Course> getCourses(String termCode, String subj) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("termCode",termCode);
		map.put("subj", subj);
		return usermapper.getCourses(map);
	}


	@Override
	public void enroll(String email) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("password", email);
		usermapper.enroll(map);
	}

}
