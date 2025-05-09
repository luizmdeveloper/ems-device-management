package br.com.luizmariodev.ems.device.management.api.client;

import br.com.luizmariodev.ems.device.management.api.model.output.SensorMonitoringOutput;
import io.hypersistence.tsid.TSID;

public interface SensorMonitoringClient {

    void enable(TSID sensorId);

    void disable(TSID sensorId);

    SensorMonitoringOutput detail(TSID sensorId);
}
