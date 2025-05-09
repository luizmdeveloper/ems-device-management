package br.com.luizmariodev.ems.device.management.api.client.rest;

import br.com.luizmariodev.ems.device.management.api.client.SensorMonitoringClient;
import br.com.luizmariodev.ems.device.management.api.client.execption.SensorMonitoringExceptionHandler;
import io.hypersistence.tsid.TSID;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Component
public class SensorMonitoringRestClient implements SensorMonitoringClient {

    private final RestClient restClient;

    public SensorMonitoringRestClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("http://localhost:8082")
                .requestFactory(generateClientHttpRequestFactory())
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                    throw new SensorMonitoringExceptionHandler();
                })
                .build();
    }

    private ClientHttpRequestFactory generateClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();

        simpleClientHttpRequestFactory.setReadTimeout(Duration.ofSeconds(20));
        simpleClientHttpRequestFactory.setConnectTimeout(Duration.ofSeconds(10));

        return simpleClientHttpRequestFactory;
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
