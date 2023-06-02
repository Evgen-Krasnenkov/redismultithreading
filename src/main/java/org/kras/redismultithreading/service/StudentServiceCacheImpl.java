package org.kras.redismultithreading.service;

import org.kras.redismultithreading.model.Student;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Primary
public class StudentServiceCacheImpl implements StudentService {
    private final RedisTemplate<String, Student> redisTemplate;

    public StudentServiceCacheImpl(RedisTemplate<String, Student> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveStudent(Student student) {
        redisTemplate.opsForValue().set(student.studentNumber(), student);
    }

    @Override
    public Student getStudent(String studentNumber) {
        return redisTemplate.opsForValue().get(studentNumber);
    }
}
