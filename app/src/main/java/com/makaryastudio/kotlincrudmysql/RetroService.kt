package com.makaryastudio.kotlincrudmysql

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetroService {

    @GET("products")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getProductList(): Call<ProductList>

}