package com.example.demo.testfeature.service;

import com.example.demo.testfeature.domain.CreateTestDto;
import com.example.demo.testfeature.domain.CreateTestResponseDto;
import com.example.demo.testfeature.domain.TestEntity;
import com.example.demo.testfeature.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestService {
    private final TestRepository testRepository;

    // 생성자 주입
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public void test() {
        testRepository.findAll();
    }

    public Optional<TestEntity> getById(Long id) {
        return testRepository.findById(id);
    }

    public CreateTestResponseDto CreateTest(CreateTestDto createTestDto) {
        TestEntity newTest = TestEntity.toEntity(createTestDto);
        testRepository.save(newTest);
        return CreateTestResponseDto.FromEntity(newTest);
    }
}
