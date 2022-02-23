package ru.oliverhd.usersdb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ru.oliverhd.usersdb.databinding.FragmentAuthorizationBinding
import ru.oliverhd.usersdb.userslist.UsersListFragment

class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            loginButton.setOnClickListener {
                if (inputEditTextLogin.text.toString() == "admin" && inputEditTextPassword.text.toString() == "admin") {
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragmentContainer, UsersListFragment.newInstance())
                        ?.addToBackStack("")
                        ?.commit()
                } else {
                    Toast.makeText(context, "Неправильные логин или пароль", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = AuthorizationFragment()
    }
}