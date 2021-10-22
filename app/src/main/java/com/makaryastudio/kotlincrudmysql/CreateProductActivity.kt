package com.makaryastudio.kotlincrudmysql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_product.*

class CreateProductActivity : AppCompatActivity() {
    lateinit var viewModel: CreateProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)

        val product_id = intent.getStringExtra("product_id")

        initViewModel()
        createProductObservable()
        deleteProductObservable()

        if (product_id != null)
        {
            loadProductData(product_id)
        }

        createProductButton.setOnClickListener {
            createProduct(product_id)

        }

        deleteProductButton.setOnClickListener {
            deleteProduct(product_id)
        }
    }

    private fun loadProductData(product_id: String?) {
        viewModel.getloadProductObservable().observe(this, Observer<ProductResponse?> {
            if (it != null)
            {
                editTextJudul.setText(it.data?.judul)
                editTextDeskripsi.setText(it.data?.deskripsi)
                createProductButton.setText("Update")
            }
        })
        viewModel.getProductData(product_id)
    }

    fun createProduct(product_id: String?)
    {
        val product = Product("",editTextJudul.text.toString(), editTextDeskripsi.text.toString(),"https://via.placeholder.com/450")
        if (product_id == null)
        {
            viewModel.createProduct(product)
        } else {
            viewModel.updateProduct(product_id, product)
        }

    }

    fun deleteProduct(product_id: String?)
    {
        if (product_id != null)
        {
            Log.d("deleteProduct", product_id.toString())
            viewModel.deleteProduct(product_id)
        }
    }

    fun initViewModel()
    {
        viewModel = ViewModelProvider(this).get(CreateProductViewModel::class.java)
    }

    private fun createProductObservable()
    {
        viewModel.getCreateProductObservable().observe(this, Observer<ProductResponse?> {
            if (it == null)
            {
                Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                finish()
            }
        })

    }

    private fun deleteProductObservable()
    {
        viewModel.getDeleteProductObservable().observe(this, Observer<ProductResponse?> {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                finish()
        })

    }
}