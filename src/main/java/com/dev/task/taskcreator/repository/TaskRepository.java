package com.dev.task.taskcreator.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.task.taskcreator.entity.TaskEntity;

@Repository
public interface TaskRepository extends CrudRepository<TaskEntity, Integer>{
	public List<TaskEntity> findByProjectBelonged(String projectBelonged);
}
