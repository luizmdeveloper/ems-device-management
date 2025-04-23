package br.com.luizmariodev.ems.device.management.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "sensors")
@Data
public class Sensor {

    @Id
    @EqualsAndHashCode.Include
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BIGINT"))
    private SensorId id;
    private String name;
    private String protocol;
    private String model;
    private String location;
    private String ip;
    private Boolean enabled;
}
