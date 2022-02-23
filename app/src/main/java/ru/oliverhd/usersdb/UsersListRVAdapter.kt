package ru.oliverhd.usersdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.oliverhd.usersdb.databinding.ItemUsersListBinding

class UsersListRVAdapter(private val data: List<User>) :
    RecyclerView.Adapter<UsersListRVAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding =
            ItemUsersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class UserListViewHolder(private val binding: ItemUsersListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            val fullName = "${user.name} ${user.surname.orEmpty()} ${user.patronymic.orEmpty()}"
            binding.userName.text = fullName
            binding.userPhoneNumber.text = user.phone
        }
    }
}