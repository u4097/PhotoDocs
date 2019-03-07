package com.oleg.photodocs.pref
import ru.prsolution.winstrike.presentation.utils.pref.SharedPrefFactory

object PrefUtils {
    private const val TOKEN = "token"

    var token: String?
        get() = SharedPrefFactory.prefs.getString(TOKEN, "")
        set(token) {
            SharedPrefFactory.editor.putString(TOKEN, token).commit()
        }
}
