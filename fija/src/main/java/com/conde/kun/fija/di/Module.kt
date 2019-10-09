package com.conde.kun.fija.di

import androidx.room.Room
import com.conde.kun.fija.data.datasource.AccessDataSource
import com.conde.kun.fija.data.datasource.FactsDataSource
import com.conde.kun.fija.data.datasource.SettingsDataSource
import com.conde.kun.fija.data.datasource.api.AccessApi
import com.conde.kun.fija.data.datasource.api.ServiceApiConfigurator
import com.conde.kun.fija.data.datasource.db.AppDatabase
import com.conde.kun.fija.data.datasource.remote.LocalSettingsDataSource
import com.conde.kun.fija.data.datasource.remote.RemoteAccessDataSource
import com.conde.kun.fija.data.datasource.remote.RemoteFactsDataSource
import com.conde.kun.fija.data.mapper.FactMapper
import com.conde.kun.fija.data.mapper.UserMapper
import com.conde.kun.fija.data.repository.AccessRepositoryImpl
import com.conde.kun.fija.data.repository.FactsRepositoryImpl
import com.conde.kun.fija.domain.repository.AccessRepository
import com.conde.kun.fija.domain.repository.FactsRepository
import com.conde.kun.fija.domain.usecase.*
import com.conde.kun.fija.view.dashboard.DashboardViewModel
import com.conde.kun.fija.view.facts.FactsViewModel
import com.conde.kun.fija.view.login.LoginViewModel
import com.conde.kun.fija.view.register.RegisterViewModel
import com.conde.kun.fija.view.splash.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { DashboardViewModel(get()) }
    viewModel { FactsViewModel(get(), get(), get(), get()) }
}

val domainModule = module {
    factory { LoginUseCase(get(), get()) }
    factory { IsUserSignedInUseCase(get(), get()) }
    factory { LogoutUseCase(get(), get()) }
    factory { RegisterUseCase(get(), get()) }
    factory { GetUserInfoUseCase(get(), get()) }
    factory { GetFactsUseCase(get(), get()) }
    factory { SaveFactUseCase(get(), get()) }
    factory { GetUserFactsUseCase(get(), get()) }
    factory { DeleteFactUseCase(get(), get()) }
}

val dataModule = module {
    single<AccessRepository> { AccessRepositoryImpl(get(), get(), get()) }
    single<AccessDataSource> { RemoteAccessDataSource(get()) }
    single { UserMapper() }
    single<SettingsDataSource> { LocalSettingsDataSource(androidContext()) }
    single<FactsRepository> { FactsRepositoryImpl(get(), get(), get(), get(), get()) }
    single { FactMapper() }
    single<FactsDataSource> { RemoteFactsDataSource(get()) }


    single { ServiceApiConfigurator(androidContext(), get()) }
    single {
        val serviceConfigurator: ServiceApiConfigurator = get()
        serviceConfigurator.factApi
    }
    single {
        val serviceConfigurator: ServiceApiConfigurator = get()
        serviceConfigurator.accessApi
    }
    single {
        val appDatabase: AppDatabase = get()
        appDatabase.factDao()
    }
    single {
        val appDatabase: AppDatabase = get()
        appDatabase.userDao()
    }
    single {
        val appDatabase: AppDatabase = get()
        appDatabase.userFactsDao()
    }
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "fija-database").build()
    }

    single { CoroutineScope(Dispatchers.IO) }

}