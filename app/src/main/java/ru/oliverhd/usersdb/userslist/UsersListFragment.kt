package ru.oliverhd.usersdb.userslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ru.oliverhd.usersdb.AppState
import ru.oliverhd.usersdb.R
import ru.oliverhd.usersdb.data.User
import ru.oliverhd.usersdb.databinding.FragmentUsersListBinding
import ru.oliverhd.usersdb.userdetail.UserDetailFragment

class UsersListFragment : Fragment() {

    private val viewModel: UsersListViewModel by viewModels()
    private var _binding: FragmentUsersListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UsersListRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        initFab()
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Loading -> {
                binding.usersListLoadingLayout.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success<*> -> {
                binding.usersListLoadingLayout.loadingLayout.visibility = View.GONE
                initRecycler(state.data as List<User>)
            }
            is AppState.Error -> {
                binding.usersListLoadingLayout.loadingLayout.visibility = View.GONE
                Toast.makeText(context, state.error.message, Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun initRecycler(data: List<User>) {
        adapter = UsersListRVAdapter(object : UsersListRVAdapter.OnRecyclerItemClickListener {
            override fun onItemClick(userId: Int) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentContainer, UserDetailFragment.newInstance(userId))
                    ?.addToBackStack("")
                    ?.commit()
            }

            override fun onItemRemoveClick(position: Int) {
                adapter.remove(position)
                viewModel.removeUser(data[position].id)
                viewModel.getLiveData()
            }

        })
        adapter.setData(data)
        binding.usersRecycler.adapter = adapter
    }

    private fun initFab() {
        binding.addUserFAB.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, UserDetailFragment.newInstance(null))
                ?.addToBackStack("")
                ?.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = UsersListFragment()
    }
}