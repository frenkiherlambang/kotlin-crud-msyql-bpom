package com.makaryastudio.kotlincrudmysql

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateProductViewModel: ViewModel() {
    lateinit var createProductLiveData: MutableLiveData<ProductResponse?>
    lateinit var loadProductData: MutableLiveData<ProductResponse?>
    lateinit var deleteProductLiveData: MutableLiveData<ProductResponse?>

    init {
        createProductLiveData = MutableLiveData()
        loadProductData = MutableLiveData()
        deleteProductLiveData = MutableLiveData()
    }

    fun getCreateProductObservable(): MutableLiveData<ProductResponse?> {
        return createProductLiveData
    }
    fun getloadProductObservable(): MutableLiveData<ProductResponse?> {
        return loadProductData
    }

    fun getDeleteProductObservable(): MutableLiveData<ProductResponse?> {
        return deleteProductLiveData
    }

    fun createProduct(product: Product)
    {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.createProduct(product)
        Log.d("product", product.toString())
        call.enqueue(object: Callback<ProductResponse?> {
            override fun onFailure(call: Call<ProductResponse?>, t: Throwable) {
                Log.e("errors", t.message.toString())
            }

            override fun onResponse(call: Call<ProductResponse?>, response: Response<ProductResponse?>) {
                if(response.isSuccessful)
                {
                    createProductLiveData.postValue(response.body())
                }
            }
        })
    }

    fun updateProduct(product_id: String, product: Product)
    {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.updateProduct(product_id, product)
        Log.d("product", product.toString())
        call.enqueue(object: Callback<ProductResponse?> {
            override fun onFailure(call: Call<ProductResponse?>, t: Throwable) {
                createProductLiveData.postValue(null)
                Log.d("debug", "failed")
            }

            override fun onResponse(call: Call<ProductResponse?>, response: Response<ProductResponse?>) {
                if(response.isSuccessful)
                {
                    Log.d("debug", response.body().toString())
                    createProductLiveData.postValue(response.body())
                } else {
                    createProductLiveData.postValue(null)
                    Log.d("debug", response.body().toString())
                }
            }
        })
    }

    fun deleteProduct(product_id: String)
    {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.deleteProduct(product_id)
        call.enqueue(object: Callback<ProductResponse?> {
            override fun onFailure(call: Call<ProductResponse?>, t: Throwable) {
                deleteProductLiveData.postValue(null)
                Log.d("debug", "failed")
            }

            override fun onResponse(call: Call<ProductResponse?>, response: Response<ProductResponse?>) {
                if(response.isSuccessful)
                {
                    Log.d("debug", response.code().toString())
                    deleteProductLiveData.postValue(response.body())
                } else {
                    deleteProductLiveData.postValue(null)
                    Log.d("debu", response.code().toString())
                }
            }
        })
    }

    fun getProductData(product_id: String?)
    {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getProduct(product_id!!)
        call.enqueue(object: Callback<ProductResponse?> {
            override fun onFailure(call: Call<ProductResponse?>, t: Throwable) {
                Log.e("errors", t.message.toString())
            }

            override fun onResponse(call: Call<ProductResponse?>, response: Response<ProductResponse?>) {
                if(response.isSuccessful)
                {
                    loadProductData.postValue(response.body())
                }
            }
        })
    }

}