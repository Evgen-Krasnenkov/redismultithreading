package org.kras.redismultithreading.service;

import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.model.dto.AccountDto;
import org.kras.redismultithreading.model.dto.AddressDto;
import org.kras.redismultithreading.model.dto.GradeDto;
import org.kras.redismultithreading.util.ExternalServiceCall;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class StudentStrategy {

    public static final String API = "API";
    public static final String DEFAULT = "DEFAULT";
    private final RedisTemplate<String, Student> redisTemplate;
    private final ExternalServiceCall<AccountDto> accountExtService;
    private ExternalServiceCall<GradeDto> gradeExtService;
    private ExternalServiceCall<AddressDto> addressExtService;

    public StudentStrategy(RedisTemplate<String, Student> redisTemplate, ExternalServiceCall<AccountDto> accountExtService, ExternalServiceCall<GradeDto> gradeExtService, ExternalServiceCall<AddressDto> addressExtService) {
        this.redisTemplate = redisTemplate;
        this.accountExtService = accountExtService;
        this.gradeExtService = gradeExtService;
        this.addressExtService = addressExtService;
    }

    public StudentService getStrategy(Student student) {
        Map<String, StudentService> strategies = new HashMap<>();
        StudentService serviceApi = new StudentServiceApiImpl(accountExtService, gradeExtService,
                addressExtService);
        StudentService serviceCache = new StudentServiceCacheImpl(redisTemplate);
        strategies.put(API, serviceApi);
        strategies.put(DEFAULT, serviceCache);
        return strategies.getOrDefault(chooser.apply(student), serviceApi);
    }

    Function<Student, String> chooser = (student -> null == student ? API : DEFAULT);

}
