package ru.oliverhd.usersdb.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ru.oliverhd.usersdb.AppState
import ru.oliverhd.usersdb.Constants
import ru.oliverhd.usersdb.R
import ru.oliverhd.usersdb.data.User
import ru.oliverhd.usersdb.data.getEmptyUser
import ru.oliverhd.usersdb.databinding.FragmentUserDetailBinding
import ru.oliverhd.usersdb.userslist.UsersListFragment

class UserDetailFragment : Fragment() {

    private var isNewUser = false
    private var userId: Int = 0
    private val viewModel: UserDetailViewModel by viewModels()
    private var _binding: FragmentUserDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isNewUser = arguments?.getBoolean(IS_NEW_USER_EXTRA) ?: false
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        if (isNewUser) {
            saveButtonClick(NEW_USER_ID)
        } else {
            userId = arguments?.getInt(USER_ID_EXTRA) ?: NEW_USER_ID
            viewModel.getUserById(userId)
        }
    }

    private fun renderData(state: AppState) {
        if (isNewUser) {
            initUser(getEmptyUser())
        } else {
            when (state) {
                is AppState.Success<*> -> {
                    initUser(state.data as User)
                    saveButtonClick(state.data.id)
                }
                is AppState.Error -> {
                    Toast.makeText(context, state.error.message, Toast.LENGTH_SHORT).show()
                    initUser(getEmptyUser())
                }
                else -> {
                    Toast.makeText(context, Constants.SOMETHING_WRONG, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initUser(user: User) {
        with(binding) {
            inputEditTextName.setText(user.name)
            inputEditTextSurname.setText(user.surname)
            inputEditTextPatronymic.setText(user.patronymic)
            inputEditTextBirthdate.setText(user.birthdate)
            inputEditTextEmail.setText(user.email)
            inputEditTextPhone.setText(user.phone)
        }
    }

    /*
    Реакция на нажатие на кнопку сохранения.
    В качестве параметра принимает ID пользователя.
    Если ID пользователя == 0. То в базе данных создаётся новый пользователь,
    иначе вносятся изменения в текущего пользователя.
    После сохранения открывается фрагмент со списком пользователей.
     */
    private fun saveButtonClick(userID: Int) {
        with(binding) {
            saveUserButton.setOnClickListener {
                if (inputEditTextName.text.isNullOrEmpty() || inputEditTextPhone.text.isNullOrEmpty()) {
                    Toast.makeText(
                        context,
                        "Имя и номер телефона не могут быть пустыми",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val user = User(
                        userID,
                        inputEditTextName.text.toString(),
                        inputEditTextSurname.text.toString(),
                        inputEditTextPatronymic.text.toString(),
                        inputEditTextBirthdate.text.toString(),
                        inputEditTextEmail.text.toString(),
                        inputEditTextPhone.text.toString(),
                        null
                    )
                    if (userID == NEW_USER_ID) {
                        viewModel.saveNewUser(user)
                    } else {
                        viewModel.changeUser(user)
                    }

                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragmentContainer, UsersListFragment.newInstance())
                        ?.addToBackStack("")
                        ?.commit()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        private const val IS_NEW_USER_EXTRA = "is_new_user_extra"
        private const val USER_ID_EXTRA = "user_id_extra"
        private const val NEW_USER_ID = 0

        fun newInstance(userId: Int?): UserDetailFragment {
            val args = Bundle()
            if (userId == null) {
                args.putBoolean(IS_NEW_USER_EXTRA, true)
            } else {
                args.putBoolean(IS_NEW_USER_EXTRA, false)
                args.putInt(USER_ID_EXTRA, userId)
            }
            val fragment = UserDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }
}