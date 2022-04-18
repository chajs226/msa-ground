package com.ground.msa.patient.adapter.api;

import com.ground.msa.patient.adapter.api.model.PatientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ExtPatientController {

    @GetMapping("/api/patients/{id}")
    public PatientDto getPatient(@PathVariable String id) throws InterruptedException {
        log.debug("getPatient id: " + id);
        PatientDto patientDto = new PatientDto();
        patientDto.setId("1");
        patientDto.setName("James");
        Thread.sleep(3000);
        return patientDto;
    }

    @GetMapping("/api/patients/welcome/")
    public String welcome() throws InterruptedException {
        Thread.sleep(3000);
        return "Welcome!!!";
    }
}
