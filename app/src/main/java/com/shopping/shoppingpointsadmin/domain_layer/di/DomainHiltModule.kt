package com.shopping.shoppingpointsadmin.domain_layer.di

import AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.shopping.shoppingpointsadmin.domain_layer.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainHiltModule {

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(auth = firebaseAuth)
    }
}