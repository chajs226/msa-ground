package com.ground.msa.order.external.patient;

import com.ground.msa.order.external.patient.model.PatientDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
@Slf4j
public class PatientService {

    //private final WebClient webClient;

    public Mono<PatientDto> getPatient(String id) {
        log.debug("in getPatient");
        return WebClient.create().get() //webClient.get()
                .uri("http://localhost:8091/api/patients/" + id)
                .retrieve()
                .bodyToMono(PatientDto.class);
    }

    public Mono<String> getWelcome() {
        log.debug("in getWelcome");
        return WebClient.create().get()
                .uri("http://localhost:8091/api/patients/welcome/")
                .retrieve()
                .bodyToMono(String.class);
    }

}
