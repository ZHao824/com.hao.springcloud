package com.hao.springcloud.controller;

import com.hao.springcloud.entities.CommonResult;
import com.hao.springcloud.entities.Payment;
import com.hao.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int i = paymentService.create(payment);
        log.info("*********插入结果：" + i);
        log.info("*********插入结果：" + i);
        log.info("*********mster test：" + i);
        log.info("*********host-fix test：" + i);

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
            return new CommonResult(200,"查询成功",payment);
        }else{
            return new CommonResult(304,"查询失败",null);
        }
    }
}
