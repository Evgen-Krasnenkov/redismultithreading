package org.kras.redismultithreading.service.mapper;

import org.kras.redismultithreading.model.Student;
import org.kras.redismultithreading.model.dto.StudentRequestDto;
import org.kras.redismultithreading.model.dto.StudentResponseDto;

public interface StudentMapper {
    Student toObject(StudentRequestDto requestDto);
    StudentResponseDto toResponseDto(Student student);
}
