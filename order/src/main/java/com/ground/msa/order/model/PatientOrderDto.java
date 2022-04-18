package com.ground.msa.order.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PatientOrderDto {
    String id;
    String name;
    String orderCd;
    String Message;
}
