package me.robi.jsonplaceholderapp.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.robi.jsonplaceholderapp.R
import me.robi.jsonplaceholderapp.fragments.JsonFragment
import org.json.JSONArray
import java.net.URL

/**
 * A fragment representing a list of Items.
 */
class PostFragment : JsonFragment() {

    private var columnCount = 1
    override fun getUrl(): String {
        return "https://jsonplaceholder.typicode.com/posts";
    }

    override fun applyData(response: String) {
        val jsonArray = JSONArray(response);
        for(i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i);
            println(item.toString());
            PostContent.addItem(
                PostContent.PostItem(
                    i.toString(),
                    item["title"].toString(),
                    item["body"].toString(),
                    item["userId"] as Int
                ))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        PostContent.clear()
        val view = inflater.inflate(R.layout.fragment_post_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyPostRecyclerViewAdapter(PostContent.ITEMS)
            }
        }
        super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            PostFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}