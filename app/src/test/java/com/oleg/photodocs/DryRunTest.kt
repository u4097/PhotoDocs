package com.oleg.photodocs

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.oleg.photodocs.AppConfiguration.createLoginApi
import com.oleg.photodocs.AppConfiguration.remoteDataSource
import com.oleg.photodocs.datasource.model.LoginEntity
import com.oleg.photodocs.networking.LoginApi
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules
import org.koin.test.get
import retrofit2.mock.MockRetrofit

/*val phoneApp = koinApplication {
    loadKoinModules(remoteDataSource)
}*/

class DryRunTest : AutoCloseKoinTest() {

    val testApp = module {
        single {
            MockRetrofit.Builder(get()).networkBehavior(get()).build()
        }
        single {
            MockLoginApi(get())
        }
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()


    @Test
    fun testRemoteConfiguration() {
        startKoin {
            logger()
            module{listOf(testApp)}
        }.checkModules()
    }

    @Test
    fun testLocalConfiguration() {
    }
}