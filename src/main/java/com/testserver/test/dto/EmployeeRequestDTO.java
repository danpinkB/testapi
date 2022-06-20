package com.testserver.test.dto;

import lombok.Data;

@Data
public class EmployeeRequestDTO {
    private String name;

    private Integer liked;

    private String description;

    private String avatar;

    private Long job_id;

    private Long location_id;
}
