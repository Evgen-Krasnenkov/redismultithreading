package org.kras.redismultithreading.service.mapper;

import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.model.dto.StudentRequestDto;
import org.kras.redismultithreading.model.dto.StudentResponseDto;
import org.springframework.stereotype.Service;

@Service
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student toObject(StudentRequestDto requestDto) {
        //TODO once we have more requests in the controller - create this mapper to object
        return Student.builder().build();
    }

    @Override
    public StudentResponseDto toResponseDto(Student student) {
        return StudentResponseDto.builder()
                .phoneNumber(student.phoneNumber())
                .studentNumber(student.studentNumber())
                .address(student.address())
                .grades(student.grades())
                .firstName(student.firstName())
                .lastName(student.lastName())
                .studentNumber(student.studentNumber())
                .build();
    }
}
