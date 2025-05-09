package br.com.luizmariodev.ems.device.management.config.web;

import br.com.luizmariodev.ems.device.management.api.client.execption.SensorMonitoringExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;


@RestControllerAdvice
public class DeviceMangementExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            SocketTimeoutException.class,
            ConnectException.class,
            ClosedChannelException.class
    })
    public ProblemDetail handleIoExceptionTimeout(IOException ex) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.GATEWAY_TIMEOUT);
        problemDetail.setTitle(HttpStatus.GATEWAY_TIMEOUT.toString());
        problemDetail.setType(URI.create("/errors/gateway-timeout"));
        problemDetail.setStatus(HttpStatus.GATEWAY_TIMEOUT.value());
        return problemDetail;
    }

    @ExceptionHandler(SensorMonitoringExceptionHandler.class)
    public ProblemDetail handlerSensorMonitoringException(SensorMonitoringExceptionHandler ex) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_GATEWAY);
        problemDetail.setTitle(HttpStatus.BAD_GATEWAY.toString());
        problemDetail.setType(URI.create("/errors/bad-gateway"));
        problemDetail.setStatus(HttpStatus.BAD_GATEWAY.value());
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }
}
