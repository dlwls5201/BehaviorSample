package com.blackjin.behaviorsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SampleAdapter : RecyclerView.Adapter<SampleAdapter.SampleViewHolder>() {

    private val items = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SampleViewHolder(parent)

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        //..
    }

    override fun getItemCount() = items.size

    fun notifySample() {
        items.clear()
        (1..10).forEach {
            items.add(it)
        }
        notifyDataSetChanged()
    }

    class SampleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_sample, parent, false),
    )
}