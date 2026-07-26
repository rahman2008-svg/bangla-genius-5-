package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lessons")
data class LessonEntity(
    @PrimaryKey val id: Int,
    val category: String,
    val subtopic: String,
    val title: String,
    val definition: String,
    val explanation: String,
    val tableChartHtml: String = "",
    val examples: String = "",
    val mnemonics: String = "",
    val isBookmarked: Boolean = false,
    val isCompleted: Boolean = false
)

@Entity(tableName = "dictionary")
data class DictionaryEntity(
    @PrimaryKey val id: Int,
    val word: String,
    val pronunciation: String,
    val meaningBangla: String,
    val meaningEnglish: String,
    val partOfSpeech: String,
    val synonyms: String = "",
    val antonyms: String = "",
    val exampleSentence: String = "",
    val isBookmarked: Boolean = false
)

@Entity(tableName = "quiz_questions")
data class QuizQuestionEntity(
    @PrimaryKey val id: Int,
    val category: String,
    val questionType: String = "MCQ", // MCQ, FILL_BLANK, TRUE_FALSE, SPELLING_CORRECTION
    val question: String,
    val optionA: String = "",
    val optionB: String = "",
    val optionC: String = "",
    val optionD: String = "",
    val correctAnswerIndex: Int = 0,
    val correctAnswerText: String = "",
    val explanation: String = ""
)

@Entity(tableName = "user_notes")
data class UserNoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val folder: String = "সাধারণ",
    val colorHex: String = "#00695C",
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: Int = 1,
    val xp: Int = 180,
    val level: Int = 2,
    val dailyStreak: Int = 5,
    val lessonsReadCount: Int = 6,
    val quizzesTakenCount: Int = 12,
    val correctAnswersCount: Int = 38,
    val totalQuestionsAttempted: Int = 45,
    val totalStudyTimeMinutes: Int = 62,
    val selectedFont: String = "SOLAIMAN_LIPI",
    val selectedFontScale: Float = 1.0f,
    val isDarkMode: Boolean = false,
    val isVoiceReadingEnabled: Boolean = false,
    val hasCompletedWelcome: Boolean = true
)

@Entity(tableName = "flashcards")
data class FlashcardEntity(
    @PrimaryKey val id: Int,
    val category: String,
    val frontText: String,
    val backText: String,
    val hint: String = "",
    val isLearned: Boolean = false
)

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val badgeType: String = "GOLD", // BRONZE, SILVER, GOLD, DIAMOND
    val isUnlocked: Boolean = false,
    val requiredCount: Int = 1,
    val currentCount: Int = 0
)

@Entity(tableName = "examples")
data class ExampleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val lessonId: Int,
    val category: String = "",
    val topic: String = "",
    val example: String,
    val type: String = "normal", // normal, grammar_analysis, correct_incorrect, exam, practice, advanced
    val analysis: String = "",
    val grammarPoint: String = "",
    val explanation: String = "",
    val difficulty: String = "EASY", // EASY, MEDIUM, HARD
    val examLevel: String = "ALL", // SSC, HSC, BCS, ADMISSION, JOB, ALL
    val isBookmarked: Boolean = false,
    val subcategory: String = "",
    val grammarRule: String = "",
    val tags: String = "",
    val sourceType: String = "STANDARD", // STANDARD, LITERATURE, SPOKEN, EXAM
    val userNotes: String = "",
    val viewCount: Int = 0,
    val orderIndex: Int = 1
)
