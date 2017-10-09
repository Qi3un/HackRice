package edu.rice.model;

import javax.xml.bind.annotation.XmlElement;

public class Course {

    private String termCode;
    private String termDescription;
    private String subject;
    private int courseNumber;
    private String section;
    private String school;
    private String department;
    private int crn;
    private String title;
    private String description;
    private String creditHours;
    private int session;
    private String meetingDays;

	private String startTime;
	private String endTime;
    private String location;
    private String instructor;
    private String xlinkCourse;   
    private int maxEnrollment;
    private int actualEnrollment;
    private int xlstWaitCapacity;
    private int xlstWaitCount;
    private String catalogInstPermission;

	public String getTermCode() {
		return termCode;
	}
	@XmlElement(name="term-code")
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	public String getTermDescription() {
		return termDescription;
	}
	@XmlElement(name="term-description")
	public void setTermDescription(String termDescription) {
		this.termDescription = termDescription;
	}
	public String getSubject() {
		return subject;
	}
	@XmlElement(name="subject")
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getCourseNumber() {
		return courseNumber;
	}
	@XmlElement(name="course-number")
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}
	public String getSection() {
		return section;
	}
	@XmlElement(name="section")
	public void setSection(String section) {
		this.section = section;
	}
	public String getSchool() {
		return school;
	}
	@XmlElement(name="school")
	public void setSchool(String school) {
		this.school = school;
	}
	public String getDepartment() {
		return department;
	}
	@XmlElement(name="department")
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getCrn() {
		return crn;
	}
	@XmlElement(name="crn")
	public void setCrn(int crn) {
		this.crn = crn;
	}
	public String getTitle() {
		return title;
	}
	@XmlElement(name="title")
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	@XmlElement(name="description")
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreditHours() {
		return creditHours;
	}
	@XmlElement(name="credit-hours")
	public void setCreditHours(String creditHours) {
		this.creditHours = creditHours;
	}
	public int getSession() {
		return session;
	}
	@XmlElement(name="session")
	public void setSession(int session) {
		this.session = session;
	}
	public String getLocation() {
		return location;
	}
	@XmlElement(name="location")
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInstructor() {
		return instructor;
	}
	@XmlElement(name="instructor")
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public int getMaxEnrollment() {
		return maxEnrollment;
	}
	@XmlElement(name="max-enrollment")
	public void setMaxEnrollment(int maxEnrollment) {
		this.maxEnrollment = maxEnrollment;
	}
	public int getActualEnrollment() {
		return actualEnrollment;
	}
	@XmlElement(name="actual-enrollment")
	public void setActualEnrollment(int actualEnrollment) {
		this.actualEnrollment = actualEnrollment;
	}
	public int getXlstWaitCapacity() {
		return xlstWaitCapacity;
	}
	@XmlElement(name="xlst-wait-capacity")
	public void setXlstWaitCapacity(int xlstWaitCapacity) {
		this.xlstWaitCapacity = xlstWaitCapacity;
	}
	public int getXlstWaitCount() {
		return xlstWaitCount;
	}
	@XmlElement(name="xlst-wait-count")
	public void setXlstWaitCount(int xlstWaitCount) {
		this.xlstWaitCount = xlstWaitCount;
	}
	public String getCatalogInstPermission() {
		return catalogInstPermission;
	}
	@XmlElement(name="catalog-inst-permission")
	public void setCatalogInstPermission(String catalogInstPermission) {
		this.catalogInstPermission = catalogInstPermission;
	}
	public String getXlinkCourse() {
		return xlinkCourse;
	}
	@XmlElement(name="xlink-course")
	public void setXlinkCourse(String xlinkCourse) {
		this.xlinkCourse = xlinkCourse;
	}
	
    public String getStartTime() {
		return startTime;
	}
    
    @XmlElement(name="start-time")
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	@XmlElement(name="end-time")
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String toString() {
		return "Subj:" + getSubject() + " course-number:" +  getCourseNumber() + 
				" Crn:" + getCrn() + " start-time:" + getStartTime() + " end-time:" + getEndTime();
	}
	
    public String getMeetingDays() {
		return meetingDays;
	}
    @XmlElement(name="meeting-days")
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}
}
