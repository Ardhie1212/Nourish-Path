package com.example.nourishpath

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.example.nourishpath.ui.reminder.helper.NotificationHelper
import com.google.firebase.FirebaseApp

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        applyDarkMode()
        createNotificationChannels()
    }

    private fun applyDarkMode() {
        val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("DARK_MODE", false)
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun createNotificationChannels() {
        NotificationHelper.createNotificationChannels(this)
    }
}
