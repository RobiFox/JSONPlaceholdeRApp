package me.robi.jsonplaceholderapp.posts.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.robi.jsonplaceholderapp.R

import me.robi.jsonplaceholderapp.posts.list.PostContent.PostItem
import me.robi.jsonplaceholderapp.databinding.FragmentPostBinding
import me.robi.jsonplaceholderapp.posts.individual.*

/**
 * [RecyclerView.Adapter] that can display a [PostItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyPostRecyclerViewAdapter(
    private val values: List<PostItem>,
    private val fragment: Fragment
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
        holder.body.text = item.details;
        holder.authorId.text = item.authorId.toString();

        holder.itemView.setOnClickListener {
            fragment.findNavController().navigate(R.id.action_postFragment_to_individualPostFragment,
                bundleOf(
                    PARAM_TITLE to item.content,
                    PARAM_BODY to item.details,
                    PARAM_USER to item.authorId,
                ))
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentPostBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val body: TextView = binding.details
        val authorId: TextView = binding.authorId

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}