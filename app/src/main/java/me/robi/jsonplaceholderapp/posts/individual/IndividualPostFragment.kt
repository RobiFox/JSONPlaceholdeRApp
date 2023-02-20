package me.robi.jsonplaceholderapp.posts.individual

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import me.robi.jsonplaceholderapp.R
import me.robi.jsonplaceholderapp.fragments.ICacheable
import me.robi.jsonplaceholderapp.fragments.JsonFragment
import me.robi.jsonplaceholderapp.user.ARG_USER_ID
import org.json.JSONObject

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val ARG_POST_ID = "id"

/**
 * A simple [Fragment] subclass.
 * Use the [IndividualPostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IndividualPostFragment : JsonFragment(), ICacheable {
    private var paramId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramId = it.getInt(ARG_POST_ID)
        }
    }

    override fun getUrl(bundle: Bundle?): String {
        return String.format("https://jsonplaceholder.typicode.com/posts/%s", bundle.let {
            paramId
        })
    }

    override fun applyData(response: String) {
        val jsonObject = JSONObject(response);
        view!!.findViewById<TextView>(R.id.individual_post_title_header).text = jsonObject.getString("title")
        view!!.findViewById<TextView>(R.id.individual_post_body_text).text = jsonObject.getString("body")
        view!!.findViewById<Button>(R.id.individual_post_user_button).let {
            it.text = String.format(getString(R.string.check_profile), arguments.let { paramId })
            it.setOnClickListener{
                findNavController().navigate(R.id.action_individualPostFragment_to_userProfileFragment,
                    bundleOf(
                        ARG_USER_ID to arguments.let { jsonObject.getInt("userId") }
                    ))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_individual_post, container, false)
        super.onCreateView(inflater, container, savedInstanceState)

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param id
         * @return A new instance of fragment IndividualPostFragment.
         */
        @JvmStatic
        fun newInstance(id: Int) =
            IndividualPostFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POST_ID, id)
                }
            }
    }

    override fun reFetchIfCached(): Boolean {
        return false
    }
}