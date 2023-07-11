package com.athar.suitmediatestathar

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.athar.suitmediatestathar.databinding.ActivityMainBinding
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var mUserPreference: UserPreference
    private lateinit var userModel: UserModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        checkPreference()
        savePreference()
        setUserChoosen()
        onClick()
    }

    private fun onClick() {
        binding.apply {
            btnChooseUser.setOnClickListener {
                val intent = Intent(this@MainActivity, ThirdActivity::class.java)
                startActivity(intent)
            }
            btnLogout.setOnClickListener {
                val intent = Intent(this@MainActivity, FirstActivity::class.java)
                startActivity(intent)
                mUserPreference.removeUser()
                finishAffinity()
            }
        }
    }

    private fun init() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))

        mUserPreference = UserPreference(this)

        val name = mUserPreference.getUser().name
        binding.tvName.text = name
    }

    private fun checkPreference () {
        userModel = mUserPreference.getUser()

        if(userModel.name == null) {
            val intent = Intent(this@MainActivity, FirstActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }

    private fun setUserChoosen() {
        val userModel = mUserPreference.getUser()
        val nameChoosen = userModel.nameChoosen


        if (nameChoosen != null) {
            binding.tvSelectedUser.visibility = View.GONE

            binding.tvNameChoosen.visibility = View.VISIBLE
            binding.tvEmailChoosen.visibility = View.VISIBLE
            binding.ImgAvatar.visibility = View.VISIBLE

            binding.tvNameChoosen.text = userModel.nameChoosen
            binding.tvEmailChoosen.text = userModel.emailChoosen
            Glide.with(binding.ImgAvatar)
                .load(userModel.avatarChoosen)
                .fitCenter()
                .into(binding.ImgAvatar)
        } else {
            binding.tvSelectedUser.visibility = View.VISIBLE
            binding.tvNameChoosen.visibility = View.GONE
            binding.tvEmailChoosen.visibility = View.GONE
            binding.ImgAvatar.visibility = View.GONE
        }
    }

    private fun savePreference () {
        val name = userModel.name
        val nameChoosen = intent.getStringExtra("Name").toString()
        val emailChoosen = intent.getStringExtra("Email").toString()
        val avatarChoosen = intent.getStringExtra("Avatar").toString()

        if (nameChoosen != "null") {
            val userModel = UserModel(
                name = name,
                nameChoosen = nameChoosen,
                emailChoosen = emailChoosen,
                avatarChoosen = avatarChoosen,
            )
            mUserPreference.setUser(userModel)
        }
    }
}