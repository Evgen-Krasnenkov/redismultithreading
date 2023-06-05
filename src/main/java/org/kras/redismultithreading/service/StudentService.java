package org.kras.redismultithreading.service;

import org.kras.redismultithreading.model.Student;

import java.util.Optional;

public abstract class StudentService {
    protected StudentService nextService;
    public abstract void saveStudent(Student student);
    public abstract Student getStudent(Optional<Student> optional, String id);

    protected void setNextService(StudentService nextService){
        this.nextService = nextService;
    }
}
