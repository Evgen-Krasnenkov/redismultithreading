package org.kras.redismultithreading.model.dto;

import lombok.Data;

import java.util.List;

public record GradeResponseDto(String studentNumber, List<GradeDto> gradeDtos) {
}
