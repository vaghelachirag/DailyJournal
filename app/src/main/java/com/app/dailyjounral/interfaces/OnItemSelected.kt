package com.app.secureglobal.interfaces

interface OnItemSelected<T> {
    fun onItemSelected(t: T?, position: Int)
}