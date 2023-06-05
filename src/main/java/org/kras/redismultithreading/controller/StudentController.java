package org.kras.redismultithreading.controller;

import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.model.dto.StudentResponseDto;
import org.kras.redismultithreading.service.ChainServiceBuilder;
import org.kras.redismultithreading.service.StudentService;
import org.kras.redismultithreading.service.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentApiService;
    private final StudentService studentCacheService;
    private final StudentMapper mapper;
    private final ChainServiceBuilder chainServiceBuilder;

    public StudentController(@Qualifier("apiService") StudentService studentApiService,
                             @Qualifier("cacheService") StudentService studentCacheService,
                             StudentMapper mapper,
                             ChainServiceBuilder chainServiceBuilder) {
        this.studentApiService = studentApiService;
        this.studentCacheService = studentCacheService;
        this.mapper = mapper;
        this.chainServiceBuilder = chainServiceBuilder;
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentByNumber(@PathVariable String id) {
        StudentService studentService = chainServiceBuilder
                .setService(studentCacheService)
                .setService(studentApiService)
                .build();
        Student student = studentService.getStudent(Optional.empty(), id);
        return mapper.toResponseDto(student);
    }
}

