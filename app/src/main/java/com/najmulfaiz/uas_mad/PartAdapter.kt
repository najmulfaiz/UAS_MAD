package com.najmulfaiz.uas_mad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.najmulfaiz.uas_mad.model.BookingItem
import com.najmulfaiz.uas_mad.model.PartItem

class PartAdapter(private val data: List<PartItem>?) : RecyclerView.Adapter<PartAdapter.PartHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): PartHolder {
        return PartHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_part, viewGroup, false))
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: PartHolder, position: Int) {
        holder.onBind(data?.get(position))
    }

    class PartHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvPartNama = view!!.findViewById<TextView>(R.id.tvPartNama)
        private val tvPartCategory = view!!.findViewById<TextView>(R.id.tvPartCategory)
        private val tvPartHarga = view!!.findViewById<TextView>(R.id.tvPartHarga)

        fun onBind(data: PartItem?) {
            tvPartNama.text = data?.nama
            tvPartCategory.text = " (${data?.part_category?.nama})"
            tvPartHarga.text = " Rp${data?.harga}"

            Glide.with(itemView.context).load(data?.image).into(itemView.findViewById(R.id.ivPart));
        }
    }
}