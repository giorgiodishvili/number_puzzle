package com.example.quiz10.ui.home

import android.util.Log.i
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.android.football.base.BaseFragment
import com.example.quiz10.adapter.RecyclerViewAdapter
import com.example.quiz10.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {
    private lateinit var adapter: RecyclerViewAdapter

    override fun start(inflater: LayoutInflater, container: ViewGroup?) {

        adapter = RecyclerViewAdapter(populateList2())
        binding.recycler.layoutManager =
            GridLayoutManager(context, 3)
        binding.recycler.adapter = adapter
    }

    private fun populateList(): ArrayList<Int> {
        val shuffledList = ArrayList((0..8).toList())
        shuffledList.shuffle()
        return shuffledList;
    }

    private fun populateList2(): Array<IntArray> {
        val values = Array(3) { IntArray(3) }
        val randList = populateList()
        var k = 0
        for (i in values.indices) {
            // do the for in the row according to the column size
            for (j in values[i].indices) {
                // multiple the random by 10 and then cast to in
                values[i][j] = randList[k++]
                i("HERE", values[i][j].toString())
            }
        }
        return values;
    }
}