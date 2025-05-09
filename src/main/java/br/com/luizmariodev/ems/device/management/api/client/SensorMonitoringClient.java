package br.com.luizmariodev.ems.device.management.api.client;

import br.com.luizmariodev.ems.device.management.api.model.output.SensorMonitoringOutput;
import io.hypersistence.tsid.TSID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange("/v1/sensors/{sensorId}/monitoring")
public interface SensorMonitoringClient {

    @PutExchange("/enable")
    void enable(@PathVariable TSID sensorId);

    @DeleteExchange("/enable")
    void disable(@PathVariable TSID sensorId);

    @GetExchange
    SensorMonitoringOutput detail(@PathVariable TSID sensorId);
}
