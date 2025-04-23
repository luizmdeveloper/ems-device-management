package br.com.luizmariodev.ems.device.management.api.model.input;

import lombok.Data;

import java.io.Serializable;

@Data
public class SensorInput implements Serializable {

    private String name;
    private String protocol;
    private String model;
    private String location;
    private String ip;

}
