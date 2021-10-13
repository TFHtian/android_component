package com.tfh.library_common.data.event

/**
 * @author tianfenghui
 * @date 2021/4/21.
 * @description eventbus发送数据封装类
 */
open class EventMessage<T>(t: T, var code: Int) {
    var value = t
}