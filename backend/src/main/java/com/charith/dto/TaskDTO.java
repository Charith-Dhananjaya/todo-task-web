package com.charith.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private Boolean status;

    private LocalDateTime createdAt;
}
