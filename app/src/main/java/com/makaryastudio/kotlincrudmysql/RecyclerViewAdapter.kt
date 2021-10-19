package com.makaryastudio.kotlincrudmysql

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var productList = mutableListOf<Product>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewJudul = view.textViewJudul
        val textViewDeskripsi = view.textViewDeskripsi
        val imageViewFoto = view.imageViewFoto


        fun bind(data: Product)
        {
            textViewJudul.text = data.judul
            textViewDeskripsi.text = data.deskripsi
            Picasso.get().load(data.foto).into(imageViewFoto);
        }
    }

}