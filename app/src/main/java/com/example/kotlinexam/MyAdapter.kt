package com.example.kotlinexam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinexam.databinding.ItemRecyclerviewBinding

class MyAdapter(val context: Context, var list: List<TestEntity>, var onDeleteListener: OnDeleteListener) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entity = list[position]

        holder.text.text = entity.text
        holder.root.setOnLongClickListener ( object : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                onDeleteListener.onDeleteListener(entity)
                return true
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val text = binding.tvText
        val root = binding.root
    }
}
