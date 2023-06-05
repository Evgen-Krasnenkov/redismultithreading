package org.kras.redismultithreading.service;

import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.model.dto.AccountDto;
import org.kras.redismultithreading.model.dto.AddressDto;
import org.kras.redismultithreading.model.dto.GradeDto;
import org.kras.redismultithreading.service.mapper.ApiResponseMapper;
import org.kras.redismultithreading.util.ExternalServiceCall;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yaml")
@Qualifier("apiService")
public class StudentServiceApiImpl implements StudentService {

    private final ExternalServiceCall<AccountDto> accountExtService;
    private final ExternalServiceCall<GradeDto> gradeExtService;
    private final ExternalServiceCall<AddressDto> addressExtService;
    private final StudentService studentService;
    private final ApiResponseMapper mapper;

    @Value("${mock-endpoint.address}")
    public void setUrlAddress(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    private String urlAddress = "demo6308572.mockable.io/student/grades";

    @Value("${mock-endpoint.grades:demo6308572.mockable.io/student/grades}")
    private String urlGrades = "demo6308572.mockable.io/student/grades";

    @Value("${mock-endpoint.account}")
    private String urlAccount = "demo6308572.mockable.io/student/account";

    public StudentServiceApiImpl(ExternalServiceCall<AccountDto> accountExtService,
                                 ExternalServiceCall<GradeDto> gradeExtService,
                                 ExternalServiceCall<AddressDto> addressExtService,
                                 @Qualifier("cacheService") StudentService studentService, ApiResponseMapper mapper) {
        this.accountExtService = accountExtService;
        this.gradeExtService = gradeExtService;
        this.addressExtService = addressExtService;
        this.studentService = studentService;
        this.mapper = mapper;
    }

    @Override
    public void saveStudent(Student student) {
        studentService.saveStudent(student);
    }

    @Override
    public Student getStudent(String studentNumber) {
        AccountDto accountDto;
        GradeDto gradeDto;
        AddressDto addressDto;
        try {
            gradeDto = gradeExtService.getPart(urlGrades, GradeDto.class, studentNumber);
            accountDto = accountExtService.getPart(urlAccount, AccountDto.class, studentNumber);
            addressDto = addressExtService.getPart(urlAddress, AddressDto.class, studentNumber);
        } catch (Exception e) {
            throw new RuntimeException("Call problem", e);
        }
        Student student = mapper.toModel(accountDto, addressDto, gradeDto);
        saveStudent(student);
        return student;
    }
}

