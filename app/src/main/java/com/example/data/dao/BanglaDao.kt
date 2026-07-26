package com.example.data.dao

import androidx.room.*
import com.example.data.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BanglaDao {

    // --- Lessons ---
    @Query("SELECT COUNT(*) FROM lessons")
    suspend fun getLessonCount(): Int

    @Query("SELECT * FROM lessons ORDER BY id ASC")
    fun getAllLessons(): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons WHERE category = :category ORDER BY id ASC")
    fun getLessonsByCategory(category: String): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons WHERE id = :id LIMIT 1")
    suspend fun getLessonById(id: Int): LessonEntity?

    @Query("SELECT * FROM lessons WHERE isBookmarked = 1 ORDER BY id ASC")
    fun getBookmarkedLessons(): Flow<List<LessonEntity>>

    @Query("SELECT * FROM lessons WHERE title LIKE '%' || :query || '%' OR definition LIKE '%' || :query || '%' OR examples LIKE '%' || :query || '%'")
    fun searchLessons(query: String): Flow<List<LessonEntity>>

    @Query("UPDATE lessons SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun updateLessonBookmark(id: Int, isBookmarked: Boolean)

    @Query("UPDATE lessons SET isCompleted = :isCompleted WHERE id = :id")
    suspend fun updateLessonCompleted(id: Int, isCompleted: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLessons(lessons: List<LessonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLesson(lesson: LessonEntity)

    // --- Dictionary ---
    @Query("SELECT * FROM dictionary ORDER BY word ASC")
    fun getAllWords(): Flow<List<DictionaryEntity>>

    @Query("SELECT * FROM dictionary WHERE word LIKE '%' || :query || '%' OR meaningBangla LIKE '%' || :query || '%' OR meaningEnglish LIKE '%' || :query || '%'")
    fun searchDictionary(query: String): Flow<List<DictionaryEntity>>

    @Query("SELECT * FROM dictionary WHERE isBookmarked = 1 ORDER BY word ASC")
    fun getBookmarkedWords(): Flow<List<DictionaryEntity>>

    @Query("UPDATE dictionary SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun updateWordBookmark(id: Int, isBookmarked: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDictionaryWords(words: List<DictionaryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDictionaryWord(word: DictionaryEntity)

    // --- Quiz Questions ---
    @Query("SELECT * FROM quiz_questions")
    fun getAllQuestions(): Flow<List<QuizQuestionEntity>>

    @Query("SELECT * FROM quiz_questions WHERE category = :category")
    suspend fun getQuestionsByCategory(category: String): List<QuizQuestionEntity>

    @Query("SELECT * FROM quiz_questions ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestions(limit: Int): List<QuizQuestionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizQuestions(questions: List<QuizQuestionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuizQuestion(question: QuizQuestionEntity)

    // --- User Notes ---
    @Query("SELECT * FROM user_notes ORDER BY updatedAt DESC")
    fun getAllNotes(): Flow<List<UserNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: UserNoteEntity)

    @Query("DELETE FROM user_notes WHERE id = :id")
    suspend fun deleteNote(id: Int)

    // --- User Progress ---
    @Query("SELECT * FROM user_progress WHERE id = 1 LIMIT 1")
    fun getUserProgress(): Flow<UserProgressEntity?>

    @Query("SELECT * FROM user_progress WHERE id = 1 LIMIT 1")
    suspend fun getUserProgressOnce(): UserProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateProgress(progress: UserProgressEntity)

    // --- Flashcards ---
    @Query("SELECT * FROM flashcards ORDER BY id ASC")
    fun getAllFlashcards(): Flow<List<FlashcardEntity>>

    @Query("SELECT * FROM flashcards WHERE category = :category")
    fun getFlashcardsByCategory(category: String): Flow<List<FlashcardEntity>>

    @Query("UPDATE flashcards SET isLearned = :isLearned WHERE id = :id")
    suspend fun updateFlashcardLearned(id: Int, isLearned: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlashcards(flashcards: List<FlashcardEntity>)

    // --- Achievements ---
    @Query("SELECT * FROM achievements")
    fun getAllAchievements(): Flow<List<AchievementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievements(achievements: List<AchievementEntity>)

    @Update
    suspend fun updateAchievement(achievement: AchievementEntity)

    // --- Examples ---
    @Query("SELECT * FROM examples ORDER BY id ASC")
    fun getAllExamples(): Flow<List<ExampleEntity>>

    @Query("SELECT * FROM examples WHERE lessonId = :lessonId ORDER BY orderIndex ASC")
    fun getExamplesForLesson(lessonId: Int): Flow<List<ExampleEntity>>

    @Query("SELECT * FROM examples WHERE category = :category ORDER BY id ASC")
    fun getExamplesByCategory(category: String): Flow<List<ExampleEntity>>

    @Query("SELECT * FROM examples WHERE isBookmarked = 1 ORDER BY id ASC")
    fun getBookmarkedExamples(): Flow<List<ExampleEntity>>

    @Query("SELECT * FROM examples WHERE example LIKE '%' || :query || '%' OR analysis LIKE '%' || :query || '%' OR explanation LIKE '%' || :query || '%' OR topic LIKE '%' || :query || '%'")
    fun searchExamples(query: String): Flow<List<ExampleEntity>>

    @Query("SELECT * FROM examples ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomExamples(limit: Int): List<ExampleEntity>

    @Query("SELECT * FROM examples ORDER BY id ASC LIMIT 1")
    suspend fun getDailyExample(): ExampleEntity?

    @Query("SELECT COUNT(*) FROM examples")
    suspend fun getExampleCount(): Int

    @Query("SELECT * FROM examples ORDER BY viewCount DESC, id ASC LIMIT :limit")
    fun getMostViewedExamples(limit: Int = 10): Flow<List<ExampleEntity>>

    @Query("UPDATE examples SET viewCount = viewCount + 1 WHERE id = :id")
    suspend fun incrementExampleViewCount(id: Int)

    @Query("UPDATE examples SET userNotes = :notes WHERE id = :id")
    suspend fun updateExampleNotes(id: Int, notes: String)

    @Query("SELECT * FROM examples WHERE (category = :category OR topic = :topic) AND id != :currentId LIMIT :limit")
    suspend fun getRelatedExamples(category: String, topic: String, currentId: Int, limit: Int = 5): List<ExampleEntity>

    @Query("UPDATE examples SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun updateExampleBookmark(id: Int, isBookmarked: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExamples(examples: List<ExampleEntity>)
}
