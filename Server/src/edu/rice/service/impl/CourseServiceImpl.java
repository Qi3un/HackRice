package edu.rice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.rice.mapper.CourseMapper;
import edu.rice.model.Course;
import edu.rice.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Resource
	private CourseMapper courseMapper;
	
	@Override
	public void addCourses(List<Course> courses) {
		for (Course course : courses) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("crn", course.getCrn());
			map.put("termCode", course.getTermCode());
			map.put("termDescription", course.getTermDescription());
			map.put("subject", course.getSubject());
			map.put("courseNumber", course.getCourseNumber());
			map.put("section", course.getSection());
			map.put("school", course.getSchool());
			map.put("department", course.getDepartment());
			map.put("title", course.getTitle());
			map.put("descirption", course.getDescription());
			map.put("creditHours", course.getCreditHours());
			map.put("session", course.getSession());
			map.put("startTime", course.getStartTime());
			map.put("endTime", course.getEndTime());
			map.put("location", course.getLocation());
			map.put("instructor", course.getInstructor());
			map.put("xlinkCourse", course.getXlinkCourse());
			map.put("meetingDays", course.getMeetingDays());
			courseMapper.addCourses(map);
		}
	}

	@Override
	public int deleteCourses() {
		return courseMapper.deleteCourses();
	}
}
