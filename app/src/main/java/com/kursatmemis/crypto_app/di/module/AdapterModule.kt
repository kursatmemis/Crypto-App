package com.kursatmemis.crypto_app.di.module

import com.kursatmemis.crypto_app.model.Crypto
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class AdapterModule {

    @ActivityScoped
    @Provides
    fun provideCryptoList() : ArrayList<Crypto> {
        return ArrayList()
    }

}