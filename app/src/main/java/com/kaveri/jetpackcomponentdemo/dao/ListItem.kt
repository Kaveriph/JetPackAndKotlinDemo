package com.kaveri.jetpackcomponentdemo.dao

sealed class ListItem {
    data class Name(var firstName: String, var lastName: String) : ListItem()
    data class HeadLine(var header: String) : ListItem()
}
