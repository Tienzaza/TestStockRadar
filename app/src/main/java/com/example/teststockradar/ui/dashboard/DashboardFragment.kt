package com.example.teststockradar.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teststockradar.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var portfolioAdapter: PortfolioAdapter
    private val dashboardViewModel: DashboardViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root


        portfolioAdapter = PortfolioAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = portfolioAdapter


        dashboardViewModel.portfolioItems.observe(viewLifecycleOwner, Observer { items ->
            portfolioAdapter = PortfolioAdapter(items)
            binding.recyclerView.adapter = portfolioAdapter
        })

        dashboardViewModel.fetchPortfolio()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}