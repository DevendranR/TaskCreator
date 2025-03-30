package com.dev.task.taskcreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dev.task.taskcreator.service.EmployeeService;
import com.dev.task.taskcreator.service.ProjectService;

@SpringBootApplication
@ComponentScan("com.dev")
@EntityScan("com.dev")
@EnableJpaRepositories("com.dev")
public class TaskcreatorApplication extends SpringBootServletInitializer{
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private EmployeeService employeeService;
	
	public static void main(String[] args) {
		SpringApplication.run(TaskcreatorApplication.class, args);
	}
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(TaskcreatorApplication.class);
	}
	
	   
   @Bean
   void init() {
	   projectService.persistProject();
	   employeeService.persistEmployee();
	   
   }
}
