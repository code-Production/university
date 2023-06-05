package com.geekbrains.university.services;

import com.geekbrains.university.dtos.StudentDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface StudentService {
    StudentDto findById(Long id);
    Page<StudentDto> findAll(Integer pageNum, BigDecimal markLesserThan, BigDecimal markGreaterThan, String namePart);
    StudentDto save(StudentDto studentDto);
    StudentDto update(StudentDto studentDto);
    void deleteById(Long id);
}
