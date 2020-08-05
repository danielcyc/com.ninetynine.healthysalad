package com.example.healthysalad

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_title,container, false)
        val selectIngredientFragment = SelectIngredientFragment.newInstance(getString(R.string.first_select_ingredient_fragment))
        val openChooseFrag: Button = view.findViewById(R.id.create_salad_button)
        openChooseFrag.setOnClickListener {
            activity!!.supportFragmentManager
                .beginTransaction().replace(R.id.activity_main, selectIngredientFragment)
                .addToBackStack(null).commit()
        }
        return view
    }
    companion object {
        @JvmStatic
        fun newInstance() =
            TitleFragment().apply {}
    }
}