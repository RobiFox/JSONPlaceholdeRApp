package me.robi.jsonplaceholderapp.post

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import me.robi.jsonplaceholderapp.post.PostContent.PostItem
import me.robi.jsonplaceholderapp.databinding.FragmentPostBinding

/**
 * [RecyclerView.Adapter] that can display a [PostItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPostRecyclerViewAdapter(
    private val values: List<PostItem>
) : RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentPostBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}