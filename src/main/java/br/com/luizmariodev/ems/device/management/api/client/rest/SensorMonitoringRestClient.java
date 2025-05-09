package br.com.luizmariodev.ems.device.management.api.client.rest;

import br.com.luizmariodev.ems.device.management.api.client.SensorMonitoringClient;
import br.com.luizmariodev.ems.device.management.api.client.factory.RestClientFactory;
import br.com.luizmariodev.ems.device.management.api.model.output.SensorMonitoringOutput;
import io.hypersistence.tsid.TSID;
import org.springframework.web.client.RestClient;

// @Component
public class SensorMonitoringRestClient implements SensorMonitoringClient {

    private final RestClient restClient;

    public SensorMonitoringRestClient(RestClientFactory restClientFactory) {
        this.restClient = restClientFactory.createRestClientTemperatureMonitoring();
    }

    @Override
    public void enable(TSID sensorId) {
        restClient.put()
                .uri("/v1/sensors/{sensorId}/monitoring/enable", sensorId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void disable(TSID sensorId) {
        restClient.delete()
                .uri("/v1/sensors/{sensorId}/monitoring/enable", sensorId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public SensorMonitoringOutput detail(TSID sensorId) {
        return restClient.get()
                .uri("/v1/sensors/{sensorId}/monitoring", sensorId)
                .retrieve()
                .body(SensorMonitoringOutput.class);
    }
}
