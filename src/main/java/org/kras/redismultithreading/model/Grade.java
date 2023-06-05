package org.kras.redismultithreading.model;

import java.io.Serializable;

public record Grade(String subject, String grade) implements Serializable {
}
