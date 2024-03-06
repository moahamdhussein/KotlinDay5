package com.example.kotlinday5.main

import com.example.kotlinday5.model.Products


interface Communicator {
    fun respond(data: Products)
}