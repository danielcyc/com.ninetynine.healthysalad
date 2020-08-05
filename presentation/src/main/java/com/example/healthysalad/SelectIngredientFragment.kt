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
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Data
import kotlinx.android.synthetic.main.fragment_select_ingredient.view.*
import java.lang.StringBuilder
import java.util.*

private const val INGREDIENT_TYPE: String  = "ingredientType"

class SelectIngredientFragment () : Fragment() {
    private val type: String by lazy {
        arguments?.getString(INGREDIENT_TYPE).toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_select_ingredient,container, false)
        Log.d("tag", "The current ingredient type is: "+ type)
        val stringBuilder : StringBuilder = StringBuilder()
        stringBuilder.append("Select ", type.capitalize())
        val selectIngredientText : String = stringBuilder.toString()
        view.select_ingredient.text = selectIngredientText

        val repoData = Data.repoWithKey(type)
        val viewManager: RecyclerView.LayoutManager = GridLayoutManager(view.context, 2)
        val viewAdapter: ViewAdapter = ViewAdapter(repoData)
        val recyclerView : RecyclerView = view.findViewById(R.id.ingredient_recycler_view)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter

        Data.loadItem(type) { dataResponse ->
            viewAdapter.updateMyDataset(dataResponse)
            Log.d("repoData Callback:" , repoData.toString())
        }

        //Log.d("Prev Frag ", FragmentScheduler(INGREDIENTTYPE).getPrevState())
        Log.d("Next Frag ", FragmentScheduler(type).getNextState())

        val openBackFrag: Button = view.findViewById(R.id.back_button)
        openBackFrag.setOnClickListener {
            if (type == FragmentScheduler(type).getFirstState()) {
                activity!!.supportFragmentManager
                    .beginTransaction().replace(
                        R.id.activity_main, TitleFragment()
                        )
                    .addToBackStack(null).commit()
            }
             else {
                activity!!.supportFragmentManager
                    .beginTransaction().replace(
                        R.id.activity_main, newInstance(
                            FragmentScheduler(
                                type
                            ).getPrevState()
                        )
                    )
                    .addToBackStack(null).commit()
                Log.d("Prev Frag ", FragmentScheduler(type).getPrevState())
            }
        }

        val openNextFrag: Button = view.findViewById(R.id.next_button)
        openNextFrag.setOnClickListener{
            if (type == FragmentScheduler(type).getLastState()){
                activity!!.supportFragmentManager
                    .beginTransaction().replace(R.id.activity_main, OrderSummaryFragment())
                    .addToBackStack(null).commit()
                //Log.d("Next Frag ", FragmentScheduler(INGREDIENTTYPE).getNextState())

        }
            else {
                activity!!.supportFragmentManager
                    .beginTransaction().replace(R.id.activity_main, newInstance(FragmentScheduler(
                        type).getNextState()))
                    .addToBackStack(null).commit()
                //Log.d("Next Frag ", FragmentScheduler(INGREDIENTTYPE).getNextState())

            }
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(ingredientType : String) =
            SelectIngredientFragment().apply {
                arguments = Bundle().apply {
                    putString(INGREDIENT_TYPE, ingredientType)
                }
            }
    }
}
