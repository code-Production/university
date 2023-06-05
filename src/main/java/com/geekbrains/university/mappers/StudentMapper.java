package com.geekbrains.university.mappers;

import com.geekbrains.university.dtos.StudentDto;
import com.geekbrains.university.models.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
public interface StudentMapper {
    StudentMapper MAPPER = Mappers.getMapper(StudentMapper.class);

    Student toEntity(StudentDto studentDto);
    StudentDto toDto(Student student);
}
