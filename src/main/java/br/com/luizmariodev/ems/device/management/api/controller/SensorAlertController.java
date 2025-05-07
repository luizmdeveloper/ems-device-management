package br.com.luizmariodev.ems.device.management.api.controller;

import br.com.luizmariodev.ems.device.management.api.model.input.SensorAlertInput;
import br.com.luizmariodev.ems.device.management.api.model.output.SensorAlertOutput;
import br.com.luizmariodev.ems.device.management.domain.service.SensorAlertService;
import io.hypersistence.tsid.TSID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sensors/{sensorId}/alerts")
public class SensorAlertController {

    private final SensorAlertService service;

    public SensorAlertController(SensorAlertService service) {
        this.service = service;
    }

    @GetMapping
    public SensorAlertOutput findById(@PathVariable TSID sensorId) {
        return service.findById(sensorId);
    }

    @PutMapping
    public SensorAlertOutput saveOrUpdate(@PathVariable TSID sensorId, @RequestBody SensorAlertInput sensorAlertInput) {
        return service.save(sensorId, sensorAlertInput);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable TSID sensorId) {
        service.delete(sensorId);
    }
}
