package com.oleg.photodocs.di

import com.oleg.photodocs.App
import com.oleg.photodocs.AppConfiguration
import com.oleg.photodocs.BuildConfig
import com.oleg.photodocs.cache.DiskLruCache
import com.oleg.photodocs.cache.LruCache
import com.oleg.photodocs.data.datasource.DocumentCacheDataSource
import com.oleg.photodocs.data.datasource.DocumentRemoteDataSource
import com.oleg.photodocs.data.datasource.LoginCacheDataSource
import com.oleg.photodocs.data.datasource.LoginRemoteDataSource
import com.oleg.photodocs.data.repository.DocumentRepositoryImpl
import com.oleg.photodocs.data.repository.LoginRepositoryImpl
import com.oleg.photodocs.datasource.cache.DocumentCacheDataSourceImpl
import com.oleg.photodocs.datasource.cache.LoginCacheDataSourceImpl
import com.oleg.photodocs.datasource.model.DocumentEntity
import com.oleg.photodocs.datasource.model.LoginResponseEntity
import com.oleg.photodocs.datasource.remote.DocumentRemoteDataSourceImpl
import com.oleg.photodocs.datasource.remote.LoginRemoteDataSourceImpl
import com.oleg.photodocs.domain.repository.DocumentRepository
import com.oleg.photodocs.domain.repository.LoginRepository
import com.oleg.photodocs.domain.usecases.DocumentUseCase
import com.oleg.photodocs.domain.usecases.LoginUseCase
import com.oleg.photodocs.networking.DocumentApi
import com.oleg.photodocs.networking.LoginApi
import com.oleg.photodocs.presentation.viewmodel.DocumentViewModel
import com.oleg.photodocs.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

fun injectAppModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        networkModule,
        cacheModule
    )
}

// VIEW MODEL`S
val viewModelModule: Module = module {
    viewModel { LoginViewModel(loginUseCase = get()) }
    viewModel { DocumentViewModel(documentUseCase = get()) }
}

// USE CASES
val useCaseModule: Module = module {
    factory { LoginUseCase(loginRepository = get()) }
    factory { DocumentUseCase(documentRepository = get()) }
}

// REPOSITORY
val repositoryModule: Module = module {
    single { LoginRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as LoginRepository }
    single { DocumentRepositoryImpl(cacheDataSource = get(), remoteDataSource = get()) as DocumentRepository }
}

// DATASOURCE (cache and remote)
val dataSourceModule: Module = module {
    // Login
    single { LoginRemoteDataSourceImpl(api = loginApi) as LoginRemoteDataSource }
    single { LoginCacheDataSourceImpl(cache = get(LOGIN_CACHE)) as LoginCacheDataSource }

    // Document
    single { DocumentRemoteDataSourceImpl(api = documentApi) as DocumentRemoteDataSource }
    single { DocumentCacheDataSourceImpl(cache = get(DOCUMENT_CACHE)) as DocumentCacheDataSource }
}

private val loginApi: LoginApi = AppConfiguration.createLoginApi()
private val documentApi: DocumentApi = AppConfiguration.createDocumentApi()

// NETWORK API
val networkModule: Module = module {
    single { loginApi }
    single { documentApi }
}

private const val LOGIN_CACHE = "LOGIN_CACHE"
private const val DOCUMENT_CACHE = "DOCUMENT_CACHE"

// CACHE
val cacheModule: Module = module {
    single(name = LOGIN_CACHE) { DiskLruCache<LoginResponseEntity>(App.instance.dirForCache) }
    single(name = DOCUMENT_CACHE) { LruCache<List<DocumentEntity>>() }
}



