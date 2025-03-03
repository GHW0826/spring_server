package com.example.demo.testfeature.domain;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CreateTestDto {
    private String name;
    private String countryCode;
    private String district;
    private Integer population;
}
