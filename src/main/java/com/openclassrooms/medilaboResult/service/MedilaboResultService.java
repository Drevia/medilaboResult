package com.openclassrooms.medilaboResult.service;

import com.openclassrooms.medilaboResult.client.medilaboNote.MedilaboNoteClient;
import com.openclassrooms.medilaboResult.client.medilaboNote.model.PatientNote;
import com.openclassrooms.medilaboResult.client.medilaboPatient.MedilaboPatientClient;
import com.openclassrooms.medilaboResult.client.medilaboPatient.model.Gender;
import com.openclassrooms.medilaboResult.client.medilaboPatient.model.Patient;
import com.openclassrooms.medilaboResult.model.RiskWordEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.List;

@Service
public class MedilaboResultService {

    @Autowired
    private MedilaboPatientClient patientClient;

    @Autowired
    private MedilaboNoteClient noteClient;

    private static final String NO_RISK = "None";
    private static final String BORDERLINE = "Borderline";
    private static final String DANGER = "InDanger";
    private static final String EARLY_ONSET = "EarlyOnset";

    public String calculateRisk(String patientId){
        List<PatientNote> patientNoteList = noteClient.findAllPatientNoteByPatientId(patientId);
        Patient patient = patientClient.findPatientById(Long.valueOf(patientId));

        if (CollectionUtils.isEmpty(patientNoteList)){
            return NO_RISK;
        }

        int patientAge = getPatientAge(patient.getBirthDate());

        int riskWordCount = 0;
        for (PatientNote patientNote : patientNoteList) {

            for (RiskWordEnum riskWordEnum : RiskWordEnum.values()){
                if(StringUtils.containsIgnoreCase(patientNote.getNote(), riskWordEnum.getValue())){
                    riskWordCount++;
                }
            }

        }

        if(riskWordCount < 2){
            return NO_RISK;
        } else if (riskWordCount >=2 && riskWordCount <=5 && patientAge > 30) {
            return BORDERLINE;
        } else if((riskWordCount >=6 && riskWordCount <= 7 && patientAge >= 30) ||
                (patient.getGender().equals(Gender.MALE) && riskWordCount >=3 && riskWordCount <=4 && patientAge < 30) ||
                (patient.getGender().equals(Gender.FEMALE) && riskWordCount >= 4 && riskWordCount<=6 && patientAge < 30)) {
            return DANGER;
        } else if(riskWordCount >= 8 && patientAge >= 30 ||
                (patient.getGender().equals(Gender.MALE) && riskWordCount >=5 && patientAge < 30) ||
                (patient.getGender().equals(Gender.FEMALE) && riskWordCount >=7 && patientAge < 30)){
            return EARLY_ONSET;
        } else {
            return NO_RISK;
        }

    }

    private int getPatientAge(OffsetDateTime birthDate) {
        LocalDate birthLocalDate = birthDate.toLocalDate();
        // Obtenir la date actuelle
        LocalDate currentDate = LocalDate.now();
        // Calculer la période entre la date de naissance et la date actuelle
        Period period = Period.between(birthLocalDate, currentDate);
        // Retourner l'âge
        return period.getYears();
    }

}
