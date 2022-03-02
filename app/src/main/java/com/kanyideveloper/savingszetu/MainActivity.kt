package com.kanyideveloper.savingszetu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.kanyideveloper.savingszetu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val firebaseMessaging = FirebaseMessaging.getInstance()

        //firebaseMessaging.subscribeToTopic("new_payment")

        binding.buttonPay.setOnClickListener {
            val name = binding.edtName.text.toString()
            val amount = binding.edtAmount.text.toString()

            if (name.isEmpty() || amount.isEmpty()) {
                return@setOnClickListener
            }

            val userDataMap = HashMap<String, String>()
            userDataMap["name"] = name
            userDataMap["amount"] = amount

            FirebaseDatabase.getInstance().getReference("payment").push().setValue(userDataMap)

            Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
        }
    }
}