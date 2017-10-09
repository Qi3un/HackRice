package edu.rice.mapper;

import java.util.List;

import edu.rice.model.TodoCategory;

public interface TodoCategoryMapper {
	
	public TodoCategory getTodoCategory(String category);
	
	public List<TodoCategory> allTodoCategories();
	
	public void updateTodoCategory(TodoCategory todoCategory);
	
	public void deleteTodoCategory(String category);
	
	public void addTodoCategory(TodoCategory todoCategory);
	
	
}
