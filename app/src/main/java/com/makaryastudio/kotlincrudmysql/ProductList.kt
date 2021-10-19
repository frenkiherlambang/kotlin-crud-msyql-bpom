package com.makaryastudio.kotlincrudmysql

data class ProductList (val data: List<Product>)

data class Product(
    val id: Int?,
    val judul: String?,
    val deskripsi: String?,
    val foto: String?
 )

data class ProductResponse(val code: Int?, val meta: String?, val data: Product?)