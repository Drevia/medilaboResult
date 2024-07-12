package com.openclassrooms.medilaboResult.controller;

import com.openclassrooms.medilaboResult.service.MedilaboResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MedilaboResultController {

    @Autowired
    private MedilaboResultService medilaboResultService;

    @GetMapping("/result/{patientId}")
    public String getMedilaboResult(@PathVariable String patientId) {
        return medilaboResultService.calculateRisk(patientId);
    }
}
