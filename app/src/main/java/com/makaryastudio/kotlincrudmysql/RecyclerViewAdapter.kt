package com.makaryastudio.kotlincrudmysql

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewJudul = view.textViewJudul
        val textViewDeskripsi = view.textViewDeskripsi

        fun bind(data: Product)
        {
            textViewJudul.text = data.judul
            textViewDeskripsi.text = data.deskripsi

        }
    }

}