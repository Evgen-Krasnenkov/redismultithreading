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

    private final StudentService studentServiceCacheImpl;
    private final StudentMapper mapper;
    private final StudentStrategy studentStrategy;

    public StudentController(StudentService studentServiceCacheImpl, StudentMapper mapper,
                             StudentStrategy studentStrategy) {
        this.studentServiceCacheImpl = studentServiceCacheImpl;
        this.mapper = mapper;
        this.studentStrategy = studentStrategy;
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudentByNumber(@PathVariable String id) {
        Student student = studentServiceCacheImpl.getStudent(id);
        StudentService service = studentStrategy.getStrategy(student);
        student = service.getStudent(id);
        service.saveStudent(student);
        return mapper.toResponseDto(student);
    }
}

