package com.charith.service.impl;

import com.charith.dto.TaskDTO;
import com.charith.model.Task;
import com.charith.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void createTask_shouldSaveAndReturnTaskDTO() {
        TaskDTO dto = new TaskDTO();
        dto.setTitle("New Task");
        dto.setDescription("Desc");

        Task saved = new Task();
        saved.setId(1L);
        saved.setTitle("New Task");
        saved.setDescription("Desc");
        saved.setStatus(false);
        saved.setCreatedAt(LocalDateTime.now());

        when(taskRepository.save(any(Task.class))).thenReturn(saved);

        TaskDTO result = taskService.createTask(dto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("New Task");
        assertThat(result.getStatus()).isFalse();

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    @Test
    void findMostRecentTasks_shouldMapEntitiesToDTOs() {
        Task t1 = new Task(); t1.setId(1L); t1.setTitle("T1");
        Task t2 = new Task(); t2.setId(2L); t2.setTitle("T2");

        when(taskRepository.findMostRecent5Tasks()).thenReturn(List.of(t1, t2));

        List<TaskDTO> result = taskService.findMostRecentTasks();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("T1");
        assertThat(result.get(1).getTitle()).isEqualTo("T2");
        verify(taskRepository, times(1)).findMostRecent5Tasks();
    }

    @Test
    void updateTaskStatus_shouldUpdateStatusWhenFound() throws Exception {
        Task existing = new Task();
        existing.setId(1L);
        existing.setStatus(false);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(existing));

        TaskDTO dto = new TaskDTO();
        dto.setStatus(true);

        taskService.updateTaskStatus(1L, dto);

        verify(taskRepository).save(argThat(t -> t.getStatus().equals(true)));
    }

    @Test
    void updateTaskStatus_whenTaskNotFound_shouldThrowException() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        TaskDTO dto = new TaskDTO();
        dto.setStatus(true);

        Exception ex = assertThrows(Exception.class, () -> taskService.updateTaskStatus(99L, dto));
        assertThat(ex).hasMessage("Task not found");
    }
}