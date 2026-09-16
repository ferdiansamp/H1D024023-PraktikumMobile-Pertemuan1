package com.example.pertemuan1.data.model

import android.content.ClipDescription

data class Product(
    val id: Int,
    val category_id: Int,
    val category: Category?,
    val name: String,
    val description: String?,
    val price: Double,
    val stock: Int,
    val img: String
)
