package com.app.dailyjounral.interfaces

interface OnItemSelected<T> {
    fun onItemSelected(t: T?, position: Int)
}