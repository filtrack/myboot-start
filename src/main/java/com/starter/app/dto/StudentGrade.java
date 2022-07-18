package com.starter.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentGrade {
    private String studentGradeName;
    private List<Student> studentList;
}