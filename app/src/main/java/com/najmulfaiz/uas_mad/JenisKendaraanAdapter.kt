package com.najmulfaiz.uas_mad

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.najmulfaiz.uas_mad.model.JenisKendaraanItem
import com.najmulfaiz.uas_mad.model.ProvinceItem
import com.najmulfaiz.uas_mad.model.ProvinceResponse

@Suppress("NAME_SHADOWING")
class JenisKendaraanAdapter internal constructor(internal var context: Context, internal var list: List<JenisKendaraanItem>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
        var view = view
        if (view == null) {
            val inflater = LayoutInflater.from(context)

            view = inflater.inflate(R.layout.adapter_jenis_kendaraan, viewGroup, false)
        }

        val tvJenisKendaraan = view!!.findViewById<TextView>(R.id.tvJenisKendaraan)

        tvJenisKendaraan.text = list[i].nama

        return tvJenisKendaraan

    }
}