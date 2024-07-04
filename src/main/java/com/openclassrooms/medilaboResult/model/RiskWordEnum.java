package com.openclassrooms.medilaboResult.model;

import lombok.ToString;

@ToString
public enum RiskWordEnum {

    HEMOGLOBINE("Hémoglobine A1C"), MICROALBUMINE("Microalbumine"), TAILLE("Taille"),
    POIDS("Poids"), FUMEUR("Fumeur"), FUMEUSE("Fumeuse"), ANORMAL("Anormal"),
    CHOLESTEROL("Cholestérol"), VERTIGES("Vertiges"), RECHUTE("Rechute"), REACTION("Réaction"), ANTICORPS("Anticorps");

    private final String value;

    RiskWordEnum(String value) {this.value = value;}

    public String getValue(){
        return value;
    }
}
