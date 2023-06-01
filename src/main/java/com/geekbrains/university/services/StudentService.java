package com.geekbrains.university.services;

import com.geekbrains.university.dtos.StudentDto;
import com.geekbrains.university.mappers.StudentMapper;
import com.geekbrains.university.models.Student;
import com.geekbrains.university.repositories.StudentRepository;
import com.geekbrains.university.specifications.StudentSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@RequiredArgsConstructor

@Service
public class StudentService {

    private final static int PAGE_SIZE = 5;

    private final StudentRepository studentRepository;

    public StudentDto findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("No such product with id %s can be found.", id))
                );
        return StudentMapper.MAPPER.toDto(student);
    }

    public Page<StudentDto> findAll(Integer pageNum, BigDecimal lesserThanMark, BigDecimal greaterThanMark, String namePart) {

        Sort sort = Sort.sort(Student.class).by(Student::getName).ascending();
        Pageable pageable;
        Specification<Student> spec = Specification.where(null);

        if (lesserThanMark != null) {
            spec = spec.and(StudentSpecification.markIsLessThanOrEqualTo(lesserThanMark));
        }
        if (greaterThanMark != null) {
            spec = spec.and(StudentSpecification.markIsGreaterThanOrEqualTo(greaterThanMark));
        }
        if (namePart != null) {
            spec = spec.and(StudentSpecification.nameContains(namePart));
        }
        if (pageNum != null) {
            pageable = PageRequest.of(pageNum, PAGE_SIZE, sort);
        } else {
            pageable = Pageable.unpaged();
        }

        Page<Student> allStudents = studentRepository.findAll(spec, pageable);
        return allStudents.map(StudentMapper.MAPPER::toDto);
    }

    public StudentDto save(StudentDto studentDto) {
        studentDto.setId(null); //make sure it`s new
        Student newStudent = studentRepository.save(StudentMapper.MAPPER.toEntity(studentDto));
        return StudentMapper.MAPPER.toDto(newStudent);
    }

    @Transactional
    public StudentDto update(StudentDto studentDto) {
        if (studentDto.getId() != null && studentRepository.existsById(studentDto.getId())) {
            Student updatedStudent = studentRepository.save(StudentMapper.MAPPER.toEntity(studentDto));
            return StudentMapper.MAPPER.toDto(updatedStudent);
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("No such product with id %s can be found.", studentDto.getId())
        );
    }

    @Transactional
    public void deleteById(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("No such product with id %s can be found.", id)
            );
        }
    }

}
