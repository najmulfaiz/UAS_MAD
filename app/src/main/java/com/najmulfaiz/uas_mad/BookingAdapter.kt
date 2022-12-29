package com.najmulfaiz.uas_mad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.najmulfaiz.uas_mad.model.BookingItem

class BookingAdapter(private val data: List<BookingItem>?) : RecyclerView.Adapter<BookingAdapter.BookingHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): BookingHolder {
        return BookingHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_booking, viewGroup, false))
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: BookingHolder, position: Int) {
        holder.onBind(data?.get(position))
    }

    class BookingHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvBookingTanggal = view!!.findViewById<TextView>(R.id.tvBookingTanggal)
        private val tvBookingNopol = view!!.findViewById<TextView>(R.id.tvBookingNopol)
        private val tvBookingJenisKendaraan = view!!.findViewById<TextView>(R.id.tvBookingJenisKendaraan)

        fun onBind(data: BookingItem?) {
            tvBookingTanggal.text = data?.tanggal
            tvBookingNopol.text = data?.nopol
            tvBookingJenisKendaraan.text = data?.jenis_kendaraan?.nama
        }
    }
}