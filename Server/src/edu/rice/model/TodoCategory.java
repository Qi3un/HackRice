package edu.rice.model;

public class TodoCategory {

	private String category;
	private String color;
	private String priority;
	private String notifyMethod;
	
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getNotifyMethod() {
		return notifyMethod;
	}
	public void setNotifyMethod(String notifyMethod) {
		this.notifyMethod = notifyMethod;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
