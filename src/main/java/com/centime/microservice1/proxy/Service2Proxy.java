package com.centime.microservice1.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "${feign.service2.name}", url = "${feign.service2.url}")
public interface Service2Proxy {
    @RequestMapping("/hellomessage")
    String getGreetMessage();

    @RequestMapping("/actuator/health")
    String getHealthIndicator();
}
