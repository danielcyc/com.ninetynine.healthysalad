package com.example.domain

data class Item (
    var header: Header,
    var body : Body
)
{
    fun priceString(price: Float ) : String {
        return price.toString()
    }
}

data class Header(
    val code : Int,
    val message : String?
)

data class Body (
    var data : Data
)

data class Data (
    var base : List<Base>
)

data class Base (
    val name : String,
    val price: Float,
    val currency: String
)