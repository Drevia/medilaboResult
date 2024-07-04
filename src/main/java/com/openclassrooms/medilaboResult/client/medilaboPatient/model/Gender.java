package com.openclassrooms.medilaboResult.client.medilaboPatient.model;

import lombok.ToString;

@ToString
public enum Gender {

    MALE("Male"), FEMALE("Female");
    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
