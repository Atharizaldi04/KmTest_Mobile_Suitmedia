package com.athar.suitmediatestathar

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.athar.suitmediatestathar.databinding.ActivityFirstBinding

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding
    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var mainLayout: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        backgroundAnimation()
        playAnimation()
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnCheck.setOnClickListener {
                palindromeChecker()
            }

            btnNext.setOnClickListener {
                openSecondPageWithName()
            }

        }
    }

    private fun backgroundAnimation() {
        mainLayout = binding.root
        animationDrawable = mainLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(3000)
        animationDrawable.start()
    }

    private fun openSecondPageWithName() {
        val name: String = binding.etName.text.toString()

        if (name.isEmpty()) {
            binding.etName.error = "Name cannot be empty."
        } else {
            val main = Intent(this@FirstActivity, MainActivity::class.java)
            savePreference(name)
            startActivity(main)
            finishAffinity()
        }
    }

    private fun savePreference (Name: String) {
        val userPreference = UserPreference(this)

        val userModel = UserModel(
            name = Name,
        )

        userPreference.setUser(userModel)
    }

    private fun palindromeChecker() {
        val inputString: String = binding.etPalindrome.text.toString().replace(" ", "")
        val reverseString: String = inputString.reversed()

        if(inputString.isEmpty()) {
            binding.etPalindrome.error = "Data tidak boleh kososng"
        } else if (inputString.equals(reverseString, ignoreCase = true)) {
            val title: String = "Palindrome"
            val message: String = "The sentence is a palindrome."


            showCustomDialog(message, title)
        } else {
            val title: String = "Not Palindrome"
            val message: String = "The sentence is not a palindrome."

            showCustomDialog(message, title)
        }
    }

    private fun showCustomDialog(message: String, tile: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog_palindrome)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val tvTitle: TextView = dialog.findViewById(R.id.tvTitle)
        val tvMessage: TextView = dialog.findViewById(R.id.tvMessage)
        val btnClose: Button = dialog.findViewById(R.id.btnClose)

        tvTitle.text = tile
        tvMessage.text = message

        btnClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }

    private fun playAnimation() {
        val imageView = ObjectAnimator.ofFloat(binding.imageView, View.ALPHA, 1f).setDuration(500)
        val imageView2 = ObjectAnimator.ofFloat(binding.imageView2, View.ALPHA, 1f).setDuration(500)
        val name = ObjectAnimator.ofFloat(binding.textInputLayout, View.ALPHA, 1f).setDuration(1000)
        val palindrome = ObjectAnimator.ofFloat(binding.textInputLayout2, View.ALPHA, 1f).setDuration(500)
        val btnCheck = ObjectAnimator.ofFloat(binding.btnCheck, View.ALPHA, 1f).setDuration(500)
        val btnNext = ObjectAnimator.ofFloat(binding.btnNext, View.ALPHA, 1f).setDuration(500)

        AnimatorSet().apply {
            playSequentially(imageView, imageView2, name, palindrome, btnCheck,btnNext)
            start()
        }
    }
}