package com.geekbrains.university.controllers;

import com.geekbrains.university.dtos.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@FeignClient(
        name = "student-service",
        url = "http://localhost:8080",
        path = "/students")
public interface StudentClient {

    @GetMapping("/{id}")
    StudentDto findById(@PathVariable Long id);

    @GetMapping()
    Page<StudentDto> findAll(
            @RequestParam(name = "page_num") Integer pageNum,
            @RequestParam(required = false, name = "mark_lesser_than") BigDecimal markLesserThan,
            @RequestParam(required = false, name = "mark_greater_than") BigDecimal markGreaterThan,
            @RequestParam(required = false, name = "name_part") String namePart
    );

    @PostMapping(consumes = "application/json")
    StudentDto save(@RequestBody StudentDto studentDto);

    @PutMapping(consumes = "application/json")
    StudentDto update(@RequestBody StudentDto studentDto);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id);
}
