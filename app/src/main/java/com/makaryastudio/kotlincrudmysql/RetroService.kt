package com.makaryastudio.kotlincrudmysql

import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    @GET("products")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getProductList(): Call<ProductList>

    @GET("products/{product_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getProduct(@Path("product_id") product_id: String): Call<ProductResponse>

    @GET("products")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun searchProducts(@Query("judul") searchText: String): Call<ProductList>

    @POST("products")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun createProduct(@Body params: Product): Call <ProductResponse>

    @PATCH("products/{product_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun updateProduct(@Path("product_id") product_id: String, @Body params: Product): Call <ProductResponse>

    @DELETE("products/{product_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun deleteProduct(@Path("product_id") product_id: String): Call <ProductResponse>




}