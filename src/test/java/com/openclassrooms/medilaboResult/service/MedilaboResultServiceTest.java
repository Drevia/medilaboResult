package com.openclassrooms.medilaboResult.service;

import com.openclassrooms.medilaboResult.client.medilaboNote.MedilaboNoteClient;
import com.openclassrooms.medilaboResult.client.medilaboNote.model.PatientNote;
import com.openclassrooms.medilaboResult.client.medilaboPatient.MedilaboPatientClient;
import com.openclassrooms.medilaboResult.client.medilaboPatient.model.Patient;
import com.openclassrooms.medilaboResult.util.PatientTestUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MedilaboResultServiceTest {

    @Mock
    MedilaboPatientClient patientClient;

    @Mock
    MedilaboNoteClient medilaboNoteClient;

    @InjectMocks
    MedilaboResultService medilaboResultService;

    @ParameterizedTest
    @MethodSource("testMedilaboResult")
    void calculateRiskOk(List<PatientNote> patientNoteList, Patient patient, String resultExpected){

        when(medilaboNoteClient.findAllPatientNoteByPatientId(any())).thenReturn(patientNoteList);
        when(patientClient.findPatientById(any())).thenReturn(patient);

        String result = medilaboResultService.calculateRisk("1");

        assertEquals(resultExpected, result);
    }

    private static Stream<Arguments> testMedilaboResult(){
        return Stream.of(Arguments.of(PatientTestUtils.buildPatientNotesNone(),
                PatientTestUtils.buildPatientMaleMoreThan30Years(), "None"),
                Arguments.of(PatientTestUtils.buildPatientNotesFourKeyword(),
                        PatientTestUtils.buildPatientMaleMoreThan30Years(), "Borderline"),
                Arguments.of(PatientTestUtils.buildPatientNotesSixKeyword(),
                        PatientTestUtils.buildPatientMaleMoreThan30Years(), "InDanger"),
                Arguments.of(PatientTestUtils.buildPatientNotesEightKeyword(),
                        PatientTestUtils.buildPatientMaleMoreThan30Years(), "EarlyOnset"),
                Arguments.of(List.of(new PatientNote()),
                        PatientTestUtils.buildPatientMaleMoreThan30Years(), "None"),
                Arguments.of(PatientTestUtils.buildPatientNotesFourKeyword(),
                        PatientTestUtils.buildPatientFemaleLessThan30Years(), "InDanger"));
    }


}
