package com.testserver.test.dto;

import lombok.Data;

@Data
public class FeedbackRequestDTO {
    private String name;

    private String email;

    private String message;
}
