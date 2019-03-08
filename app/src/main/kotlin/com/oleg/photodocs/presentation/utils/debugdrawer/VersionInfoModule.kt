package com.oleg.photodocs.presentation.utils.debugdrawer

import android.content.Context
import android.content.res.Resources
import au.com.gridstone.debugdrawer.KeyValueDebugDrawerModule
import com.oleg.photodocs.BuildConfig
import com.oleg.photodocs.R

/**
 * A simple module that displays
 *  - Version login
 *  - Version code
 */
class VersionInfoModule : KeyValueDebugDrawerModule() {
    override fun getEntries(context: Context): Map<String, String> {
        val resources: Resources = context.resources

        val make = resources.getString(R.string.drawer_version_name)
        val model = resources.getString(R.string.drawer_version_code)

        val version: String = BuildConfig.VERSION_NAME
        val versionCode: String = BuildConfig.VERSION_CODE.toString()

        return mapOf(
            make to version.take(20),
            model to versionCode.take(20)
        )
    }
}
