package com.charith.controller;

import com.charith.dto.TaskDTO;
import com.charith.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @Test
    void createTask_shouldReturnOkWithCreatedTask() {

        TaskDTO request = new TaskDTO();
         request.setTitle("Sample"); request.setDescription("desription");

        TaskDTO created = new TaskDTO();
         created.setId(1L); created.setTitle("Sample"); created.setDescription("desription");

        when(taskService.createTask(request)).thenReturn(created);

        ResponseEntity<TaskDTO> response = taskController.createTask(request);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isSameAs(created);
        verify(taskService, times(1)).createTask(request);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void createTask_whenServiceThrows_shouldPropagateException() {

        TaskDTO request = new TaskDTO();
        when(taskService.createTask(request)).thenThrow(new RuntimeException("DB error"));

        try {
            taskController.createTask(request);
        } catch (RuntimeException ex) {
            assertThat(ex).hasMessage("DB error");
        }

        verify(taskService, times(1)).createTask(request);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void findMostRecentTasks_shouldReturnOkWithList() {

        TaskDTO t1 = new TaskDTO();
        t1.setId(1L); t1.setTitle("Sample"); t1.setDescription("desription"); t1.setStatus(false);
        TaskDTO t2 = new TaskDTO();
        t2.setId(2L); t2.setTitle("title s"); t2.setDescription("desription s"); t2.setStatus(false);
        List<TaskDTO> expected = Arrays.asList(t1, t2);

        when(taskService.findMostRecentTasks()).thenReturn(expected);

        ResponseEntity<List<TaskDTO>> res = taskController.findMostRecentTasks();

        assertThat(res.getStatusCodeValue()).isEqualTo(200);
        assertThat(res.getBody()).isSameAs(expected);
        verify(taskService, times(1)).findMostRecentTasks();
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void updateTask_shouldCallServiceAndReturnOk() throws Exception {

        Long id = 42L;
        TaskDTO dto = new TaskDTO();
        dto.setStatus(true);
        ResponseEntity<String> res = taskController.updateTask(id, dto);

        assertThat(res.getStatusCodeValue()).isEqualTo(200);
        assertThat(res.getBody()).isEqualTo("Task status updated successfully");
        verify(taskService, times(1)).updateTaskStatus(id, dto);
        verifyNoMoreInteractions(taskService);
    }
}
