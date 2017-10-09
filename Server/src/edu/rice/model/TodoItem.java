package edu.rice.model;

public class TodoItem {
	
	private String name;
	private TodoCategory todoCategory;
	private String date;
	private String beginTime;
	private String endTime;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TodoCategory getTodoCategory() {
		return todoCategory;
	}
	public void setTodoCategory(TodoCategory todoCategory) {
		this.todoCategory = todoCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
