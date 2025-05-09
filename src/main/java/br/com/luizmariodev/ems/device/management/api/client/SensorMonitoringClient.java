package br.com.luizmariodev.ems.device.management.api.client;

import io.hypersistence.tsid.TSID;

public interface SensorMonitoringClient {

    void enable(TSID sensorId);

    void disable(TSID sensorId);
}
