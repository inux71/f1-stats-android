package com.grabieckacper.f1stats.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.grabieckacper.f1stats.repository.impl.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DataStoreRepository {
        return DataStoreRepositoryImpl(dataStore = dataStore)
    }
}
