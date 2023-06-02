package org.kras.redismultithreading.controller;

import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.model.dto.StudentResponseDto;
import org.kras.redismultithreading.service.StudentService;
import org.kras.redismultithreading.service.StudentStrategy;
import org.kras.redismultithreading.service.mapper.StudentMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper mapper;
    private final StudentStrategy studentStrategy;

    public StudentController(StudentService studentService, StudentMapper mapper, StudentStrategy studentStrategy) {
        this.studentService = studentService;
        this.mapper = mapper;
        this.studentStrategy = studentStrategy;
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentByNumber(@PathVariable String id) {
        Student student = studentService.getStudent(id);
        student = studentStrategy.getStrategy(student).getStudent(id);
        return mapper.toResponseDto(student);
    }
}

