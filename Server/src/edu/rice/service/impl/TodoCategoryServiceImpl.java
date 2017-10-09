package edu.rice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.rice.mapper.TodoCategoryMapper;
import edu.rice.model.TodoCategory;
import edu.rice.service.TodoCategoryService;

@Service
@Transactional
public class TodoCategoryServiceImpl implements TodoCategoryService {

	@Resource
	private TodoCategoryMapper todoCategoryMapper;
	
	@Override
	public TodoCategory getTodoCategory(String category) {
		return todoCategoryMapper.getTodoCategory(category);
	}

	@Override
	public void updateTodoCategory(TodoCategory todoCategory) {
		if (todoCategory == null || todoCategory.getCategory() == null)
			return;
		
		if (getTodoCategory(todoCategory.getCategory()) != null)
			todoCategoryMapper.updateTodoCategory(todoCategory);
		else
			todoCategoryMapper.addTodoCategory(todoCategory);
	}

	@Override
	public void deleteTodoCategory(String category) {
		if (category == null)
			return ;
		todoCategoryMapper.deleteTodoCategory(category);
	}

	@Override
	public void addTodoCategory(TodoCategory todoCategory) {
		updateTodoCategory(todoCategory);
	}

	@Override
	public List<TodoCategory> allTodoCategories() {
		return todoCategoryMapper.allTodoCategories();
	}

}
