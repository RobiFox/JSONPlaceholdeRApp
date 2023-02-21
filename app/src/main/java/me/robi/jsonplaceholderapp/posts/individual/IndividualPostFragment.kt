package me.robi.jsonplaceholderapp.posts.individual

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import me.robi.jsonplaceholderapp.R
import me.robi.jsonplaceholderapp.fragments.ICacheable
import me.robi.jsonplaceholderapp.fragments.JsonFragment
import me.robi.jsonplaceholderapp.posts.list.PostContent
import me.robi.jsonplaceholderapp.user.ARG_USER_ID
import org.json.JSONObject

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val ARG_POST = "id"

/**
 * A simple [Fragment] subclass.
 * Use the [IndividualPostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IndividualPostFragment : Fragment() {
    private var paramPost: PostContent.PostItem? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramPost = it.getSerializable(ARG_POST, PostContent.PostItem::class.java)
        }
    }

    /*override fun applyData(response: String) {
        val jsonObject = JSONObject(response);
        view!!.findViewById<TextView>(R.id.individual_post_title_header).text = paramPost?.content
        view!!.findViewById<TextView>(R.id.individual_post_body_text).text = paramPost?.details
        view!!.findViewById<Button>(R.id.individual_post_user_button).let {
            it.text = String.format(getString(R.string.check_profile), arguments.let { paramPost })
            it.setOnClickListener{
                findNavController().navigate(R.id.action_individualPostFragment_to_userProfileFragment,
                    bundleOf(
                        ARG_USER_ID to arguments.let { jsonObject.getInt("userId") }
                    ))
            }
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_individual_post, container, false)
        view!!.findViewById<TextView>(R.id.individual_post_title_header).text = paramPost?.content
        view.findViewById<TextView>(R.id.individual_post_body_text).text = paramPost?.details
        view.findViewById<Button>(R.id.individual_post_user_button).let {
            it.text = String.format(getString(R.string.check_profile), arguments.let { paramPost?.authorId })
            it.setOnClickListener{
                findNavController().navigate(R.id.action_individualPostFragment_to_userProfileFragment,
                    bundleOf(
                        ARG_USER_ID to arguments.let { paramPost?.authorId }
                    ))
            }
        }
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
                    putInt(ARG_POST, id)
                }
            }
    }
}