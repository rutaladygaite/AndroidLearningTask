package com.example.to_dolist.di.modules

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.to_dolist.ToDoApplication
import com.example.to_dolist.database.AppDatabase
import com.example.to_dolist.database.ToDoDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    fun provideDatabase(
        application: ToDoApplication,
    ): RoomDatabase.Builder<AppDatabase> = Room.databaseBuilder(application, AppDatabase::class.java, DATABASE_NAME)

    @Suppress("MagicNumber")
    companion object {
        const val DATABASE_NAME = "database"
    }

    @Provides
    fun provideServerDao(db: AppDatabase): ToDoDao = db.toDoDao()
}
