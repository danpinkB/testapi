package com.testserver.test.dto;

import lombok.Data;

@Data
public class FeedbackResponseDTO {
    private Long id;
    private String name;

    private String email;

    private String message;
}
