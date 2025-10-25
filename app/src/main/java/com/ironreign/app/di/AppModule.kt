package com.ironreign.app.di

import android.content.Context
import com.ironreign.app.data.IronDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context, scope: CoroutineScope): IronDatabase {
        return IronDatabase.getDatabase(context, scope)
    }
}
