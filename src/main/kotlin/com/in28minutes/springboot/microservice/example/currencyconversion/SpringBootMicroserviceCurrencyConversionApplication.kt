package com.in28minutes.springboot.microservice.example.currencyconversion

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients("com.in28minutes.springboot.microservice.example.currencyconversion")
@EnableDiscoveryClient
class SpringBootMicroserviceCurrencyConversionApplication

fun main(args: Array<String>) {
    runApplication<SpringBootMicroserviceCurrencyConversionApplication>(*args)
}
