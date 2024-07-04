package com.openclassrooms.medilaboResult.client.medilaboPatient;

import com.openclassrooms.medilaboResult.client.medilaboPatient.model.Patient;
import com.openclassrooms.medilaboResult.client.medilaboPatient.model.PatientDto;
import com.openclassrooms.medilaboResult.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8091/api", name = "MedilaboPatient", configuration = FeignConfiguration.class)
public interface MedilaboPatientClient {

    @GetMapping("/patient")
    List<Patient> findAllPatient();

    @GetMapping("/patient/{id}")
    Patient findPatientById(@PathVariable Long id);

    @PatchMapping("/patient/{id}")
    Patient updatePatient(PatientDto patient, @PathVariable Long id);
}
