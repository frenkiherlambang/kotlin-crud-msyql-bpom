package com.makaryastudio.kotlincrudmysql

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    lateinit var recycleLiveData: MutableLiveData<ProductList>

    init {
        recycleLiveData = MutableLiveData()
    }

    fun getProductListObservable() : MutableLiveData<ProductList> {
        return recycleLiveData
    }

    fun getProductList()
    {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getProductList()
        call.enqueue(object: Callback<ProductList> {
            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                Log.e("errors", t.message.toString())
            }

            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                if(response.isSuccessful)
                {
                    recycleLiveData.postValue(response.body())
                }
            }
        })
    }

    fun searchProduct(searchText: String)
    {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.searchProducts(searchText)
        call.enqueue(object: Callback<ProductList> {
            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                Log.e("errors", t.message.toString())
            }

            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                if(response.isSuccessful)
                {
                    recycleLiveData.postValue(response.body())
                }
            }
        })
    }
}