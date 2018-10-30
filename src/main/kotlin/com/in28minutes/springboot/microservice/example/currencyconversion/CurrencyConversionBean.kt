package com.in28minutes.springboot.microservice.example.currencyconversion

import java.math.BigDecimal


data class CurrencyConversionBean (

    var id: Long? = null,
    var from: String? = null,
    var to: String? = null,
    var conversionMultiple: BigDecimal?= null,
    var quantity: BigDecimal? = null,
    var totalCalculatedAmount: BigDecimal? = null,
    var port: Int? = null
)

