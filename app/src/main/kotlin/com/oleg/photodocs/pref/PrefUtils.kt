package com.oleg.photodocs.pref

object PrefUtils {
    private const val TOKEN = "token"

    var token: String?
        get() = SharedPrefFactory.prefs.getString(TOKEN, "")
        set(token) {
            SharedPrefFactory.editor.putString(TOKEN, token).commit()
        }
}
