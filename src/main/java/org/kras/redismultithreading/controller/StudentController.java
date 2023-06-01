package org.kras.redismultithreading.controller;

import org.kras.redismultithreading.model.dto.StudentResponseDto;
import org.kras.redismultithreading.service.StudentService;
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

    public StudentController(StudentService studentService, StudentMapper mapper) {
        this.studentService = studentService;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentByNumber(@PathVariable String id) {
        return mapper.toResponseDto(studentService.getStudent(id));
    }
}

