package com.conde.kun.randomusers.view.user.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.conde.kun.randomusers.R
import com.conde.kun.randomusers.domain.model.User
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UserDetailFragment : Fragment() {

    private lateinit var viewModel: UserDetailViewModel

    companion object {
        private val KEY_USER = "user"

        fun newInstance(user: User): UserDetailFragment {
            val fr = UserDetailFragment()
            val args = Bundle()
            args.putParcelable(KEY_USER, user)
            fr.arguments = args
            return fr
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = getViewModel()
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onViewCreated(arguments?.getParcelable(KEY_USER))
    }
}