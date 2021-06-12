package com.centime.microservice1.proxy;

import com.centime.microservice1.model.Person;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${feign.service3.name}", url = "${feign.service3.url}")
public interface Service3Proxy {

    @RequestMapping(method = RequestMethod.POST, path = "/cancatenate")
    @Headers("Content-Type: application/json")
    String concatenatedString(@RequestBody Person person);

    @RequestMapping("/actuator/health")
    String getHealthIndicator();
}
