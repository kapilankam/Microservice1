package com.centime.microservice1.service;

import com.centime.microservice1.exception.ServiceNotAvailableException;
import com.centime.microservice1.model.HealthStatus;
import com.centime.microservice1.model.Person;
import com.centime.microservice1.proxy.Service2Proxy;
import com.centime.microservice1.proxy.Service3Proxy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Service;

/**
 * Interacts with Two Microservices
 */
@Service
public class EndpointService {
    final private Logger LOG = LoggerFactory.getLogger(this.getClass());
    private static String DOWN = "DOWN";


    @Autowired
    Service2Proxy service2Proxy;
    @Autowired
    Service3Proxy service3Proxy;

    /**
     * This method will check the health indicator of the other two services.
     */
    public Health getHealthCheckInfo() throws JsonProcessingException, ServiceNotAvailableException {
        try {
            service2Proxy.getHealthIndicator();
        } catch (Exception exception) {
            LOG.error(exception.getLocalizedMessage(), exception);
            throw new ServiceNotAvailableException("Service 2 is down");
        }
        try {
            service3Proxy.getHealthIndicator();
        } catch (Exception exception) {
            LOG.error(exception.getLocalizedMessage(), exception);
            throw new ServiceNotAvailableException("Service 3 is down");
        }
        return Health.up().build();
    }

    public String getConcatenatedResponse(Person person) {
        LOG.info("Calling Microservice2..");
        String service2ProxyGreetMessage = service2Proxy.getGreetMessage();
        LOG.info("Got successful response from  Microservice2..");
        LOG.info("Calling Microservice3..");
        String service3response = service3Proxy.concatenatedString(person);
        LOG.info("Got successful response from  Microservice3..");
        return service2ProxyGreetMessage + " " + service3response;
    }
}
