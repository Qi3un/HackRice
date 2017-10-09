package edu.rice.controller;

import java.util.ArrayList;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.rice.bean.ResultBean;
import edu.rice.model.Course;
import edu.rice.service.UserService;

@Controller
public class eventController {
	
	private static int t[] = new int[]{ 0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4 };
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="/getEventsWithinMonth", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean<?> eventsWithinMonth(String email, String year, String month) {
		if (email == null || year == null || month == null)
			return ResultBean.failure("1003");
		
		List<Course> courses = userService.allCourses(email);
		return ResultBean.success(getCoursesWithinGivenMonth(courses, year, month));
	}
	
	
	private List<Map<String, String>> getCoursesWithinGivenMonth(List<Course> courses, String Year, String  Month) {
		int year = Integer.parseInt(Year);
		int month = Integer.parseInt(Month);
		List<Map<String, String>> res = new ArrayList<Map<String, String>>();
		boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
		int Maxday = new int[]{31,28,31,30,31,30,31,31,30,31,30,31}[month-1];
		if (month == 2 && isLeapYear) {
			Maxday++;
		}
		String[] table = {"U","M","R","W","T","F","S"};
		for (int i = 1;i < Maxday + 1;i++) {
			String day = table[dayofweek(i, month, year)];
			for (Course course : courses) {
				if (course.getMeetingDays() == null)
					continue;
				String[] meetingDays = course.getMeetingDays().split(",");
				String[] startTime = course.getStartTime().split(",");
				String[] endTime = course.getEndTime().split(",");
				for (int j = 0;j < meetingDays.length;j++) {
					if (meetingDays[j].indexOf(day) != -1) {
						Map<String, String> item = new HashMap<String, String>();
						item.put("courseName", course.getTitle());
						item.put("year", year+"");
						item.put("month", month+"");
						item.put("day", i+"");
						item.put("startTime",startTime[j]);
						item.put("endTime", endTime[j]);
						item.put("termCode", course.getTermCode());
						item.put("department", course.getDepartment());
						res.add(item);
					}
				}	
			}
		}
		return res;
	}

	private int dayofweek(int d, int m, int y){
	    if (m < 3) {
			y--;
		}
	    return ( y + y/4 - y/100 + y/400 + t[m-1] + d) % 7;
	}
}
