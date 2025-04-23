package br.com.luizmariodev.ems.device.management.domain.service;

import br.com.luizmariodev.ems.device.management.api.model.input.SensorInput;
import br.com.luizmariodev.ems.device.management.api.model.output.SensorOutput;
import br.com.luizmariodev.ems.device.management.common.IdGenerator;
import br.com.luizmariodev.ems.device.management.domain.model.Sensor;
import br.com.luizmariodev.ems.device.management.domain.model.SensorId;
import br.com.luizmariodev.ems.device.management.domain.repository.SensorRepository;
import io.hypersistence.tsid.TSID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SensorService {

    private final SensorRepository repository;

    public SensorService(SensorRepository repository){
        this.repository = repository;
    }

    public SensorOutput save(SensorInput sensorInput) {
        var sensor = convertInputToEntity(sensorInput);
        repository.save(sensor);
        return convertEntityToOuput(sensor);
    }

    public SensorOutput convertEntityToOuput(Sensor sensor) {
        var response = new SensorOutput();
        response.setId(sensor.getId().getValue());
        response.setIp(sensor.getIp());
        response.setLocation(sensor.getLocation());
        response.setName(sensor.getName());
        response.setProtocol(sensor.getProtocol());
        response.setModel(sensor.getModel());
        return response;
    }

    public Sensor convertInputToEntity(SensorInput sensorInput) {
        var sensor = new Sensor();
        sensor.setId(new SensorId(IdGenerator.generateTSID()));
        sensor.setIp(sensorInput.getIp());
        sensor.setName(sensorInput.getName());
        sensor.setLocation(sensorInput.getLocation());
        sensor.setProtocol(sensorInput.getProtocol());
        sensor.setModel(sensorInput.getModel());
        sensor.setEnabled(Boolean.FALSE);
        return sensor;
    }

    public SensorOutput findOne(TSID id) {
        var optionalSensor = repository.findById(new SensorId(id));

        if (optionalSensor.isPresent()) {
            return convertEntityToOuput(optionalSensor.get());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Page<SensorOutput> findAll(Pageable pageable) {
        var pages = repository.findAll(pageable);
        return pages.map(this::convertEntityToOuput);
    }

    public SensorOutput update(TSID sensorId, SensorInput sensorInput) {
        var optionalSensor = repository.findById(new SensorId(sensorId));

        if (optionalSensor.isPresent()) {
            var sensor = optionalSensor.get();
            sensor.setIp(sensorInput.getIp());
            sensor.setName(sensorInput.getName());
            sensor.setModel(sensorInput.getModel());
            sensor.setProtocol(sensorInput.getProtocol());
            sensor.setLocation(sensorInput.getLocation());
            repository.save(sensor);
            return convertEntityToOuput(sensor);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void delete(TSID sensorId) {
        var optionalSensor = repository.findById(new SensorId(sensorId));

        if (!optionalSensor.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var sensor = optionalSensor.get();
        repository.delete(sensor);
    }
}
