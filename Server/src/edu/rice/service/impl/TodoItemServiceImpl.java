package edu.rice.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.rice.mapper.TodoItemMapper;
import edu.rice.model.TodoItem;
import edu.rice.service.TodoItemService;

@Service
@Transactional
public class TodoItemServiceImpl implements TodoItemService {

	@Resource
	private TodoItemMapper todoItemMapper;
	
	@Override
	public TodoItem getTodoItem(String name, String email) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email", email);
		return todoItemMapper.getTodoItem(map);
	}

	@Override
	public void addTodoItem(Map<String, String> map) {
		todoItemMapper.addTodoItem(map);
	}

	@Override
	public void updateTodoItem(Map<String, String> map) {
		todoItemMapper.updateTodoItem(map);
	}

	@Override
	public void deleteTodoItem(String name, String email) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("email", email);
		todoItemMapper.deleteTodoItem(map);
	}

	@Override
	public List<TodoItem> allTodoItems(String email) {
		return todoItemMapper.allTodoItems(email);
	}

	@Override
	public List<TodoItem> itemsInDateRange(String beginDate, String endDate, String email) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		map.put("email", email);
		return todoItemMapper.itemsInDateRange(map);
	}

	@Override
	public List<TodoItem> searchTodoItemsByMonth(int year, int month, String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("email", email);
		return todoItemMapper.searchTodoItemsByMonth(map);
	}

	@Override
	public List<TodoItem> getTodoItemsByCategory(String category, String email) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("category", category);
		map.put("email", email);
		return todoItemMapper.getTodoItemsByCategory(map);
	}

	@Override
	public List<TodoItem> getTodoItemsByPriority(String priority, String email) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("priority", priority);
		map.put("email", email);
		return todoItemMapper.getTodoItemsByPriority(map);
	}

}
