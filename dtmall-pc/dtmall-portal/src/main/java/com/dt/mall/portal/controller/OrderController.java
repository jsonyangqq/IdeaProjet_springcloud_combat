package com.dt.mall.portal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @author jason.yang
 * @Description 门户下单接口提供者
 * @Date 2022-08-25 22:46
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private LoadBalancerClient loadbalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("#{'${goodsService.serverList}'.split(',')}")
    private List<String> goodsList;

    private String getGoodsServer(){
        Random random=new Random();
        return goodsList.get(random.nextInt(goodsList.size()));
    }

    @GetMapping("/order")
    public String order(){
        log.info("begin do order");
        String goodsInfo=restTemplate.getForObject(getGoodsServer(),String.class);
        // 没有哪些中间件提供的情况下，我们写死调用服务
        String promotionInfo=restTemplate.getForObject("http://localhost:8080/promotion",String.class);
        MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
        param.add("goodsInfo",goodsInfo);
        param.add("pomotionInfo",promotionInfo);

        HttpEntity<MultiValueMap<String,Object>> httpEntity=new HttpEntity<>(param,new HttpHeaders());
        ResponseEntity<String> response=restTemplate.postForEntity("http://localhost:9090/order",httpEntity,String.class);
        return response.getBody();
    }

    @GetMapping("/order2")
    public String order2(){
        log.info("begin do order");
        // 我们可以通过LoadBalancerClient.choose("服务名称")获取到相对应的服务信息
//        ServiceInstance si=loadbalancerClient.choose("goods-service");
//        String url=String.format("http://%s:%s",si.getHost(),si.getPort());
//        log.info("ribbon-url:{}",url);
        String goodsInfo=restTemplate.getForObject("http://goods-service/goods",String.class);
        // 没有哪些中间件提供的情况下，我们写死调用服务
        String promotionInfo=restTemplate.getForObject("http://markting-service/promotion",String.class);
        MultiValueMap<String,Object> param=new LinkedMultiValueMap<>();
        param.add("goodsInfo",goodsInfo);
        param.add("pomotionInfo",promotionInfo);

        HttpEntity<MultiValueMap<String,Object>> httpEntity=new HttpEntity<>(param,new HttpHeaders());
        ResponseEntity<String> response=restTemplate.postForEntity("http://order-service/order",httpEntity,String.class);
        return response.getBody();
    }

}