package org.kras.redismultithreading.service;

import org.kras.redismultithreading.exception.GlobalException;
import org.kras.redismultithreading.model.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
@Qualifier("cacheService")
public class StudentServiceCacheImpl extends StudentService {
    public static final int REFRESH_TIME = 5;
    private ValueOperations<String, Student> valOps;
    private final RedisTemplate<String, Student> redisTemplate;

    public StudentServiceCacheImpl(RedisTemplate<String, Student> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveStudent(Student student) {
        valOps = redisTemplate.opsForValue();
        try {
            valOps.set(student.getStudentNumber(), student, REFRESH_TIME, TimeUnit.MINUTES);
        } catch (RuntimeException e) {
            throw new GlobalException("Exception while  saving cache " + e.getMessage());
        }
    }

    @Override
    public Student getStudent(Optional<Student> optional, String studentNumber) {
        valOps = redisTemplate.opsForValue();
        Student student = valOps.get(studentNumber);
        Optional<Student> tempOptional = Optional.ofNullable(student);
        if (tempOptional.isEmpty()) {
            student = this.nextService.getStudent(optional, studentNumber);
            saveStudent(student);
            return student;
        } else {
            return tempOptional.orElseThrow(() -> new GlobalException("Can't either get or save student"));
        }
    }
}
