package com.example.demo.testfeature.controller;

import com.example.demo.common.response.ApiResponse;
import com.example.demo.testfeature.domain.CreateTestDto;
import com.example.demo.testfeature.domain.CreateTestResponseDto;
import com.example.demo.testfeature.domain.TestEntity;
import com.example.demo.testfeature.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test")
    public String ReadTest() {
        testService.test();
        return "test";
    }

    @GetMapping("/test/{id}")
    public ResponseEntity<ApiResponse<TestEntity>> getTestById(@PathVariable("id") Long id) {
        // 전달받은 id로 테스트 데이터를 조회하는 로직을 수행합니다.
        TestEntity t = testService.getById(id).orElseThrow();
        return ApiResponse.ok(t);  // 뷰 이름 또는 응답값 반환 (예: test.html)
    }

    @PostMapping("/test")
    public ResponseEntity<ApiResponse<CreateTestResponseDto>> CreateTest(@RequestBody CreateTestDto createTestDto) {
        CreateTestResponseDto test = testService.CreateTest(createTestDto);
        System.out.println(test.toString());
        return ApiResponse.ok(test);  // 뷰 이름 또는 응답값 반환 (예: test.html)
    }

    @DeleteMapping("/test")
    public String DeleteTest() {
        testService.test();
        return "test";
    }

    @PutMapping("/test")
    public String UpdateTest() {
        testService.test();
        return "test";
    }

    @GetMapping("/cache/{id}")
    public String CacheTest(@PathVariable("id") Long id) {
        testService.CacheTest(id);
        return "test";
    }
}
