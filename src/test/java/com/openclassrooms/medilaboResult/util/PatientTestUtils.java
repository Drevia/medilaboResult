package com.openclassrooms.medilaboResult.util;

import com.openclassrooms.medilaboResult.client.medilaboNote.model.PatientNote;
import com.openclassrooms.medilaboResult.client.medilaboPatient.model.Gender;
import com.openclassrooms.medilaboResult.client.medilaboPatient.model.Patient;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class PatientTestUtils {

    public static List<PatientNote> buildPatientNotesNone(){
        PatientNote patientNote = new PatientNote();
        patientNote.setPatientId("1");
        patientNote.setPatientName("name");
        patientNote.setNote("Rien à signaler");

        return List.of(patientNote);

    }

    public static List<PatientNote> buildPatientNotesBorderline(){
        PatientNote patientNote = new PatientNote();
        patientNote.setPatientId("1");
        patientNote.setPatientName("name");
        patientNote.setNote("Cholestérol Vertiges Rechute Anticorps");

        return List.of(patientNote);

    }

    public static List<PatientNote> buildPatientNotesInDanger(){
        PatientNote patientNote = new PatientNote();
        patientNote.setPatientId("1");
        patientNote.setPatientName("name");
        patientNote.setNote("Cholestérol Vertiges Rechute Anticorps Poids Microalbumine");

        return List.of(patientNote);

    }

    public static List<PatientNote> buildPatientNotesEarlyOnSet(){
        PatientNote patientNote = new PatientNote();
        patientNote.setPatientId("1");
        patientNote.setPatientName("name");
        patientNote.setNote("Cholestérol Vertiges Rechute Anticorps Poids Microalbumine Hémoglobine A1C Fumeur");

        return List.of(patientNote);

    }

    public static Patient buildPatientMaleMoreThan30Years(){
        Patient patient = new Patient();
        patient.setGender(Gender.MALE);
        patient.setId(1L);
        ZoneOffset offset = ZoneOffset.UTC;
        patient.setBirthDate(OffsetDateTime.of(LocalDateTime.of(1966, 12, 31, 0, 0), offset));
        return patient;
    }

    public static Patient buildPatientMaleLessThan30Years(){
        Patient patient = new Patient();
        patient.setGender(Gender.MALE);
        patient.setId(1L);
        ZoneOffset offset = ZoneOffset.UTC;
        patient.setBirthDate(OffsetDateTime.of(LocalDateTime.of(2002, 12, 31, 0, 0), offset));
        return patient;
    }

    public static Patient buildPatientFemaleMoreThan30Years(){
        Patient patient = new Patient();
        patient.setGender(Gender.FEMALE);
        patient.setId(1L);
        ZoneOffset offset = ZoneOffset.UTC;
        patient.setBirthDate(OffsetDateTime.of(LocalDateTime.of(1966, 12, 31, 0, 0), offset));
        return patient;
    }

    public static Patient buildPatientFemaleLessThan30Years(){
        Patient patient = new Patient();
        patient.setGender(Gender.FEMALE);
        patient.setId(1L);
        ZoneOffset offset = ZoneOffset.UTC;
        patient.setBirthDate(OffsetDateTime.of(LocalDateTime.of(2002, 12, 31, 0, 0), offset));
        return patient;
    }
}
