package br.com.luizmariodev.ems.device.management.api.client;

import br.com.luizmariodev.ems.device.management.api.client.factory.RestClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class SensorMonitoringClientConfig {

    @Bean
    public SensorMonitoringClient sensorMonitoringClient(RestClientFactory restClientFactory) {
        var restClient = restClientFactory.createRestClientTemperatureMonitoring();

        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();

        return proxyFactory.createClient(SensorMonitoringClient.class);
    }
}
