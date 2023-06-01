package org.kras.redismultithreading.model;

import lombok.Builder;

import java.util.Set;

@Builder
public record Student(String studentNumber, String firstName, String lastName, String phoneNumber, Set<Grade> grades,
                      Address address) {
    public Student {
    }
}