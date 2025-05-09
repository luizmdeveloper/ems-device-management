package br.com.luizmariodev.ems.device.management.api.client.rest;

import br.com.luizmariodev.ems.device.management.api.client.SensorMonitoringClient;
import io.hypersistence.tsid.TSID;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SensorMonitoringRestClient implements SensorMonitoringClient {

    private final RestClient restClient;

    public SensorMonitoringRestClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://localhost:8082").build();
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
}
