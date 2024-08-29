package com.example.capstone3ee.Repository;

import com.example.capstone3ee.Model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer> {
    Tasks findTaskByTaskId(Integer taskId);

}
