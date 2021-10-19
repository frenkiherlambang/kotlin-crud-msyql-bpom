package com.makaryastudio.kotlincrudmysql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter : RecyclerViewAdapter
    lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycleView()
        initViewModel()

    }

    private fun initRecycleView()

    {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            addItemDecoration((decoration))

            recyclerViewAdapter = RecyclerViewAdapter()
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



}