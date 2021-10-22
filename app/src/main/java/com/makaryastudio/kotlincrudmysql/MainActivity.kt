package com.makaryastudio.kotlincrudmysql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    lateinit var recyclerViewAdapter : RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycleView()
        initViewModel()
        searchProduct()

        launchProductActivity.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateProductActivity::class.java))
        }

    }

    private fun searchProduct()
    {
        searchProductButton.setOnClickListener {
            if (!TextUtils.isEmpty(searchEditText.text.toString()))
            {
                viewModel.searchProduct(searchEditText.text.toString());
            } else {
                viewModel.getProductList()
            }
        }
    }

    private fun initRecycleView()

    {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration((decoration))

            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
        }
    }

    fun initViewModel()
    {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getProductListObservable().observe(this, Observer<ProductList> {
            if (it == null)
            {
                Toast.makeText(this@MainActivity,"Data tidak ditemukan...", Toast.LENGTH_LONG).show()
            } else
            {
                recyclerViewAdapter.productList = it.data.toMutableList()
                recyclerViewAdapter.notifyDataSetChanged()
            }
        })
        viewModel.getProductList()
    }

    override fun onItemEditClick(product: Product) {
        val intent = Intent(this@MainActivity, CreateProductActivity::class.java)
        intent.putExtra("product_id", product.id)
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if(requestCode == 1000)
        {
            viewModel.getProductList()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }




}