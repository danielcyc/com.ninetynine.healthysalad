package com.example.domain

object Order {
    private val order : MutableList<Base> = mutableListOf()
    fun addItem(base : Base) {
        this.order.add(base)
    }
    fun getOrder(): MutableList<Base> {
        return this.order
    }
}
