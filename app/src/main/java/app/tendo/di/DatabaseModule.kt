package app.tendo.di

/**
 * Created by goodlife on 26,August,2021
 */

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import app.tendo.data.local.db.AppDatabase
import app.tendo.data.local.db.TendoDao
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app.db"
        ).build()
    }

    @Provides
    fun provideTendoDao(appDatabase: AppDatabase): TendoDao {
        return appDatabase.tendoDao()
    }
}