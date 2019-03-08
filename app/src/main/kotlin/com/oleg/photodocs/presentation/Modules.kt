package com.oleg.photodocs.presentation

import com.oleg.photodocs.App
import com.oleg.photodocs.BuildConfig
import com.oleg.photodocs.cache.DiskLruCache
import com.oleg.photodocs.data.datasource.LoginCacheDataSource
import com.oleg.photodocs.data.datasource.LoginRemoteDataSource
import com.oleg.photodocs.data.repository.LoginRepositoryImpl
import com.oleg.photodocs.datasource.cache.LoginCacheDataSourceImpl
import com.oleg.photodocs.datasource.remote.LoginRemoteDataSourceImpl
import com.oleg.photodocs.domain.repository.LoginRepository
import com.oleg.photodocs.domain.usecases.LoginUseCase
import com.oleg.photodocs.networking.LoginApi
import com.oleg.photodocs.networking.createNetworkClient
import com.oleg.photodocs.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    loadKoinModules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        networkModule,
        cacheModule
    )
}

val viewModelModule: Module = module {
    viewModel { LoginViewModel(loginUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { LoginUseCase(loginRepository = get()) }
}

val repositoryModule: Module = module {
    single { LoginRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as LoginRepository }
}

val dataSourceModule: Module = module {
    single { LoginCacheDataSourceImpl(cache = get(LOGIN_CACHE)) as LoginCacheDataSource }
    single { LoginRemoteDataSourceImpl(api = loginApi) as LoginRemoteDataSource }
}

val networkModule: Module = module {
    single { loginApi }
}

val cacheModule: Module = module {
    single(name = LOGIN_CACHE) { DiskLruCache<LoginResponse>(App.instance.dirForCache) }
}

private const val DEV_URL = BuildConfig.DEV_URL
private const val PROD_URL = BuildConfig.PROD_URL


private val retrofit: Retrofit = createNetworkClient(DEV_URL, BuildConfig.DEBUG)

private val loginApi: LoginApi = retrofit.create(LoginApi::class.java)

private const val LOGIN_CACHE = "LOGIN_CACHE"
