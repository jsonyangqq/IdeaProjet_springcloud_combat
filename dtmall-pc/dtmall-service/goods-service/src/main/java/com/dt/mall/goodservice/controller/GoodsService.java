package com.dt.mall.goodservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jason.yang
 * @Description 商品服务接口
 * @Date 2022-08-25 22:40
 */
@RestController
@Slf4j
public class GoodsService {

    @Value("${server.port}")
    private String port;

    /**
     * 根据ID查询商品信息
     * @return
     */
    @GetMapping("/goods")
    public String getGoodsById(){
        log.info("收到请求，端口为：{}",port);
        return "返回商品信息";
    }

}