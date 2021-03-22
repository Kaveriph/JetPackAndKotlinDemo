package com.kaveri.jetpackcomponentdemo.di

import com.kaveri.jetpackcomponentdemo.repositories.DemoRepository
import com.kaveri.jetpackcomponentdemo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.reflect.KClass

val demoAppModule = module{
    viewModel { MainViewModel<KClass<Any>>(demoRepository = get()) }
    single {
        DemoRepository()
    }
}