package com.in28minutes.springboot.microservice.example.currencyconversion

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

@RestController
class CurrencyConversionController {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var proxy: CurrencyExchangeServiceProxy

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    fun convertCurrency (
            @PathVariable from: String, @PathVariable to: String,
            @PathVariable quantity: BigDecimal
    ) : CurrencyConversionBean {

        val uriVariables: MutableMap<String, String> = HashMap()
        uriVariables["from"] = from
        uriVariables["to"] = to

        logger.info("From {} -> To {}", from, to)
        logger.info("Quantity {}", quantity.toString())

        val responseEntity = RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean::class.java, uriVariables
        )

        val response = responseEntity.body

        return CurrencyConversionBean(response!!.id, from, to, response.conversionMultiple, quantity,
                quantity.multiply(response.conversionMultiple), response.port)
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    fun convertCurrencyFeign (
            @PathVariable("from") from: String, @PathVariable("to") to: String,
            @PathVariable("quantity") quantity: BigDecimal
    ): CurrencyConversionBean {

        val response: CurrencyConversionBean = proxy.retrieveExchangeValue(from, to)

        logger.info("{}", response)

        return CurrencyConversionBean(response.id, from, to, response.conversionMultiple, quantity,
                quantity.multiply(response.conversionMultiple), response.port)
    }
}