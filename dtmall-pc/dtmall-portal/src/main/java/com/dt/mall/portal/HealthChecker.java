package com.dt.mall.portal;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author jason.yang
 * @Description 心跳健康检查
 * @Date 2022-08-26 22:01
 */
public class HealthChecker implements IPing {

    @Override
    public boolean isAlive(Server server) {
        String url="http://"+server.getId()+"/healthStatus";
        boolean isAlive=true;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpUriRequest request=new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(request);
            isAlive = response.getStatusLine().getStatusCode() == 200;
        }catch (Exception e){
            isAlive=false;
        }finally {
            request.abort();
        }
        return isAlive;
    }

}