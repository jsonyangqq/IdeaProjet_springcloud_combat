package com.dt.mall.marktingservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jason.yang
 * @Description 营销活动服务提供
 * @Date 2022-08-25 22:43
 */
@RestController
@Slf4j
public class PromotionService {


    @GetMapping("/promotion")
    public String getPromotionById(){
        return "查询到指定商品的促销信息";
    }

}