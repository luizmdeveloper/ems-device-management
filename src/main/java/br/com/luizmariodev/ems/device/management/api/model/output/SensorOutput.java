package br.com.luizmariodev.ems.device.management.api.model.output;

import io.hypersistence.tsid.TSID;
import lombok.Data;

import java.io.Serializable;

@Data
public class SensorOutput implements Serializable {

    private TSID id;
    private String name;
    private String protocol;
    private String model;
    private String location;
    private String ip;

}
