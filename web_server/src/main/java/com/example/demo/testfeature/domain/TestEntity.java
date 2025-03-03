package com.example.demo.testfeature.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID 컬럼이 auto-increment인 경우
    @Column(name = "ID")
    private Long id;

    @Column(name = "Name", length = 35, nullable = false)
    private String name;

    @Column(name = "CountryCode", length = 3, nullable = false)
    private String countryCode;

    @Column(name = "District", length = 20)
    private String district;

    @Column(name = "Population", nullable = false)
    private Integer population;

    public TestEntity() {
    }

    public TestEntity(String name, String countryCode, String district, int population) {
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public static TestEntity toEntity(CreateTestDto dto) {
        if (dto == null) {
            return null;
        }

        return new TestEntity(
                dto.getName(),
                dto.getCountryCode(),
                dto.getDistrict(),
                dto.getPopulation()
        );
    }
}