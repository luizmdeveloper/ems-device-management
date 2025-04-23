package br.com.luizmariodev.ems.device.management.api.controller;

import br.com.luizmariodev.ems.device.management.api.model.input.SensorInput;
import br.com.luizmariodev.ems.device.management.api.model.output.SensorOutput;
import br.com.luizmariodev.ems.device.management.domain.service.SensorService;
import io.hypersistence.tsid.TSID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/sensors")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SensorOutput create(@RequestBody SensorInput sensorInput){
        return service.save(sensorInput);
    }

    @GetMapping("{sensorId}")
    public SensorOutput findOne(@PathVariable TSID sensorId) {
        return service.findOne(sensorId);
    }

    @GetMapping
    public Page<SensorOutput> findAll(@PageableDefault Pageable pageable) {
        return service.findAll(pageable);
    }

    @PutMapping("{sensorId}")
    public SensorOutput update(@PathVariable TSID sensorId, @RequestBody SensorInput sensorInput) {
        return service.update(sensorId, sensorInput);
    }
}
