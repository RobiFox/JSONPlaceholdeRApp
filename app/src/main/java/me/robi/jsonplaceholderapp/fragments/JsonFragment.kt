package me.robi.jsonplaceholderapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.robi.jsonplaceholderapp.posts.PostContent
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

abstract class JsonFragment : Fragment() {
    val scope = CoroutineScope(Dispatchers.IO)

    abstract fun getUrl() : String;
    abstract fun applyData(response: String);

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        scope.launch {
            applyData(getPosts(getUrl()));
            activity?.runOnUiThread {
                view?.requestLayout()
            }
        }
        return view
    }

    suspend fun getPosts(url: String): String {
        return withContext(scope.coroutineContext) {
            URL(url).readText()
        }
    }
}