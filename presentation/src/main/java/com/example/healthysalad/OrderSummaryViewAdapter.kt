package com.example.healthysalad

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Base
import com.example.domain.Order

class OrderSummaryViewAdapter(private val myDataset: Order) :
    RecyclerView.Adapter<ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycler_item_view, parent, false)
        // set the view's size, margins, paddings and layout parameters
        //holder.textView.text = myDataset.get(position).name
        Log.d("LOG", "view holder created ")
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val item = myDataset.getOrder()[position]
        holder.itemButton.text = item.name

    }

    override fun getItemCount(): Int {
        return myDataset.getOrder().size
    }
}
