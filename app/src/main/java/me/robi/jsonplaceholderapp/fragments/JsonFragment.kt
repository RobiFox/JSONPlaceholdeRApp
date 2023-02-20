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
import me.robi.jsonplaceholderapp.CacheSingleton
import java.net.URL

abstract class JsonFragment : Fragment() {
    val scope = CoroutineScope(Dispatchers.IO)

    abstract fun getUrl(bundle: Bundle?) : String;
    abstract fun applyData(response: String);

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val url = getUrl(savedInstanceState);
        if(this is ICacheable && CacheSingleton.caches.containsKey(url)) {
            CacheSingleton.caches[url]?.let { applyData(it) };
            if(!this.reFetchIfCached()) return view;
        }
        scope.launch {
            val response = getPosts(url)
            activity?.runOnUiThread {
                applyData(response);
                if(this is ICacheable) {
                    CacheSingleton.caches[url] = response;
                }
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