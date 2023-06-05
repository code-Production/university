package com.geekbrains.university.controllers;

import com.geekbrains.university.dtos.StudentDto;
import com.geekbrains.university.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @GetMapping()
    public Page<StudentDto> findAll(
            @RequestParam(name = "page_num") Integer pageNum,
            @RequestParam(required = false, name = "mark_lesser_than") BigDecimal markLesserThan,
            @RequestParam(required = false, name = "mark_greater_than") BigDecimal markGreaterThan,
            @RequestParam(required = false, name = "name_part") String namePart
    )
    {
        return studentService.findAll(pageNum, markLesserThan, markGreaterThan, namePart);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto save(@RequestBody StudentDto studentDto) {
        return studentService.save(studentDto);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public StudentDto update(@RequestBody StudentDto studentDto) {
        return studentService.update(studentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }

}
