package org.kras.redismultithreading.service;

import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.model.dto.AccountDto;
import org.kras.redismultithreading.util.ExternalServiceCall;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentServiceApiImpl<T> implements StudentService {

    private ExternalServiceCall<AccountDto> externalServiceCall;
    @Value("${app.mock-endpoint.address}")
    private String urlAddress;

    @Value("${app.mock-endpoint.grades}")
    private String urlGrades;

    @Value("${app.mock-endpoint.account}")
    private String urlAccount = "demo6308572.mockable.io/student/account";

    public StudentServiceApiImpl(ExternalServiceCall<AccountDto> externalServiceCall) {
        this.externalServiceCall = externalServiceCall;
    }

    @Override
    public void saveStudent(Student student) {

    }

    @Override
    public Student getStudent(String studentNumber) {
        try {
            AccountDto dto = externalServiceCall.getPart(urlAccount, AccountDto.class, studentNumber);
        } catch (Exception e) {
            new RuntimeException("Call problem", e);
        }
        return null;
    }
}
