package org.kras.redismultithreading.service;

import org.kras.redismultithreading.model.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class StudentStrategy {

    public static final String API = "API";
    public static final String DEFAULT = "DEFAULT";
    @Qualifier("cacheService")
    private StudentService studentServiceCache;
    @Qualifier("apiService")
    private StudentService studentServiceApi;

    public StudentStrategy(@Qualifier("cacheService") StudentService studentServiceCache,
                           @Qualifier("apiService") StudentService studentServiceApi) {
        this.studentServiceCache = studentServiceCache;
        this.studentServiceApi = studentServiceApi;
    }

    public StudentService getStrategy(Student student) {
        Map<String, StudentService> strategies = new HashMap<>();
        strategies.put(DEFAULT, studentServiceCache);
        strategies.put(API, studentServiceApi);
        return strategies.getOrDefault(chooser.apply(student), studentServiceCache);
    }

    Function<Student, String> chooser = (student -> null == student ? API : DEFAULT);

}
