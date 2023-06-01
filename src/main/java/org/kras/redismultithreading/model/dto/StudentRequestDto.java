package org.kras.redismultithreading.model.dto;

import org.kras.redismultithreading.model.Address;
import org.kras.redismultithreading.model.Grade;

import java.util.Set;

public record StudentRequestDto(String studentNumber, String firstName, String lastName, String phoneNumber,
                                Set<Grade> grades,
                                Address address) {
    public StudentRequestDto {
    }
}
