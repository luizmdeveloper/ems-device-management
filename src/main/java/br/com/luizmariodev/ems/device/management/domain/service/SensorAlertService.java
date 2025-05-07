package br.com.luizmariodev.ems.device.management.domain.service;

import br.com.luizmariodev.ems.device.management.api.model.input.SensorAlertInput;
import br.com.luizmariodev.ems.device.management.api.model.output.SensorAlertOutput;
import br.com.luizmariodev.ems.device.management.domain.model.SensorAlert;
import br.com.luizmariodev.ems.device.management.domain.model.SensorId;
import br.com.luizmariodev.ems.device.management.domain.repository.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SensorAlertService {

    private final SensorAlertRepository repository;

    public SensorAlertService(SensorAlertRepository repository) {
        this.repository = repository;
    }

    public SensorAlertOutput findById(TSID sensorId) {
        var opitionalSensorAlert = repository.findById(new SensorId(sensorId));

        if (opitionalSensorAlert.isPresent()) {
            return convertEntityToOutput(opitionalSensorAlert.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    public SensorAlertOutput save(TSID sensorId, SensorAlertInput sensorAlertInput) {
        var sensorAlert = new SensorAlert();
        sensorAlert.setId(new SensorId(sensorId));
        sensorAlert.setMinTemperature(sensorAlertInput.getMinTemperature());
        sensorAlert.setMaxTemperature(sensorAlertInput.getMaxTemperature());
        repository.saveAndFlush(sensorAlert);
        return convertEntityToOutput(sensorAlert);
    }


    public void delete(TSID sensorId) {
        var opitionalSensorAlert = repository.findById(new SensorId(sensorId));

        if (opitionalSensorAlert.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        repository.delete(opitionalSensorAlert.get());
    }

    private SensorAlertOutput convertEntityToOutput(SensorAlert sensorAlert) {
        var output = new SensorAlertOutput();
        output.setId(sensorAlert.getId().getValue());
        output.setMinTemperature(sensorAlert.getMinTemperature());
        output.setMaxTemperature(sensorAlert.getMaxTemperature());
        return output;
    }
}
