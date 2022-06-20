package com.testserver.test.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LocationDTO {
    private Long id;

    private String name;
}
