package me.robi.jsonplaceholderapp.posts.individual

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import me.robi.jsonplaceholderapp.R
import me.robi.jsonplaceholderapp.user.ARG_ID

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
const val PARAM_TITLE = "title"
const val PARAM_BODY = "body"
const val PARAM_USER = "user"

/**
 * A simple [Fragment] subclass.
 * Use the [IndividualPostFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IndividualPostFragment : Fragment() {
    private var paramTitle: String? = null
    private var paramBody: String? = null
    private var paramUser: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramTitle = it.getString(PARAM_TITLE)
            paramBody = it.getString(PARAM_BODY)
            paramUser = it.getInt(PARAM_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_individual_post, container, false)
        view.findViewById<TextView>(R.id.individual_post_title_header).text = arguments.let { paramTitle }
        view.findViewById<TextView>(R.id.individual_post_body_text).text = arguments.let { paramBody }
        view.findViewById<Button>(R.id.individual_post_user_button).let {
            it.text = String.format(getString(R.string.check_profile), arguments.let { paramUser })
            it.setOnClickListener{
                findNavController().navigate(R.id.action_individualPostFragment_to_userProfileFragment,
                bundleOf(
                    ARG_ID to arguments.let { paramUser }
                ))
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param titleParam
         * @param bodyParam
         * @param userId
         * @return A new instance of fragment IndividualPostFragment.
         */
        @JvmStatic
        fun newInstance(titleParam: String, bodyParam: String, userId: Int) =
            IndividualPostFragment().apply {
                arguments = Bundle().apply {
                    putString(PARAM_TITLE, titleParam)
                    putString(PARAM_BODY, bodyParam)
                    putInt(PARAM_USER, userId)
                }
            }
    }
}