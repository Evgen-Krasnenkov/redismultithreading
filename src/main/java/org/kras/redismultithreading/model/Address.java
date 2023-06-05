package org.kras.redismultithreading.model;

import java.io.Serializable;

public record Address(String streetNumber, String streetName, String city, String province) implements Serializable {
}
