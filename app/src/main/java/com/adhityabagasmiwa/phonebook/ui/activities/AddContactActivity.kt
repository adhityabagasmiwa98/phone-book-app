package com.adhityabagasmiwa.phonebook.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adhityabagasmiwa.phonebook.R
import com.adhityabagasmiwa.phonebook.databinding.ActivityAddContactBinding

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddContact.setOnClickListener {
            val mIntent = Intent(this, DetailActivity::class.java)
            startActivity(mIntent)
        }
    }
}