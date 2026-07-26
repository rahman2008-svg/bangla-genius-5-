package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.BanglaDao
import com.example.data.model.*

@Database(
    entities = [
        LessonEntity::class,
        DictionaryEntity::class,
        QuizQuestionEntity::class,
        UserNoteEntity::class,
        UserProgressEntity::class,
        FlashcardEntity::class,
        AchievementEntity::class,
        ExampleEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun banglaDao(): BanglaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bangla_genius_db"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
