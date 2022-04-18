package com.ground.msa.order.service;

import com.ground.msa.order.model.PatientOrderDto;
import com.ground.msa.order.external.patient.PatientService;
import com.ground.msa.order.external.patient.model.PatientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    private final PatientService patientService;

    public PatientOrderDto getPatientOrderASync(String id) {
        long start = System.currentTimeMillis();
        log.debug("Start getPatientOrderASync :" + start);
        log.debug("before getPatient");
        Mono<PatientDto> patientDto = patientService.getPatient(id)
                .subscribeOn(Schedulers.parallel());

        log.debug("before getWelcome");
        Mono<String> welcome = patientService.getWelcome()
                .subscribeOn(Schedulers.parallel());

        Mono.zip(patientDto, welcome, (p, w) -> {
            PatientOrderDto patientOrderDto = new PatientOrderDto();
            patientOrderDto.setId(p.getId());
            patientOrderDto.setName(p.getName());
            patientOrderDto.setOrderCd("aspirin");
            patientOrderDto.setMessage(w.toString());
            log.debug("PatientOrderDto: " + patientOrderDto.toString());
            long end = System.currentTimeMillis();
            log.debug("End getPatientOrderASync :" + end);
            log.debug("Time gap : {}", (end-start)/1000.0 + "s");
            return patientOrderDto;
        }).subscribe();

        return null;
    }

    public PatientOrderDto getPatientOrderSync(String id) {
        long start = System.currentTimeMillis();
        log.debug("Start getPatientOrderASync :" + start);

        PatientOrderDto patientOrderDto = new PatientOrderDto();

        log.debug("before getPatient");
        PatientDto patientDto = patientService.getPatient(id).block();
        log.debug("after getPatient: " + patientDto.toString());

        patientOrderDto.setId(patientDto.getId());
        patientOrderDto.setName(patientDto.getName());

        patientOrderDto.setOrderCd("aspirin");

        log.debug("before getWelcome");
        String welcome = patientService.getWelcome().block();
        log.debug("after getWelcome: " + welcome);

        patientOrderDto.setMessage(welcome);
        log.debug("PatientOrderDto: " + patientOrderDto.toString());

        long end = System.currentTimeMillis();
        log.debug("End getPatientOrderASync :" + end);
        log.debug("Time gap : {}", (end-start)/1000.0 + "s");

        return patientOrderDto;
    }
}
