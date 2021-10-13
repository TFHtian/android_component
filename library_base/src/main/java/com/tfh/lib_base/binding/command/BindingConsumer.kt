package com.tfh.lib_base.binding.command

interface BindingConsumer<T> {

    fun call(t: T)
}