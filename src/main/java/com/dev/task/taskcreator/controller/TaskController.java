package com.dev.task.taskcreator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dev.task.taskcreator.model.Employee;
import com.dev.task.taskcreator.model.Projects;
import com.dev.task.taskcreator.model.Tasks;
import com.dev.task.taskcreator.service.EmployeeService;
import com.dev.task.taskcreator.service.ProjectService;
import com.dev.task.taskcreator.service.TaskService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

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

    private final Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

    /**
     * Get all projects.
     *
     * @return List of projects
     */
    @GetMapping("/projects")
    public ResponseEntity<List<Projects>> getAllProjects() {
        List<Projects> projects = projectService.fetchAllProjects();
        return ResponseEntity.ok(projects);
    }

    /**
     * Get tasks for a specific project.
     *
     * @param projectId ID of the project
     * @return List of tasks for the project
     */
    @GetMapping("/projects/{projectId}/tasks")
    public ResponseEntity<List<Tasks>> getTasksByProject(@PathVariable String projectId) {
        List<Tasks> tasks = taskService.fetchTaskByProjects(projectId);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Add a new task.
     *
     * @param task Task details
     * @throws Exception if task cannot be assigned
     */
    @PostMapping("/tasks")
    public ResponseEntity<Void> addTask(@RequestBody Tasks task) throws Exception {
        taskService.assignTasks(task);
        return ResponseEntity.status(201).build(); // HTTP 201 for successful creation
    }

    /**
     * Get all employees assigned to a specific project.
     *
     * @param projectId ID of the project
     * @return JSON array of employees
     */
    @GetMapping("/projects/{projectId}/employees")
    public ResponseEntity<String> getEmployeesByProject(@PathVariable String projectId) {
        List<Employee> employees = employeeService.fetchEmployeeByProject(projectId);
        String employeesJson = gson.toJson(employees);
        return ResponseEntity.ok(employeesJson);
    }
}
