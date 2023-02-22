package me.robi.jsonplaceholderapp.user.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import me.robi.jsonplaceholderapp.R
import me.robi.jsonplaceholderapp.databinding.FragmentUserBinding
import me.robi.jsonplaceholderapp.user.individual.ARG_USER_ID

import me.robi.jsonplaceholderapp.user.list.UserContent.UserItem

/**
 * [RecyclerView.Adapter] that can display a [UserItem].
 */
class MyUsersRecyclerViewAdapter(
    private val values: List<UserItem>,
    private val fragment: Fragment
) : RecyclerView.Adapter<MyUsersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.name.toString()
        holder.itemView.setOnClickListener {
            fragment.findNavController().navigate(
                R.id.action_usersFragment_to_userProfileFragment,
                bundleOf(
                    ARG_USER_ID to item.id
                )
            )
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentUserBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}