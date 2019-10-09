package com.conde.kun.randomusers.di.module

import com.conde.kun.randomusers.data.datasource.UserDataSource
import com.conde.kun.randomusers.data.datasource.remote.RemoteUserDataSource
import com.conde.kun.randomusers.domain.repository.UserRepository
import com.conde.kun.randomusers.domain.usecase.GetUserUseCase
import com.conde.kun.randomusers.domain.usecase.StoreUserSelectionCountUseCase
import com.conde.kun.randomusers.view.user.userdetail.UserDetailViewModel
import com.conde.kun.randomusers.view.user.userlist.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val usersModule = module {

    viewModel { UserListViewModel(get(), get()) }

    viewModel { UserDetailViewModel() }
}

val domainModule = module {

    factory { GetUserUseCase(get(), get()) }

    factory { StoreUserSelectionCountUseCase(get(), get()) }

}

val dataModule = module {
    single<UserDataSource> { RemoteUserDataSource() }

    single<UserRepository> { com.conde.kun.randomusers.data.repository.UserRepository(get()) }
}