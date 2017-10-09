package edu.rice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.rice.bean.ResultBean;
import edu.rice.model.Course;
import edu.rice.service.CourseService;
import edu.rice.service.UserService;
import edu.rice.util.Http;

@Controller
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public CourseService courseService;
	
	@RequestMapping(value="/sync", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean<?> syncWithRice() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("term", "201810");
		String[] subjs = {"ARCH", "COMP","CSCI", "ELEC", "EMBA", "ASIA", "KECK", "CHEM"};
		System.out.println("Delete all courses, records " + courseService.deleteCourses());
		for (int i = 0; i < subjs.length; i++) {
			map.put("subj", subjs[i]);
			List<Course> courses = Http.Singleton.getCousrses(map);
			courseService.addCourses(courses);
			System.out.println("add courses " + courses.size());
		}
		return ResultBean.success();
	}
	
	@RequestMapping(value="/enroll/{termCode}/{crn}", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean<?> enrollCourse(String email, @PathVariable String termCode, @PathVariable String crn) {
		if (userService.addCourse(email, termCode, crn) )
			return ResultBean.success();
		return ResultBean.failure("1002");
	}
	
	@RequestMapping(value="/courses", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean<?> myCourses(String email) {
		if (email == null)
			return ResultBean.failure("");
		return ResultBean.success(userService.allCourses(email));
	}
	
	@RequestMapping(value="/courses/{termCode}/{subj}", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean<?> searchCourses(@PathVariable String termCode, @PathVariable String subj){
		return ResultBean.success(userService.getCourses(termCode, subj));
	}
	
	@RequestMapping(value="/drop/{termCode}/{crn}", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean<?> dropCourses(String email, @PathVariable String termCode, @PathVariable String crn) {
		if (email == null)
			return ResultBean.failure("1003");
		userService.dropCourse(email, termCode, crn);
		return ResultBean.success();
	}
}
