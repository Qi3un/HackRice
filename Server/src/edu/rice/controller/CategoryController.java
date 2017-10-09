package edu.rice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.rice.bean.ResultBean;
import edu.rice.model.TodoCategory;
import edu.rice.service.TodoCategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private TodoCategoryService todoCategoryService;
	
	@RequestMapping(value="/categories", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean<List<TodoCategory>> allCategories() {
		return ResultBean.success(todoCategoryService.allTodoCategories());
	}
	
	@RequestMapping(value="/category", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean<?> addCategory(TodoCategory category) {
		if (category == null || category.getCategory() == null)
			return ResultBean.failure("1003");
		if (todoCategoryService.getTodoCategory(category.getCategory()) != null )
			return ResultBean.failure("1004");
		todoCategoryService.addTodoCategory(category);
		return ResultBean.success();
	}
	
	@RequestMapping(value="/category/{category}", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean<?> getCategory(@PathVariable String category) {
		return ResultBean.success(todoCategoryService.getTodoCategory(category));
	}
	
	@RequestMapping(value="/category/{category}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultBean<?> deleteCategory(@PathVariable String category) {
		todoCategoryService.deleteTodoCategory(category);
		return ResultBean.success();
	}
}
