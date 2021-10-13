package com.tfh.lib_base.binding.command

interface BindingFunction<T> {

    fun call(): T
}