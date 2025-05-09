package br.com.luizmariodev.ems.device.management.api.model.output;

import io.hypersistence.tsid.TSID;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class SensorMonitoringOutput implements Serializable {

    private TSID id;
    private BigDecimal lastTemparature;
    private OffsetDateTime updateAt;
    private boolean enabled;

}
