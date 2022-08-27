## 原始服务请求通过RestTemplate的二种方式

### 方式一：原始Http封装请求，通过手动写死服务地址
![img.png](images/img.png)

### 方式二： 集成Ribbon后，通过ribbon配置服务地址，通过服务名称访问
![img.png](images/img2.png)


## 后来我们引入Eureka注册中心后，可以通过在注册中心拿到服务地址，再做请求
![img.png](images/img3.png)




