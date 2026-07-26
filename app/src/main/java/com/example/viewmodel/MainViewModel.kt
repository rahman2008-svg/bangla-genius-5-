package com.example.viewmodel

import android.app.Application
import android.speech.tts.TextToSpeech
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.model.*
import com.example.data.repository.BanglaRepository
import com.example.ui.theme.AppFontFamily
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Locale

class MainViewModel(application: Application) : AndroidViewModel(application), TextToSpeech.OnInitListener {

    private val repository: BanglaRepository = BanglaRepository(AppDatabase.getInstance(application).banglaDao())
    private var tts: TextToSpeech? = null
    private var isTtsReady = false

    private val _dailyExample = MutableStateFlow<ExampleEntity?>(null)
    val dailyExample: StateFlow<ExampleEntity?> = _dailyExample.asStateFlow()

    init {
        viewModelScope.launch {
            repository.initializeDatabaseIfEmpty()
            _dailyExample.value = repository.getDailyExample()
        }

        try {
            tts = TextToSpeech(application, this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun toggleExampleBookmark(id: Int, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleExampleBookmark(id, currentStatus)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale("bn", "BD"))
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                isTtsReady = true
            } else {
                tts?.language = Locale.ENGLISH
                isTtsReady = true
            }
        }
    }

    fun speakText(text: String) {
        if (isTtsReady) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "BanglaGeniusTTS")
        }
    }

    fun stopSpeech() {
        tts?.stop()
    }

    override fun onCleared() {
        tts?.stop()
        tts?.shutdown()
        super.onCleared()
    }

    // --- User Progress & Settings ---
    val userProgress: StateFlow<UserProgressEntity?> = repository.userProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Current Navigation Route State
    private val _currentRoute = MutableStateFlow("splash")
    val currentRoute: StateFlow<String> = _currentRoute.asStateFlow()

    fun navigateTo(route: String) {
        _currentRoute.value = route
    }

    // Active Category Filter for Lessons
    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    fun selectCategory(category: String?) {
        _selectedCategory.value = category
    }

    // Selected Lesson Detail ID
    private val _selectedLessonId = MutableStateFlow<Int?>(null)
    val selectedLessonId: StateFlow<Int?> = _selectedLessonId.asStateFlow()

    fun selectLesson(id: Int) {
        _selectedLessonId.value = id
        _currentRoute.value = "lesson_detail"
    }

    // Search Query
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // --- Examples ---
    private val _exampleCategoryFilter = MutableStateFlow<String?>(null)
    val exampleCategoryFilter: StateFlow<String?> = _exampleCategoryFilter.asStateFlow()

    private val _exampleDifficultyFilter = MutableStateFlow<String?>(null)
    val exampleDifficultyFilter: StateFlow<String?> = _exampleDifficultyFilter.asStateFlow()

    private val _exampleExamLevelFilter = MutableStateFlow<String?>(null)
    val exampleExamLevelFilter: StateFlow<String?> = _exampleExamLevelFilter.asStateFlow()

    fun setExampleCategoryFilter(cat: String?) { _exampleCategoryFilter.value = cat }
    fun setExampleDifficultyFilter(diff: String?) { _exampleDifficultyFilter.value = diff }
    fun setExampleExamLevelFilter(level: String?) { _exampleExamLevelFilter.value = level }

    val examplesList: StateFlow<List<ExampleEntity>> = combine(
        repository.allExamples,
        _searchQuery,
        _exampleCategoryFilter,
        _exampleDifficultyFilter,
        _exampleExamLevelFilter
    ) { all, query, cat, diff, level ->
        var filtered = all
        if (!cat.isNullOrEmpty()) {
            filtered = filtered.filter { it.category == cat }
        }
        if (!diff.isNullOrEmpty()) {
            filtered = filtered.filter { it.difficulty.equals(diff, ignoreCase = true) }
        }
        if (!level.isNullOrEmpty() && level != "ALL") {
            filtered = filtered.filter { it.examLevel.contains(level, ignoreCase = true) || it.examLevel == "ALL" }
        }
        if (query.isNotEmpty()) {
            filtered = filtered.filter {
                it.example.contains(query, ignoreCase = true) ||
                it.analysis.contains(query, ignoreCase = true) ||
                it.explanation.contains(query, ignoreCase = true) ||
                it.category.contains(query, ignoreCase = true) ||
                it.subcategory.contains(query, ignoreCase = true) ||
                it.tags.contains(query, ignoreCase = true) ||
                it.topic.contains(query, ignoreCase = true)
            }
        }
        filtered
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedExamples: StateFlow<List<ExampleEntity>> = repository.bookmarkedExamples
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val mostViewedExamples: StateFlow<List<ExampleEntity>> = repository.getMostViewedExamples(10)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun incrementExampleView(id: Int) {
        viewModelScope.launch {
            repository.incrementExampleView(id)
        }
    }

    fun updateExampleNotes(id: Int, notes: String) {
        viewModelScope.launch {
            repository.updateExampleNotes(id, notes)
        }
    }

    // Lessons List
    val lessonsList: StateFlow<List<LessonEntity>> = combine(
        repository.allLessons,
        _selectedCategory,
        _searchQuery
    ) { all, cat, query ->
        var list = all
        if (!cat.isNullOrEmpty()) {
            list = list.filter { it.category == cat }
        }
        if (query.isNotEmpty()) {
            list = list.filter {
                it.title.contains(query, ignoreCase = true) ||
                it.definition.contains(query, ignoreCase = true) ||
                it.category.contains(query, ignoreCase = true) ||
                it.subtopic.contains(query, ignoreCase = true)
            }
        }
        list
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Bookmarked Lessons
    val bookmarkedLessons: StateFlow<List<LessonEntity>> = repository.bookmarkedLessons
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Selected Lesson Detail Entity
    val selectedLesson: StateFlow<LessonEntity?> = combine(
        repository.allLessons,
        _selectedLessonId
    ) { all, id ->
        all.find { it.id == id }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun toggleLessonBookmark(id: Int, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleLessonBookmark(id, currentStatus)
        }
    }

    fun markLessonCompleted(id: Int) {
        viewModelScope.launch {
            repository.markLessonCompleted(id)
        }
    }

    // --- Dictionary ---
    val dictionaryWords: StateFlow<List<DictionaryEntity>> = combine(
        repository.allWords,
        _searchQuery
    ) { words, query ->
        if (query.isEmpty()) words
        else words.filter {
            it.word.contains(query, ignoreCase = true) ||
            it.meaningBangla.contains(query, ignoreCase = true) ||
            it.meaningEnglish.contains(query, ignoreCase = true)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedWords: StateFlow<List<DictionaryEntity>> = repository.bookmarkedWords
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleWordBookmark(id: Int, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleWordBookmark(id, currentStatus)
        }
    }

    // --- Quiz System ---
    private val _quizQuestions = MutableStateFlow<List<QuizQuestionEntity>>(emptyList())
    val quizQuestions: StateFlow<List<QuizQuestionEntity>> = _quizQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap()) // QuestionId -> SelectedOptionIndex
    val selectedAnswers: StateFlow<Map<Int, Int>> = _selectedAnswers.asStateFlow()

    private val _isQuizSubmitted = MutableStateFlow(false)
    val isQuizSubmitted: StateFlow<Boolean> = _isQuizSubmitted.asStateFlow()

    private val _quizScore = MutableStateFlow(0)
    val quizScore: StateFlow<Int> = _quizScore.asStateFlow()

    fun startQuiz(category: String = "All", count: Int = 5) {
        viewModelScope.launch {
            val questions = if (category == "All") {
                repository.getRandomQuestions(count)
            } else {
                repository.getQuestionsByCategory(category).ifEmpty { repository.getRandomQuestions(count) }
            }
            _quizQuestions.value = questions
            _currentQuestionIndex.value = 0
            _selectedAnswers.value = emptyMap()
            _isQuizSubmitted.value = false
            _quizScore.value = 0
            _currentRoute.value = "quiz_active"
        }
    }

    fun selectQuizAnswer(questionId: Int, optionIndex: Int) {
        val updated = _selectedAnswers.value.toMutableMap()
        updated[questionId] = optionIndex
        _selectedAnswers.value = updated
    }

    fun nextQuizQuestion() {
        if (_currentQuestionIndex.value < _quizQuestions.value.size - 1) {
            _currentQuestionIndex.value += 1
        }
    }

    fun previousQuizQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value -= 1
        }
    }

    fun submitQuiz() {
        var score = 0
        _quizQuestions.value.forEach { q ->
            val userSelected = _selectedAnswers.value[q.id]
            if (userSelected == q.correctAnswerIndex) {
                score++
            }
        }
        _quizScore.value = score
        _isQuizSubmitted.value = true

        viewModelScope.launch {
            repository.addXpAndProgress(
                xpGained = score * 15 + 10,
                quizAttempted = true,
                isCorrect = true
            )
        }
    }

    // --- User Notes ---
    val allNotes: StateFlow<List<UserNoteEntity>> = repository.allNotes
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun saveNote(title: String, content: String, folder: String, colorHex: String) {
        viewModelScope.launch {
            repository.saveNote(
                UserNoteEntity(
                    title = title,
                    content = content,
                    folder = folder,
                    colorHex = colorHex,
                    updatedAt = System.currentTimeMillis()
                )
            )
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }

    // --- Flashcards ---
    val allFlashcards: StateFlow<List<FlashcardEntity>> = repository.allFlashcards
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleFlashcardLearned(id: Int, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.toggleFlashcardLearned(id, currentStatus)
        }
    }

    // --- Achievements ---
    val allAchievements: StateFlow<List<AchievementEntity>> = repository.allAchievements
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Settings
    fun updateSettings(font: AppFontFamily, fontScale: Float, isDarkMode: Boolean, isVoiceReadingEnabled: Boolean) {
        viewModelScope.launch {
            repository.updateSettings(
                font = font.name,
                fontScale = fontScale,
                isDarkMode = isDarkMode,
                isVoiceReadingEnabled = isVoiceReadingEnabled
            )
        }
    }

    fun completeSetup(font: AppFontFamily, isDarkMode: Boolean) {
        viewModelScope.launch {
            repository.completeSetup(font = font.name, isDarkMode = isDarkMode)
            _currentRoute.value = "home"
        }
    }

    // Admin panel additions
    fun addAdminLesson(title: String, category: String, subtopic: String, definition: String, explanation: String, examples: String, mnemonics: String) {
        viewModelScope.launch {
            val newId = (lessonsList.value.maxOfOrNull { it.id } ?: 0) + 1
            repository.addCustomLesson(
                LessonEntity(
                    id = newId,
                    category = category,
                    subtopic = subtopic,
                    title = title,
                    definition = definition,
                    explanation = explanation,
                    examples = examples,
                    mnemonics = mnemonics
                )
            )
        }
    }

    fun addAdminWord(word: String, pronunciation: String, meaningBangla: String, meaningEnglish: String, partOfSpeech: String, example: String) {
        viewModelScope.launch {
            val newId = (dictionaryWords.value.maxOfOrNull { it.id } ?: 0) + 1
            repository.addCustomWord(
                DictionaryEntity(
                    id = newId,
                    word = word,
                    pronunciation = pronunciation,
                    meaningBangla = meaningBangla,
                    meaningEnglish = meaningEnglish,
                    partOfSpeech = partOfSpeech,
                    exampleSentence = example
                )
            )
        }
    }

    fun addAdminQuestion(question: String, category: String, optionA: String, optionB: String, optionC: String, optionD: String, correctIdx: Int, explanation: String) {
        viewModelScope.launch {
            val newId = (quizQuestions.value.maxOfOrNull { it.id } ?: 0) + 1
            repository.addCustomQuestion(
                QuizQuestionEntity(
                    id = newId,
                    category = category,
                    question = question,
                    optionA = optionA,
                    optionB = optionB,
                    optionC = optionC,
                    optionD = optionD,
                    correctAnswerIndex = correctIdx,
                    explanation = explanation
                )
            )
        }
    }
}
