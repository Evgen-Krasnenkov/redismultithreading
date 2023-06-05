package org.kras.redismultithreading.model.dto;

import lombok.Builder;
import org.kras.redismultithreading.model.Address;
import org.kras.redismultithreading.model.Grade;

import java.util.List;

@Builder
public record StudentResponseDto(String studentNumber, String firstName, String lastName, String phoneNumber,
                                 List<Grade> grades,
                                 Address address) {
    public StudentResponseDto {
    }
}