package org.kras.redismultithreading.service;

import org.kras.redismultithreading.model.Student;

public interface StudentService {
    void saveStudent(Student student);
    Student getStudent(String studentNumber);
}
