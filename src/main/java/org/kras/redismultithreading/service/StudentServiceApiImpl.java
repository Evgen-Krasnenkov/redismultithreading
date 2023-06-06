package org.kras.redismultithreading.service;

import org.kras.redismultithreading.exception.GlobalException;
import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.model.dto.AccountDto;
import org.kras.redismultithreading.model.dto.AddressDto;
import org.kras.redismultithreading.model.dto.GradeDto;
import org.kras.redismultithreading.service.mapper.ApiResponseMapper;
import org.kras.redismultithreading.util.ExternalServiceCall;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("apiService")
public class StudentServiceApiImpl extends StudentService {

    private final ExternalServiceCall<AccountDto> accountExtService;
    private final ExternalServiceCall<GradeDto> gradeExtService;
    private final ExternalServiceCall<AddressDto> addressExtService;
    private final ApiResponseMapper mapper;


    @Value("${mock-endpoint.address}")
    private String urlAddress;

    @Value("${mock-endpoint.grades}")
    private String urlGrades;

    @Value("${mock-endpoint.account}")
    private String urlAccount;

    public StudentServiceApiImpl(ExternalServiceCall<AccountDto> accountExtService,
                                 ExternalServiceCall<GradeDto> gradeExtService,
                                 ExternalServiceCall<AddressDto> addressExtService,
                                 ApiResponseMapper mapper) {
        this.accountExtService = accountExtService;
        this.gradeExtService = gradeExtService;
        this.addressExtService = addressExtService;
        this.mapper = mapper;
    }

    @Override
    public void saveStudent(Student student) {
    }

    @Override
    public Student getStudent(Optional<Student> optional, String studentNumber) {
        AccountDto accountDto;
        GradeDto gradeDto;
        AddressDto addressDto;
        try {
            gradeDto = gradeExtService.getPart(urlGrades, GradeDto.class, studentNumber);
            accountDto = accountExtService.getPart(urlAccount, AccountDto.class, studentNumber);
            addressDto = addressExtService.getPart(urlAddress, AddressDto.class, studentNumber);
        } catch (Exception e) {
            throw new GlobalException("Call problem", e);
        }
        return mapper.toModel(accountDto, addressDto, gradeDto);
    }
}

