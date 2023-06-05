package org.kras.redismultithreading.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
public class Student implements Serializable {
    private String studentNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Grade> grades;
    private Address address;
}