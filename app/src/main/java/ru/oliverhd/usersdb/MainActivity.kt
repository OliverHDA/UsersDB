package ru.oliverhd.usersdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.oliverhd.usersdb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AuthorizationFragment.newInstance())
                .commitNow()
        }
    }
}