package com.example.nourishpath.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.nourishpath.databinding.FragmentHomeBinding
import com.example.nourishpath.ui.profile.ProfileActivity
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menampilkan tanggal hari ini
        // val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        // val todayDate = dateFormat.format(Date())
        // binding.tvTodayDate.text = todayDate

        sharedPreferences = requireContext().getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

        val isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false)
        binding.themeSwitch.isChecked = isDarkMode
        setDarkMode(isDarkMode)

        binding.ivProfilePicture.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

//        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "Guest") // "Guest" sebagai default

        binding.tvUserName.text = userName

        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked != isDarkMode) {
                setDarkMode(isChecked)
                with(sharedPreferences.edit()) {
                    putBoolean("DARK_MODE", isChecked)
                    apply()
                }
            }
        }
    }

    private fun setDarkMode(isDarkMode: Boolean) {
        Log.e("Set Dark Mode", "Dark mode")
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
