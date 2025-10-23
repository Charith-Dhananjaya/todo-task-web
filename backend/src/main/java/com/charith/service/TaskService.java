package com.charith.service;

import com.charith.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);
    List<TaskDTO> findMostRecentTasks();
    void updateTaskStatus(Long id, TaskDTO taskDTO) throws Exception;
}
