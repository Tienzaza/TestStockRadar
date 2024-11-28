package com.example.teststockradar.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.teststockradar.R
import com.example.teststockradar.databinding.ListItemStockBinding

class StockAdapter(private val data: List<StockData>) : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val binding = ListItemStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val stock = data[position]
        holder.binding.apply {
            textShortName.text = stock.short_name
            textPrice.text = stock.formattedPrice()

            if (stock.change > 50){
                textChange.text = "+" + stock.formattedChange()
                stockCardView.backgroundTintList = ContextCompat.getColorStateList(holder.binding.root.context, R.color.moregreen)
            }
            else if (stock.change > 0){
                textChange.text = "+" + stock.formattedChange()
                stockCardView.backgroundTintList = ContextCompat.getColorStateList(holder.binding.root.context, R.color.green)
            }
            else {
                textChange.text = stock.formattedChange()
                stockCardView.backgroundTintList = ContextCompat.getColorStateList(holder.binding.root.context, R.color.red)
            }


            if (stock.percent_change > 0){
                textPercentChange.text = "+" + stock.formattedPercentChange()
            }
            else {
                textPercentChange.text = stock.formattedPercentChange()
            }

        }
    }



    class StockViewHolder(val binding: ListItemStockBinding) : RecyclerView.ViewHolder(binding.root)
}
