package com.example.diagnofish;

import android.app.Application
import com.example.diagnofish.repository.ApiRepository
import com.example.diagnofish.repository.ApiRepositoryImpl
import com.example.diagnofish.repository.UserPreferencesRepository
import com.example.diagnofish.viewmodel.DetailViewModel
import com.example.diagnofish.viewmodel.HistoryViewModel
import com.example.diagnofish.viewmodel.LoginViewModel
import com.example.diagnofish.viewmodel.RegisterViewModel
import com.example.diagnofish.viewmodel.ScanViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


val appModule = module {
    single<ApiRepository> { ApiRepositoryImpl() }
    single<UserPreferencesRepository> { UserPreferencesRepository(androidContext()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { ScanViewModel(get()) }
    viewModel { HistoryViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}


class Diagnofish : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Diagnofish)
            modules(appModule)
        }
    }
}