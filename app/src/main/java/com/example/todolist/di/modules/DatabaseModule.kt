package com.example.todolist.di.modules

import android.app.Application
import androidx.room.Room
import com.example.todolist.database.AppDatabase
import com.example.todolist.database.ToDoDao
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        application: Application,
        coroutineScope: CoroutineScope,
    ): AppDatabase = Room.databaseBuilder(application, AppDatabase::class.java, "todo-list.db")
        .allowMainThreadQueries()
        .setQueryExecutor { coroutineScope.launch { it.run() } }
        .build()

    @Provides
    fun provideServerDao(db: AppDatabase): ToDoDao = db.toDoDao()
}
