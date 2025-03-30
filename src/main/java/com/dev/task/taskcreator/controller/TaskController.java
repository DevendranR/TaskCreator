package com.dev.task.taskcreator.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.task.taskcreator.model.Employee;
import com.dev.task.taskcreator.model.Projects;
import com.dev.task.taskcreator.model.Tasks;
import com.dev.task.taskcreator.service.EmployeeService;
import com.dev.task.taskcreator.service.ProjectService;
import com.dev.task.taskcreator.service.TaskService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {
	@Autowired
	@Qualifier("projectService")
	private ProjectService projectService;
	@Autowired
	@Qualifier("employeeService")
	private EmployeeService employeeService;
	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;


	@GetMapping("project")
	public List<Projects> getAllProjects() {
		List<Projects> allTasks = new ArrayList<Projects>();
		allTasks = projectService.fetchAllProjects();
		return allTasks;
	}
	
	@GetMapping("project/{projectId}/task")
	public List<Tasks> viewTasks(@PathVariable("projectId") String projectId) {
		List<Tasks> allTasks = new ArrayList<Tasks>();
		allTasks = taskService.fetchTaskByProjects(projectId);
		return allTasks;
	}

	@PostMapping("task/")
	public void addTask(@RequestBody Tasks task) throws Exception {
		taskService.assignTasks(task);
	}

	@GetMapping("project/{projectId}/employee/")
	public String getAllEmployees(@PathVariable("projectId") String projectId) {
		String value2 = "";
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("dd-MM-yyyy");
		Gson gson = gsonBuilder.create();

		List<Employee> val = employeeService.fetchEmployeeByProject(projectId);
		value2 = gson.toJson(val);

		return value2;
	}
}
