package com.geekbrains.university.dtos;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDto {
    private Long id;
    private String name;
    private BigDecimal mark;
}
