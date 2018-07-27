package com.eatchicken.go.extension

import android.view.View

/**判断一个列表是不是null或者empty*/
fun List<Any>?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> List<*>.checkItemsAre() =
        if (all { it is T })
            this as List<T>
        else emptyList()

fun <T> List<T>?.toArrayList(): ArrayList<T> {
    return this?.mapTo(kotlin.collections.arrayListOf()) { it } ?: arrayListOf()
}

fun <T> List<T>.firstObject(): T? {
    return if (isEmpty()) null
    else this[0]
}

fun View.gone(){
    this.visibility = View.GONE
}

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun View.isVisible():Boolean = this.visibility == View.VISIBLE