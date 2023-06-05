package org.kras.redismultithreading.service;

import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.model.dto.AccountDto;
import org.kras.redismultithreading.model.dto.AddressDto;
import org.kras.redismultithreading.model.dto.GradeDto;
import org.kras.redismultithreading.util.ExternalServiceCall;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
@PropertySource("classpath:application.yaml")
public class StudentServiceApiImpl implements StudentService {

    private final ExternalServiceCall<AccountDto> accountExtService;
    private final ExternalServiceCall<GradeDto> gradeExtService;
    private final ExternalServiceCall<AddressDto> addressExtService;

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
                                 ExternalServiceCall<AddressDto> addressExtService) {
        this.accountExtService = accountExtService;
        this.gradeExtService = gradeExtService;
        this.addressExtService = addressExtService;
    }

    @Override
    public void saveStudent(Student student) {

    }

    @Override
    public Student getStudent(String studentNumber) {
        Properties props = new Properties();
        try {
            props.load(StudentServiceApiImpl.class.getResourceAsStream("/application.yaml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String property = props.getProperty("mock-endpoint.address");
        AccountDto accountDto = null;
        GradeDto gradeDto = null;
        AddressDto addressDto = null;
        try {
            gradeDto = gradeExtService.getPart(urlGrades, GradeDto.class, studentNumber);
            accountDto = accountExtService.getPart(urlAccount, AccountDto.class, studentNumber);
            addressDto = addressExtService.getPart(urlAddress, AddressDto.class, studentNumber);
        } catch (Exception e) {
            throw new RuntimeException("Call problem", e);
        }
        Student student = Student.builder()
                .address(addressDto.address())
                .grades(gradeDto.grades())
                .phoneNumber(accountDto.phoneNumber())
                .studentNumber(accountDto.studentNumber())
                .firstName(accountDto.firstName())
                .lastName(accountDto.lastName())
                .build();
        saveStudent(student);
        return student;
    }
}

