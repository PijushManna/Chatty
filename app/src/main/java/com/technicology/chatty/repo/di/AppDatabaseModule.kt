package com.technicology.chatty.repo.di

import android.content.Context
import androidx.room.Room
import com.technicology.chatty.repo.auth.AuthRepo
import com.technicology.chatty.repo.auth.AuthRepoImpl
import com.technicology.chatty.repo.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun getLocalDb(@ApplicationContext context:Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "chatty")
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()
    }

    @Singleton
    @Provides
    fun getAuthRepo(appDatabase: AppDatabase): AuthRepo = AuthRepoImpl(appDatabase.userDao)
}