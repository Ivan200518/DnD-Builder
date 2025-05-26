package com.example.dndbuilder.auth.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dndbuilder.utils.AppDatabase
import com.example.dndbuilder.R
import com.example.dndbuilder.utils.UserDao
import com.example.dndbuilder.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentLogin () : Fragment(R.layout.fragment_login) {
    private var binding : FragmentLoginBinding? = null
    private lateinit var userDao: UserDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = AppDatabase.getInstance(requireContext())
        userDao = db.userDao()
        binding = FragmentLoginBinding.bind(view)

        binding?.loginToSuccess?.setOnClickListener {
            val username = binding?.loginUsernameEditText?.text.toString().trim()
            val password = binding?.loginPasswordEditText?.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val user = userDao.getUserByCredentials(username, password)

                withContext(Dispatchers.Main) {
                    if (user == null) {
                        Toast.makeText(requireContext(), "Неверный ник или пароль", Toast.LENGTH_SHORT).show()
                    } else {
                        findNavController().navigate(FragmentLoginDirections.actionLogToMain())
                        Toast.makeText(requireContext(), "Добро пожаловать, ${user.username}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding?.registerButton?.setOnClickListener {
            findNavController().navigate(FragmentLoginDirections.actionLogToReg())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}