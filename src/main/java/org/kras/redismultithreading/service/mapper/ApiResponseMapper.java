package org.kras.redismultithreading.service.mapper;

import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.model.dto.AccountDto;
import org.kras.redismultithreading.model.dto.AddressDto;
import org.kras.redismultithreading.model.dto.GradeDto;
import org.springframework.stereotype.Component;

@Component
public class ApiResponseMapper {
    public Student toModel(AccountDto accountDto, AddressDto addressDto, GradeDto gradeDto) {
        return Student.builder()
                .address(addressDto.address())
                .grades(gradeDto.grades())
                .phoneNumber(accountDto.phoneNumber())
                .studentNumber(accountDto.studentNumber())
                .firstName(accountDto.firstName())
                .lastName(accountDto.lastName())
                .build();
    }
}
