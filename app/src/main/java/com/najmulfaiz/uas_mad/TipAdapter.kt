package com.najmulfaiz.uas_mad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.najmulfaiz.uas_mad.model.TipItem

class TipAdapter(private val data: List<TipItem>?, val listener: OnAdapterListener) : RecyclerView.Adapter<TipAdapter.TipHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): TipHolder {
        return TipHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_tip, viewGroup, false))
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: TipHolder, position: Int) {
        holder.onBind(data?.get(position))
        holder.itemView.setOnClickListener {
            listener.showDetail(data?.get(position))
        }
    }

    class TipHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTipTitle = view!!.findViewById<TextView>(R.id.tvTipTitle)

        fun onBind(data: TipItem?) {
            tvTipTitle.text = data?.title

            Glide.with(itemView.context).load(data?.image).into(itemView.findViewById(R.id.ivTip));
        }
    }

    interface OnAdapterListener {
        fun showDetail(data: TipItem?)
    }
}