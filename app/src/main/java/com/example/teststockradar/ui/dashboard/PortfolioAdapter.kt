package com.example.teststockradar.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teststockradar.R
import com.example.teststockradar.databinding.ListItemPortfolioBinding

class PortfolioAdapter(private val portfolioList: List<PortfolioItem>) : RecyclerView.Adapter<PortfolioAdapter.PortfolioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val binding = ListItemPortfolioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PortfolioViewHolder(binding)
    }

    override fun getItemCount(): Int = portfolioList.size

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        val item = portfolioList[position]
        holder.bind(item)
    }



    inner class PortfolioViewHolder(private val binding: ListItemPortfolioBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PortfolioItem) {
            binding.textTitle.text = item.title
            binding.textPendingPoint.text = item.formattedPendingPoint()

            if (item.pending_point <= 0) {
                binding.textPendingPoint.visibility = View.GONE
                binding.textShowPendingPoint.visibility = View.GONE
            } else {
                binding.textPendingPoint.visibility = View.VISIBLE
                binding.textShowPendingPoint.visibility = View.VISIBLE
            }

            if (item.change < 0.0){
                binding.textChange.setTextColor(binding.root.context.getColor(R.color.orange))
                binding.textChange.text = "(" + item.formattedChange() + ")"
            }
            else if(item.change > 0.0){
                binding.textChange.text = "(+" + item.formattedChange() + ")"
                binding.textChange.setTextColor(binding.root.context.getColor(R.color.anothergreen))

            }
            else{
                binding.textChange.text = "(" + item.formattedChange() + ")"
                binding.textChange.setTextColor(binding.root.context.getColor(R.color.yellow))
            }


            binding.textAll.text = item.formattedAll()

            Glide.with(binding.root.context).load(item.image_plan).into(binding.imagePlan)
        }
    }
}
