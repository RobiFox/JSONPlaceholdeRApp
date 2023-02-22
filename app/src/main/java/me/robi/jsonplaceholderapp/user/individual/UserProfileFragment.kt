package me.robi.jsonplaceholderapp.user.individual

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.robi.jsonplaceholderapp.R
import me.robi.jsonplaceholderapp.fragments.ICacheable
import me.robi.jsonplaceholderapp.fragments.JsonFragment
import org.json.JSONObject

public const val ARG_USER_ID = "id"

/**
 * A simple [Fragment] subclass.
 * Use the [UserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserProfileFragment : JsonFragment(), ICacheable {
    private var paramId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramId = it.getInt(ARG_USER_ID)
        }
    }

    override fun getUrl(bundle: Bundle?): String {
        return String.format("https://jsonplaceholder.typicode.com/users/%s", bundle.let {
            paramId
        })
    }

    override fun applyData(response: String) {
        val user = JSONObject(response);
        view!!.findViewById<TextView>(R.id.user_profile_name).text = user.getString("name")
        view!!.findViewById<TextView>(R.id.user_profile_email).text = user.getString("email")
        view!!.findViewById<TextView>(R.id.user_profile_phone).text = user.getString("phone")
        view!!.findViewById<TextView>(R.id.user_profile_website).text = user.getString("website")

        val company = user.getJSONObject("company");

        view!!.findViewById<TextView>(R.id.user_profile_company_name).text = company.getString("name")
        view!!.findViewById<TextView>(R.id.user_profile_company_catchphrase).text = company.getString("catchPhrase")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)
        super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param name Parameter 1.
         * @param email Parameter 2.
         * @return A new instance of fragment UserProfileFragment.
         */
        @JvmStatic
        fun newInstance(id: Int) =
            UserProfileFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_USER_ID, id)
                }
            }
    }

    override fun reFetchIfCached(): Boolean {
        return false
    }
}