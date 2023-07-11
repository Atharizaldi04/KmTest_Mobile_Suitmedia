package com.athar.suitmediatestathar

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (
    var name: String? = null,
    var nameChoosen: String? = null,
    var emailChoosen: String? = null,
    var avatarChoosen: String? = null,
) : Parcelable