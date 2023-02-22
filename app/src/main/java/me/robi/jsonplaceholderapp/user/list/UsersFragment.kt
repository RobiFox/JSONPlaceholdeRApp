package me.robi.jsonplaceholderapp.user.list

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.robi.jsonplaceholderapp.R
import me.robi.jsonplaceholderapp.fragments.ICacheable
import me.robi.jsonplaceholderapp.fragments.JsonFragment
import org.json.JSONArray

/**
 * A fragment representing a list of Items.
 */
class UsersFragment : JsonFragment(), ICacheable {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun getUrl(bundle: Bundle?): String {
        return "https://jsonplaceholder.typicode.com/users";
    }

    override fun applyData(response: String) {
        val jsonArray = JSONArray(response);
        for(i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i);
            UserContent.addItem(
                UserContent.UserItem(
                    item["id"] as Int,
                    item["name"].toString()
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyUsersRecyclerViewAdapter(UserContent.ITEMS, this@UsersFragment)
            }
        }
        super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun reFetchIfCached(): Boolean {
        return true;
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            UsersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}