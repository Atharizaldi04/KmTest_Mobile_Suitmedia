package com.athar.suitmediatestathar

import android.content.Context

class UserPreference(context: Context) {

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAMECHOOSEN = "nameChoosen"
        private const val NAME = "name"
        private const val EMAILCHOOSEN = "emailChoosen"
        private const val AVATARCHOOSEN = "avatarChoosen"
    }

    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(value: UserModel) {
        val editor = preference.edit()
        editor.putString(NAME, value.name)
        editor.putString(NAMECHOOSEN, value.nameChoosen)
        editor.putString(EMAILCHOOSEN, value.emailChoosen)
        editor.putString(AVATARCHOOSEN, value.avatarChoosen)
        editor.apply()
    }

    fun getUser(): UserModel {
        val model = UserModel()
        model.name = preference.getString(NAME, null)
        model.nameChoosen = preference.getString(NAMECHOOSEN, null)
        model.emailChoosen = preference.getString(EMAILCHOOSEN, null)
        model.avatarChoosen = preference.getString(AVATARCHOOSEN, null)

        return model
    }

    fun removeUser() {
        val editor = preference.edit().clear()
        editor.apply()
    }

}