package com.example.kotlinday5.model



data class UpperClassPojo(
    var products: MutableList<Products>,
    var total: Int,
    var skip: Int,
    var limit: Int,
)