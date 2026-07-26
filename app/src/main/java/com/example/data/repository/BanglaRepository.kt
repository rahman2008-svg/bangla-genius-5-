package com.example.data.repository

import com.example.data.dao.BanglaDao
import com.example.data.initial.InitialData
import com.example.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

class BanglaRepository(private val dao: BanglaDao) {

    suspend fun initializeDatabaseIfEmpty() = withContext(Dispatchers.IO) {
        val currentProgress = dao.getUserProgressOnce()
        if (currentProgress == null) {
            dao.insertOrUpdateProgress(UserProgressEntity())
            dao.insertLessons(InitialData.getInitialLessons())
            dao.insertDictionaryWords(InitialData.getInitialDictionary())
            dao.insertQuizQuestions(InitialData.getInitialQuizQuestions())
            dao.insertFlashcards(InitialData.getInitialFlashcards())
            dao.insertAchievements(InitialData.getInitialAchievements())
            dao.insertExamples(InitialData.getInitialExamples())
        } else {
            val count = dao.getLessonCount()
            if (count < 1000) {
                dao.insertLessons(InitialData.getInitialLessons())
            }
        }
    }

    // --- Lessons ---
    val allLessons: Flow<List<LessonEntity>> = dao.getAllLessons()
    val bookmarkedLessons: Flow<List<LessonEntity>> = dao.getBookmarkedLessons()

    fun getLessonsByCategory(category: String): Flow<List<LessonEntity>> = dao.getLessonsByCategory(category)
    fun searchLessons(query: String): Flow<List<LessonEntity>> = dao.searchLessons(query)

    suspend fun getLessonById(id: Int): LessonEntity? = dao.getLessonById(id)

    suspend fun toggleLessonBookmark(id: Int, currentStatus: Boolean) = withContext(Dispatchers.IO) {
        dao.updateLessonBookmark(id, !currentStatus)
    }

    suspend fun markLessonCompleted(id: Int) = withContext(Dispatchers.IO) {
        dao.updateLessonCompleted(id, true)
        addXpAndProgress(xpGained = 20, completedLesson = true)
    }

    suspend fun addCustomLesson(lesson: LessonEntity) = withContext(Dispatchers.IO) {
        dao.insertLesson(lesson)
    }

    // --- Dictionary ---
    val allWords: Flow<List<DictionaryEntity>> = dao.getAllWords()
    val bookmarkedWords: Flow<List<DictionaryEntity>> = dao.getBookmarkedWords()

    fun searchDictionary(query: String): Flow<List<DictionaryEntity>> = dao.searchDictionary(query)

    suspend fun toggleWordBookmark(id: Int, currentStatus: Boolean) = withContext(Dispatchers.IO) {
        dao.updateWordBookmark(id, !currentStatus)
    }

    suspend fun addCustomWord(word: DictionaryEntity) = withContext(Dispatchers.IO) {
        dao.insertDictionaryWord(word)
    }

    // --- Quiz Questions ---
    val allQuestions: Flow<List<QuizQuestionEntity>> = dao.getAllQuestions()

    suspend fun getQuestionsByCategory(category: String): List<QuizQuestionEntity> = withContext(Dispatchers.IO) {
        dao.getQuestionsByCategory(category)
    }

    suspend fun getRandomQuestions(limit: Int = 10): List<QuizQuestionEntity> = withContext(Dispatchers.IO) {
        dao.getRandomQuestions(limit)
    }

    suspend fun addCustomQuestion(question: QuizQuestionEntity) = withContext(Dispatchers.IO) {
        dao.insertQuizQuestion(question)
    }

    // --- User Notes ---
    val allNotes: Flow<List<UserNoteEntity>> = dao.getAllNotes()

    suspend fun saveNote(note: UserNoteEntity) = withContext(Dispatchers.IO) {
        dao.insertNote(note)
    }

    suspend fun deleteNote(id: Int) = withContext(Dispatchers.IO) {
        dao.deleteNote(id)
    }

    // --- User Progress & Settings ---
    val userProgress: Flow<UserProgressEntity?> = dao.getUserProgress()

    suspend fun addXpAndProgress(xpGained: Int, completedLesson: Boolean = false, quizAttempted: Boolean = false, isCorrect: Boolean = false) = withContext(Dispatchers.IO) {
        val progress = dao.getUserProgressOnce() ?: UserProgressEntity()
        val newXp = progress.xp + xpGained
        val newLevel = (newXp / 100) + 1
        val newLessonsCount = if (completedLesson) progress.lessonsReadCount + 1 else progress.lessonsReadCount
        val newQuizzesCount = if (quizAttempted) progress.quizzesTakenCount + 1 else progress.quizzesTakenCount
        val newAttempted = if (quizAttempted) progress.totalQuestionsAttempted + 1 else progress.totalQuestionsAttempted
        val newCorrect = if (isCorrect) progress.correctAnswersCount + 1 else progress.correctAnswersCount

        dao.insertOrUpdateProgress(
            progress.copy(
                xp = newXp,
                level = newLevel,
                lessonsReadCount = newLessonsCount,
                quizzesTakenCount = newQuizzesCount,
                totalQuestionsAttempted = newAttempted,
                correctAnswersCount = newCorrect
            )
        )
    }

    suspend fun updateSettings(
        font: String,
        fontScale: Float,
        isDarkMode: Boolean,
        isVoiceReadingEnabled: Boolean
    ) = withContext(Dispatchers.IO) {
        val progress = dao.getUserProgressOnce() ?: UserProgressEntity()
        dao.insertOrUpdateProgress(
            progress.copy(
                selectedFont = font,
                selectedFontScale = fontScale,
                isDarkMode = isDarkMode,
                isVoiceReadingEnabled = isVoiceReadingEnabled
            )
        )
    }

    suspend fun completeSetup(font: String, isDarkMode: Boolean) = withContext(Dispatchers.IO) {
        val progress = dao.getUserProgressOnce() ?: UserProgressEntity()
        dao.insertOrUpdateProgress(
            progress.copy(
                selectedFont = font,
                isDarkMode = isDarkMode,
                hasCompletedWelcome = true
            )
        )
    }

    // --- Flashcards ---
    val allFlashcards: Flow<List<FlashcardEntity>> = dao.getAllFlashcards()

    suspend fun toggleFlashcardLearned(id: Int, currentStatus: Boolean) = withContext(Dispatchers.IO) {
        dao.updateFlashcardLearned(id, !currentStatus)
    }

    // --- Achievements ---
    val allAchievements: Flow<List<AchievementEntity>> = dao.getAllAchievements()

    // --- Examples ---
    val allExamples: Flow<List<ExampleEntity>> = dao.getAllExamples()
    val bookmarkedExamples: Flow<List<ExampleEntity>> = dao.getBookmarkedExamples()
    fun getMostViewedExamples(limit: Int = 10): Flow<List<ExampleEntity>> = dao.getMostViewedExamples(limit)

    fun getExamplesForLesson(lessonId: Int): Flow<List<ExampleEntity>> = dao.getExamplesForLesson(lessonId)
    fun getExamplesByCategory(category: String): Flow<List<ExampleEntity>> = dao.getExamplesByCategory(category)
    fun searchExamples(query: String): Flow<List<ExampleEntity>> = dao.searchExamples(query)

    suspend fun incrementExampleView(id: Int) = withContext(Dispatchers.IO) {
        dao.incrementExampleViewCount(id)
    }

    suspend fun updateExampleNotes(id: Int, notes: String) = withContext(Dispatchers.IO) {
        dao.updateExampleNotes(id, notes)
    }

    suspend fun getRelatedExamples(category: String, topic: String, currentId: Int): List<ExampleEntity> = withContext(Dispatchers.IO) {
        dao.getRelatedExamples(category, topic, currentId)
    }

    suspend fun getExampleCount(): Int = withContext(Dispatchers.IO) {
        dao.getExampleCount()
    }

    suspend fun getRandomExamples(limit: Int = 10): List<ExampleEntity> = withContext(Dispatchers.IO) {
        dao.getRandomExamples(limit)
    }

    suspend fun getDailyExample(): ExampleEntity? = withContext(Dispatchers.IO) {
        dao.getDailyExample()
    }

    suspend fun toggleExampleBookmark(id: Int, currentStatus: Boolean) = withContext(Dispatchers.IO) {
        dao.updateExampleBookmark(id, !currentStatus)
    }

    suspend fun addExamples(examples: List<ExampleEntity>) = withContext(Dispatchers.IO) {
        dao.insertExamples(examples)
    }
}
