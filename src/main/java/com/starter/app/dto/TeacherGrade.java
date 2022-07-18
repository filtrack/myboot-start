package com.starter.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeacherGrade {
    private String teacherGradeName;
    private List<Teacher> teacherList;
}