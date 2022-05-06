package com.hao.springcloud.controller;

import com.hao.springcloud.entities.CommonResult;
import com.hao.springcloud.entities.Payment;
import com.hao.springcloud.service.PaymentService;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("*********插入结果：" + i);
        log.info("*********插入结果：" + i);
        log.info("*********mster test：" + i);
        log.info("*********host-fix test：" + i);
        log.info("*********push test：" + i);
        log.info("*********pull test：" + i);
        
        if(i > 0){
            return new CommonResult(200,"插入数据库成功",i);
        }else{
            return new CommonResult(500,"插入数据库失败",null);
        }
    }

    @GetMapping(value = "/payment/getPaymentById/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*********插入结果：" + payment + "hhhh");

        if(payment != null){
            return new CommonResult(200,"查询成功"+"\t 服务端口："+serverPort,payment);
        }else{
            return new CommonResult(304,"查询失败",null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.discoveryClient;
    }
}
