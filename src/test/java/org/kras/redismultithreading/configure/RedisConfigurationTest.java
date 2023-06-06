package org.kras.redismultithreading.configure;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.kras.redismultithreading.model.Address;
import org.kras.redismultithreading.model.Grade;
import org.kras.redismultithreading.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
//@Import(RedisConfiguration.class)
//@Testcontainers(disabledWithoutDocker = true)
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@SpringBootTest
@Import(RedisConfiguration.class)
@SpringJUnitConfig(classes = RedisConfiguration.class)
public class RedisConfigurationTest {
    private ValueOperations<String, Student> valOps;
    private Student student;

    @Container
    public static GenericContainer<?> redisContainer = new GenericContainer<>("redis:latest");

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private RedisConfiguration redisConfiguration;
    @Autowired
    private RedisTemplate<String, Student> template;

    @BeforeEach
    void SetUp() {
        Grade mathGrade = new Grade("math", "90");
        Grade englishGrade = new Grade("english", "80");
        Address address = new Address("8200", "Dixie Road", "Brampton", "ON");

        this.student = Student.builder()
                .studentNumber("960054419")
                .firstName("Joe")
                .lastName("Smith")
                .phoneNumber("8976543324")
                .grades(Arrays.asList(mathGrade, englishGrade))
                .address(address)
                .build();
    }

    @Test
    public void testRedisTemplate() {
        RedisTemplate<String, Student> redisTemplate = redisConfiguration.redisTemplate(redisConnectionFactory);
        valOps = redisTemplate.opsForValue();
        valOps.set(student.getStudentNumber(), student, 30, TimeUnit.SECONDS);
        Student actual = valOps.get(student.getStudentNumber());
        Assertions.assertEquals(student, actual);
    }
}
