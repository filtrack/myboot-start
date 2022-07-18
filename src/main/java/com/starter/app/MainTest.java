package com.starter.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.starter.app.dto.*;
import com.starter.app.utils.OrikaUtils;
import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainTest {
    public static void main(String[] args) {
//        tc1();
//        tc2();
//        tc3();
//        tc4();
//        tc5();
//        tc6();
//        tc7();
        tc8();
    }

    private static void tc8() {
        // 实体转换 SysKind转化为SysKindDto
        Map<String,String> refMap = new HashMap<>(3);
        refMap.put("kindName","name");
        refMap.put("createBy","createUserName");
        refMap.put("createTime","createDate");
//        pageList.convert(item -> OrikaUtils.convert(item, UserDTO.class, refMap));
    }

    /**
     * 多重映射
     */
    private static void tc7() {
        StudentGrade studentGrade = new StudentGrade();
        studentGrade.setStudentGradeName("硕士");
        Student student1 = new Student("1","javadaily","jianzh5@163.com");
        Student student2 = new Student("2","JAVA日知录","jianzh5@xxx.com");
        List<Student> studentList = Lists.newArrayList(student1,student2);
        studentGrade.setStudentList(studentList);

        Map<String,String> refMap1 = new HashMap<>();
        //map key 放置 源属性，value 放置 目标属性
        refMap1.put("email","emailAddress");


        Map<String, String> refMap2 = new HashMap<>();
        refMap2.put("studentGradeName","teacherGradeName");
        refMap2.put("studentList","teacherList");
        OrikaUtils.register(Student.class,Teacher.class,refMap1);
        TeacherGrade teacherGrade = OrikaUtils.convert(studentGrade,TeacherGrade.class,refMap2);
        System.out.println(JSON.toJSONString(teacherGrade, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 类类型映射
     */
    private static void tc6() {
        BasicPerson basicPerson = new BasicPerson();
        Student student = new Student("1","javadaily","jianzh5@163.com");
        basicPerson.setStudent(student);

        Map<String,String> refMap = new HashMap<>(2);
        //map key 放置 源属性，value 放置 目标属性
        refMap.put("student.id","id");
        refMap.put("student.name","name");
        refMap.put("student.email","emailAddress");

        Teacher teacher = OrikaUtils.convert(basicPerson, Teacher.class,refMap);
        System.out.println(JSON.toJSONString(teacher, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 集合与实体映射
     */
    private static void tc5() {
        Person person = new Person();
        person.setNameParts(Lists.newArrayList("1","javadaily","jianzh5@163.com"));
        Map<String,String> refMap = new HashMap<>(2);
        //map key 放置 源属性，value 放置 目标属性
        refMap.put("nameParts[0]","id");
        refMap.put("nameParts[1]","name");
        refMap.put("nameParts[2]","email");
        Student student = OrikaUtils.convert(person, Student.class,refMap);
        System.out.println(JSON.toJSONString(student, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 集合映射 - 字段映射
     */
    private static void tc4() {
        Student student1 = new Student("1","javadaily","jianzh5@163.com");
        Student student2 = new Student("2","JAVA日知录","jianzh5@xxx.com");
        List<Student> studentList = Stream.of(student1,student2).collect(Collectors.toList());
        Map<String, String> refMap = new HashMap<>();
        //map key 放置 源属性，value 放置 目标属性
        refMap.put("email","emailAddress");
        List<Teacher> teachers = OrikaUtils.convertList(studentList,Teacher.class,refMap);
        System.out.println(JSON.toJSONString(teachers, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 基础集合映射s
     */
    private static void tc3() {
        Student student1 = new Student("1","javadaily","jianzh5@163.com");
        Student student2 = new Student("2","JAVA日知录","jianzh5@xxx.com");
        List<Student> studentList = Stream.of(student1,student2).collect(Collectors.toList());
        List<Teacher> teachers = OrikaUtils.convertList(studentList,Teacher.class);
        System.out.println(JSON.toJSONString(teachers, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 实体映射 - 字段转换
     */
    private static void tc2() {
        Student student = new Student("1","javadaily","jianzh5@163.com");
        Map<String,String> refMap = new HashMap<>(1);
        //map key 放置 源属性，value 放置 目标属性
        refMap.put("email","emailAddress");
        Teacher teacher = OrikaUtils.convert(student, Teacher.class, refMap);
        System.out.println(JSON.toJSONString(teacher));
    }

    /**
     * 基础实体映射
     */
    private static void tc1() {
        Student student = new Student("1","javadaily","jianzh5@163.com");
        Teacher teacher = OrikaUtils.convert(student, Teacher.class);
        System.out.println(JSON.toJSONString(teacher));
    }
}
