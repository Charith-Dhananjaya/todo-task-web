package com.charith.service.impl;

import com.charith.dto.TaskDTO;
import com.charith.model.Task;
import com.charith.repository.TaskRepository;
import com.charith.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {

        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(false);
        Task savedTask = taskRepository.save(task);

        TaskDTO taskdto = new TaskDTO();
        taskdto.setId(savedTask.getId());
        taskdto.setTitle(savedTask.getTitle());
        taskdto.setDescription(savedTask.getDescription());
        taskdto.setStatus(savedTask.getStatus());
        taskdto.setCreatedAt(savedTask.getCreatedAt());
        return taskdto;
    }

    @Override
    public List<TaskDTO> findMostRecentTasks() {

        List<Task> tasks = taskRepository.findMostRecent5Tasks();

        List<TaskDTO> taskList = new ArrayList<TaskDTO>();
        for(int i = 0; i < tasks.size(); i++){
            TaskDTO taskDto = new TaskDTO();

            taskDto.setId(tasks.get(i).getId());
            taskDto.setTitle(tasks.get(i).getTitle());
            taskDto.setDescription(tasks.get(i).getDescription());
            taskDto.setStatus(tasks.get(i).getStatus());
            taskDto.setCreatedAt(tasks.get(i).getCreatedAt());
            taskList.add(taskDto);
        }
        return taskList;
    }
}
