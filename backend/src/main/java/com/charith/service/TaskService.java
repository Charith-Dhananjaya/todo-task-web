package com.charith.service;

import com.charith.dto.TaskDTO;
import com.charith.model.Task;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);
    List<TaskDTO> findMostRecentTasks();
}
