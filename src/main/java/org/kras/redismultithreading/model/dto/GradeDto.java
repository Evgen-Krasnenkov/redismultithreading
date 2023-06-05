package org.kras.redismultithreading.model.dto;

import org.kras.redismultithreading.model.Grade;

import java.util.List;

public record GradeDto(String studentNumber, List<Grade> grades) {
}
