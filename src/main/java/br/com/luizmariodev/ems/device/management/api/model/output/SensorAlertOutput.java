package br.com.luizmariodev.ems.device.management.api.model.output;

import io.hypersistence.tsid.TSID;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SensorAlertOutput implements Serializable {

    private TSID id;
    private BigDecimal maxTemperature;
    private BigDecimal minTemperature;
}
