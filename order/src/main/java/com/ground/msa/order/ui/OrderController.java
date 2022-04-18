package com.ground.msa.order.ui;

import com.ground.msa.order.external.patient.model.PatientDto;
import com.ground.msa.order.model.PatientOrderDto;
import com.ground.msa.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/order/patientInfo/{id}/ASync")
    public PatientOrderDto getPatientOrderASync(@PathVariable String id) {
        return orderService.getPatientOrderASync(id);
    }

    @GetMapping("/order/patientInfo/{id}/Sync")
    public PatientOrderDto getPatientOrderSync(@PathVariable String id) {
        return orderService.getPatientOrderSync(id);
    }
}
