package com.dev.task.taskcreator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.task.taskcreator.entity.ProjectsEntity;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectsEntity, String>{

}
