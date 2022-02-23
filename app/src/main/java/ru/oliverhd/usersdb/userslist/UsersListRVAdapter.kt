package ru.oliverhd.usersdb.userslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.oliverhd.usersdb.data.User
import ru.oliverhd.usersdb.databinding.ItemUsersListBinding

class UsersListRVAdapter(
    private var onItemClickListener: OnRecyclerItemClickListener?
) :
    RecyclerView.Adapter<UsersListRVAdapter.UserListViewHolder>() {

    private val data: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding =
            ItemUsersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<User>) {
        data.forEach {
            this.data.add(it)
        }
    }

    fun remove(position: Int) {
        data.removeAt(position)
        notifyDataSetChanged()
    }

    inner class UserListViewHolder(private val binding: ItemUsersListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, position: Int) {
            binding.root.setOnClickListener { onItemClickListener?.onItemClick(user.id) }
            val fullName = "${user.name} ${user.surname.orEmpty()} ${user.patronymic.orEmpty()}"
            binding.userName.text = fullName
            binding.userPhoneNumber.text = user.phone
            binding.removeItem.setOnClickListener { onItemClickListener?.onItemRemoveClick(position) }
        }
    }

    interface OnRecyclerItemClickListener {
        fun onItemClick(userId: Int)
        fun onItemRemoveClick(position: Int)
    }
}