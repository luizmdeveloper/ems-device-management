package br.com.luizmariodev.ems.device.management.api.model.output;

import lombok.Data;

import java.io.Serializable;

@Data
public class SensorDetailOutput implements Serializable {

    private SensorOutput sensor;
    private SensorMonitoringOutput monitoring;

}
