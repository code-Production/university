package com.geekbrains.university.controllers;

import com.geekbrains.university.dtos.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientTest {

    private final StudentClient studentClient;

    @PostMapping
    public StudentDto update(@RequestBody StudentDto studentDto) {
        return studentClient.update(studentDto);
    }

}
