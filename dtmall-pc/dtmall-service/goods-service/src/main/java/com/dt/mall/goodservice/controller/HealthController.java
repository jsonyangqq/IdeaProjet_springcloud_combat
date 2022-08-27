package com.dt.mall.goodservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jason.yang
 * @Description 健康检查的服务响应类
 * @Date 2022-08-26 22:02
 */
@Slf4j
@RestController
public class HealthController {

    @GetMapping("/healthStatus")
    public String healthStatus() {
        log.info("healthCheck...");
        return "SUCCESS";
    }

}