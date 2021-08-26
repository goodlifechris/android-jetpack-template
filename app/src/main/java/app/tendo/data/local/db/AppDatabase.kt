package app.tendo.data.local.db

/**
 * Created by goodlife on 26,August,2021
 */

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.tendo.data.GenreConverters
import app.tendo.data.local.db.entities.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(GenreConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tendoDao(): TendoDao
}