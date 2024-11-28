package com.example.teststockradar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.teststockradar.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var stockAdapter: StockAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textLastUpdate
        homeViewModel.lastUpdate.observe(viewLifecycleOwner) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
            try {
                val date = inputFormat.parse(it)
                val formattedDate = outputFormat.format(date)
                textView.text = "Last Update: $formattedDate"
            } catch (e: Exception) {
                e.printStackTrace()
                textView.text = "Last Update: $it"
            }
        }

        val layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.layoutManager = layoutManager

        homeViewModel.stockData.observe(viewLifecycleOwner) { data ->
            stockAdapter = StockAdapter(data)
            binding.recyclerView.adapter = stockAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
