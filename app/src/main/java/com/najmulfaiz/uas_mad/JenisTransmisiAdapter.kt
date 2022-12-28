package com.najmulfaiz.uas_mad

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.najmulfaiz.uas_mad.model.JenisKendaraanItem
import com.najmulfaiz.uas_mad.model.JenisTransmisiItem
import com.najmulfaiz.uas_mad.model.ProvinceItem
import com.najmulfaiz.uas_mad.model.ProvinceResponse

@Suppress("NAME_SHADOWING")
class JenisTransmisiAdapter internal constructor(internal var context: Context, internal var list: List<JenisTransmisiItem>) : BaseAdapter() {
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

            view = inflater.inflate(R.layout.adapter_jenis_transmisi, viewGroup, false)
        }

        val tvJenisTransmisi = view!!.findViewById<TextView>(R.id.tvJenisTransmisi)

        tvJenisTransmisi.text = list[i].nama

        return tvJenisTransmisi

    }
}