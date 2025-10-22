package com.charith.repository;

import com.charith.dto.TaskDTO;
import com.charith.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query(
            value = """
       SELECT t.id, t.title, t.description, t.status, t.created_at FROM task t
       WHERE t.status = 0 
       ORDER BY t.created_at DESC 
       LIMIT 5
       """, nativeQuery = true
       )
    List<Task> findMostRecent5Tasks();
}
