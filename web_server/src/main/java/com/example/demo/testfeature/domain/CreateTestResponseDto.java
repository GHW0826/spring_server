package com.example.demo.testfeature.domain;

import lombok.Data;

@Data
public class CreateTestResponseDto {
    private Long id;
    private String name;
    private String countryCode;
    private String district;
    private Integer population;

    public CreateTestResponseDto(Long id, String name, String countryCode, String district, Integer population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public static CreateTestResponseDto FromEntity(TestEntity entity) {
        if (entity == null) {
            return null;
        }

        return new CreateTestResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getCountryCode(),
                entity.getDistrict(),
                entity.getPopulation()
        );
    }
}