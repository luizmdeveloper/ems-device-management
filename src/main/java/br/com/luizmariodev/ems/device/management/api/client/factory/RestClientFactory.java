package br.com.luizmariodev.ems.device.management.api.client.factory;

import br.com.luizmariodev.ems.device.management.api.client.execption.SensorMonitoringExceptionHandler;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Component
public class RestClientFactory {

    private final RestClient.Builder builder;

    public RestClientFactory(RestClient.Builder builder) {
        this.builder = builder;
    }

    public RestClient createRestClientTemperatureMonitoring() {
        return builder
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

}
