package com.openclassrooms.medilaboResult.client.medilaboNote;


import com.openclassrooms.medilaboResult.client.medilaboNote.model.PatientNote;
import com.openclassrooms.medilaboResult.client.medilaboNote.model.PatientNoteDto;
import com.openclassrooms.medilaboResult.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(url = "http://localhost:8093/api", name = "MedilaboNote", configuration = FeignConfiguration.class)
public interface MedilaboNoteClient {

    @GetMapping("/note")
    List<PatientNote> findAllPatientNote();

    @GetMapping("/note/{id}")
    List<PatientNote> findAllPatientNoteByPatientId(@PathVariable String id);

    @PostMapping("/note")
    PatientNote createPatientNote(PatientNoteDto patientNote);
}
