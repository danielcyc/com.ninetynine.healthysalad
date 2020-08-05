package com.example.healthysalad

import ViewAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Order

class OrderSummaryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_order_summary, container,false)
        val viewManager: RecyclerView.LayoutManager = LinearLayoutManager(view.context)
        val viewAdapter: OrderSummaryViewAdapter = OrderSummaryViewAdapter(Order)
        val recyclerView : RecyclerView = view.findViewById(R.id.order_summary_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter
        val openBackFrag: Button = view.findViewById(R.id.back_button)
        openBackFrag.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.activity_main, SelectIngredientFragment.newInstance(getString(R.string.last_ingredient_fragment)) // TODO: make select ingredient fragment scheduler be able to be accessed
            ).addToBackStack(null).commit()
            }
        return view
    }
}