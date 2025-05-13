package com.example.dndbuilder

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dndbuilder.databinding.FragmentSignUpBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentSignUp : Fragment(R.layout.fragment_sign_up) {
    private var binding :FragmentSignUpBinding? = null
    private lateinit var  userDao : UserDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        val usernameEditText = binding?.usernameEditText
        val passwordEditText = binding?.passwordEditText
        val confirmPasswordEditText = binding?.confirmPassword
        val registerButton = binding?.registerButtonSuccess
        val db = AppDatabase.getInstance(requireContext())
        userDao = db.userDao()

        binding?.loginButton?.setOnClickListener {
            findNavController().navigate(FragmentSignUpDirections.actionRegToLog())
        }

        registerButton?.setOnClickListener {
            val username = usernameEditText?.text.toString().trim()
            val password = passwordEditText?.text.toString().trim()
            val confirmPassword = confirmPasswordEditText?.text.toString().trim()


            var isValid = true

            if (username.isBlank() || username.length < 3) {
                usernameEditText?.error = "Ник должен быть от 3 символов"
                isValid = false
            }

            if (password.isBlank() || password.length < 6) {
                passwordEditText?.error = "Пароль должен быть от 6 символов"
                isValid = false
            }
            if (confirmPassword != password) {
                confirmPasswordEditText?.error = "Пароли не совпадают"
                isValid = false
            }

            if (isValid) {
                // Здесь вставка в базу данных, если всё валидно
                lifecycleScope.launch {
                    val existingUser = userDao.getUserByUsername(username)
                    if (existingUser != null) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                "Пользователь уже существует",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        userDao.insertUser(User(username = username, password = password))
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                requireContext(),
                                "Регистрация успешна",
                                Toast.LENGTH_SHORT
                            ).show()
                            findNavController().navigate(FragmentSignUpDirections.actionRegToSuccessReg())
                        }
                    }
                }
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
