package br.com.luizmariodev.ems.device.management.api.model.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SensorAlertInput {

    private BigDecimal maxTemperature;
    private BigDecimal minTemperature;
}
