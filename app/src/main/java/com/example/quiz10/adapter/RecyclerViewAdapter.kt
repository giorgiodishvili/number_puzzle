package com.example.quiz10.adapter

import android.util.Log.i
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quiz10.databinding.ButtonLayoutBinding

class RecyclerViewAdapter(
    private val numbers: Array<IntArray>,
) : RecyclerView.Adapter<RecyclerViewAdapter.ChildRecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildRecyclerViewHolder {
        return ChildRecyclerViewHolder(ButtonLayoutBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ChildRecyclerViewHolder, position: Int) = holder.onBind()

    override fun getItemCount(): Int = numbers.size * numbers.size

    inner class ChildRecyclerViewHolder(private val binding: ButtonLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            val row = adapterPosition / numbers.size
            val col = adapterPosition % numbers.size
            if(numbers[row] [col]==0) return
            binding.button.text = numbers[row] [col].toString();

            binding.button.setOnClickListener {
                i("FETCHH","HEREEE $row  $col")
                when {
                    fetchN(numbers,row,col) -> {
                        swap(row,col,row-1,col)
                    }
                    fetchS(numbers,row,col) -> {
                        swap(row,col,row+1,col)

                    }
                    fetchE(numbers,row,col) -> {
                        swap(row,col,row,col+1)
                    }
                    fetchW(numbers,row,col) -> {
                        swap(row,col,row,col-1)

                    }
                }
            }
        }

        private fun fetchN(array: Array<IntArray>, rw: Int, cl: Int): Boolean {
            if (rw - 1 >= 0) {
                val north = array[rw - 1][cl]
                if(north == 0){
                    return true
                }
            }
            return false
        }

        private fun fetchS(array: Array<IntArray>, rw: Int, cl: Int): Boolean {
            if (rw+1< array.size) {
                val south = array[rw + 1][cl]
                if(south == 0){
                    return true
                }
            }
            return false
        }

        private fun fetchW(array: Array<IntArray>, rw: Int, cl: Int): Boolean {
            if (cl-1>=0 ) {
                val west = array[rw][cl-1]
                if(west == 0){
                    return true
                }
            }
            return false

        }
        private fun fetchE(array: Array<IntArray>, rw: Int, cl: Int): Boolean {
            if (cl+1< array[cl].size) {
                val east = array[rw][cl+1]
                if(east == 0){
                    return true
                }
            }
            return false
        }

        private fun swap(row: Int, col: Int, newrow: Int, newcol: Int){
            i("inSwap","$row $col $newrow $newcol")
            run { val temp = numbers[newrow][newcol]; numbers[newrow][newcol] = numbers[row][col]; numbers[row][col] = temp;i("here",temp.toString()) }
            notifyDataSetChanged()
        }

    }


}
