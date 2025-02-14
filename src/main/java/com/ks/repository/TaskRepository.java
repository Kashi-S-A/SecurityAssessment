package com.ks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ks.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{

}
