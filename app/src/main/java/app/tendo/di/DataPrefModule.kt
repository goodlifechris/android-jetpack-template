package app.tendo.di

import android.content.Context
import app.tendo.data.local.dataStore.DataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by goodlife on 26,August,2021
 */

@Module
@InstallIn(SingletonComponent::class)
object DataPrefModule {

    @Singleton
    @Provides
    fun provideDataManager(@ApplicationContext context: Context) = DataManager(context)

}