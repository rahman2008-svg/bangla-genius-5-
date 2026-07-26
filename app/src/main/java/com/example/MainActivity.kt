package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.*
import com.example.ui.screens.*
import com.example.ui.theme.AppFontFamily
import com.example.ui.theme.BanglaGeniusTheme
import com.example.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val viewModel: MainViewModel = viewModel()
            val userProgress by viewModel.userProgress.collectAsStateWithLifecycle()
            val currentRoute by viewModel.currentRoute.collectAsStateWithLifecycle()

            val fontOption = remember(userProgress?.selectedFont) {
                try {
                    AppFontFamily.valueOf(userProgress?.selectedFont ?: "SOLAIMAN_LIPI")
                } catch (e: Exception) {
                    AppFontFamily.SOLAIMAN_LIPI
                }
            }
            val fontScale = userProgress?.selectedFontScale ?: 1.0f
            val isDarkMode = userProgress?.isDarkMode ?: false

            BanglaGeniusTheme(
                darkTheme = isDarkMode,
                fontFamily = fontOption,
                fontScale = fontScale
            ) {
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                val showAppBars = currentRoute !in listOf("splash", "welcome", "setup")

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = showAppBars,
                    drawerContent = {
                        BanglaDrawerContent(
                            currentRoute = currentRoute,
                            userProgress = userProgress,
                            onNavigate = { route -> viewModel.navigateTo(route) },
                            onCloseDrawer = { scope.launch { drawerState.close() } }
                        )
                    }
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            if (showAppBars) {
                                BanglaTopBar(
                                    title = when (currentRoute) {
                                        "home" -> "বাংলা জিনিয়াস"
                                        "grammar" -> "ব্যাকরণ অধ্যায়"
                                        "lesson_detail" -> "পাঠ বিস্তারিত"
                                        "dictionary" -> "বাংলা অভিধান"
                                        "quiz", "quiz_active" -> "কুইজ ও পরীক্ষা"
                                        "mock_test" -> "মক টেস্ট"
                                        "flashcards" -> "ফ্ল্যাশকার্ড"
                                        "notes" -> "আমার নোটস"
                                        "bookmarks" -> "বুকমার্কস"
                                        "profile" -> "আমার অগ্রগতি"
                                        "admin" -> "অ্যাডমিন প্যানেল"
                                        "settings" -> "সেটিংস"
                                        "search" -> "অনুসন্ধান"
                                        else -> "বাংলা জিনিয়াস"
                                    },
                                    currentRoute = currentRoute,
                                    userProgress = userProgress,
                                    onOpenDrawer = { scope.launch { drawerState.open() } },
                                    onNavigate = { route -> viewModel.navigateTo(route) },
                                    onBack = if (currentRoute in listOf("lesson_detail", "quiz_active", "search")) {
                                        {
                                            if (currentRoute == "lesson_detail") viewModel.navigateTo("grammar")
                                            else if (currentRoute == "quiz_active") viewModel.navigateTo("quiz")
                                            else viewModel.navigateTo("home")
                                        }
                                    } else null
                                )
                            }
                        },
                        bottomBar = {
                            if (showAppBars) {
                                BanglaBottomBar(
                                    currentRoute = currentRoute,
                                    onNavigate = { route -> viewModel.navigateTo(route) }
                                )
                            }
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            when (currentRoute) {
                                "splash" -> SplashScreen(
                                    onSplashFinished = {
                                        if (userProgress?.hasCompletedWelcome == true) {
                                            viewModel.navigateTo("home")
                                        } else {
                                            viewModel.navigateTo("welcome")
                                        }
                                    }
                                )

                                "welcome" -> WelcomeScreen(
                                    onContinue = { viewModel.navigateTo("setup") }
                                )

                                "setup" -> SetupScreen(
                                    onCompleteSetup = { font, isDark ->
                                        viewModel.completeSetup(font, isDark)
                                    }
                                )

                                "home" -> {
                                    val lessonsList by viewModel.lessonsList.collectAsStateWithLifecycle()
                                    val dailyExample by viewModel.dailyExample.collectAsStateWithLifecycle()
                                    HomeScreen(
                                        userProgress = userProgress,
                                        lessons = lessonsList,
                                        dailyExample = dailyExample,
                                        onSelectCategory = { cat -> viewModel.selectCategory(cat) },
                                        onSelectLesson = { id -> viewModel.selectLesson(id) },
                                        onStartQuiz = { viewModel.startQuiz() },
                                        onNavigate = { route -> viewModel.navigateTo(route) }
                                    )
                                }

                                "grammar" -> {
                                    val lessonsList by viewModel.lessonsList.collectAsStateWithLifecycle()
                                    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
                                    GrammarCategoryScreen(
                                        lessons = lessonsList,
                                        selectedCategory = selectedCategory,
                                        onSelectCategory = { cat -> viewModel.selectCategory(cat) },
                                        onSelectLesson = { id -> viewModel.selectLesson(id) },
                                        onToggleBookmark = { id, status -> viewModel.toggleLessonBookmark(id, status) }
                                    )
                                }

                                "lesson_detail" -> {
                                    val selectedLesson by viewModel.selectedLesson.collectAsStateWithLifecycle()
                                    val examplesList by viewModel.examplesList.collectAsStateWithLifecycle()
                                    val lessonExamples = remember(selectedLesson, examplesList) {
                                        if (selectedLesson == null) emptyList()
                                        else examplesList.filter { it.lessonId == selectedLesson?.id || it.category == selectedLesson?.category }
                                    }
                                    LessonDetailScreen(
                                        lesson = selectedLesson,
                                        examples = lessonExamples,
                                        onToggleBookmark = { id, status -> viewModel.toggleLessonBookmark(id, status) },
                                        onToggleExampleBookmark = { id, status -> viewModel.toggleExampleBookmark(id, status) },
                                        onMarkCompleted = { id -> viewModel.markLessonCompleted(id) },
                                        onSpeakText = { text -> viewModel.speakText(text) },
                                        onStopSpeech = { viewModel.stopSpeech() },
                                        onSaveNote = { title, content, folder, color ->
                                            viewModel.saveNote(title, content, folder, color)
                                        }
                                    )
                                }

                                "dictionary" -> {
                                    val words by viewModel.dictionaryWords.collectAsStateWithLifecycle()
                                    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
                                    DictionaryScreen(
                                        words = words,
                                        searchQuery = searchQuery,
                                        onSearchQueryChange = { q -> viewModel.updateSearchQuery(q) },
                                        onToggleBookmark = { id, status -> viewModel.toggleWordBookmark(id, status) },
                                        onSpeakText = { text -> viewModel.speakText(text) }
                                    )
                                }

                                "quiz" -> {
                                    QuizHubScreen(
                                        onStartQuiz = { cat -> viewModel.startQuiz(category = cat) },
                                        onStartMockTest = { viewModel.navigateTo("mock_test") }
                                    )
                                }

                                "quiz_active" -> {
                                    val questions by viewModel.quizQuestions.collectAsStateWithLifecycle()
                                    val currentIndex by viewModel.currentQuestionIndex.collectAsStateWithLifecycle()
                                    val selectedAnswers by viewModel.selectedAnswers.collectAsStateWithLifecycle()
                                    val isSubmitted by viewModel.isQuizSubmitted.collectAsStateWithLifecycle()
                                    val score by viewModel.quizScore.collectAsStateWithLifecycle()

                                    ActiveQuizScreen(
                                        questions = questions,
                                        currentIndex = currentIndex,
                                        selectedAnswers = selectedAnswers,
                                        isSubmitted = isSubmitted,
                                        score = score,
                                        onSelectAnswer = { qId, opt -> viewModel.selectQuizAnswer(qId, opt) },
                                        onNextQuestion = { viewModel.nextQuizQuestion() },
                                        onPreviousQuestion = { viewModel.previousQuizQuestion() },
                                        onSubmitQuiz = { viewModel.submitQuiz() },
                                        onFinishQuiz = { viewModel.navigateTo("home") }
                                    )
                                }

                                "mock_test" -> {
                                    MockTestScreen(
                                        onStartTest = { viewModel.startQuiz(count = 10) }
                                    )
                                }

                                "flashcards" -> {
                                    val flashcards by viewModel.allFlashcards.collectAsStateWithLifecycle()
                                    FlashcardsScreen(
                                        flashcards = flashcards,
                                        onToggleLearned = { id, status -> viewModel.toggleFlashcardLearned(id, status) }
                                    )
                                }

                                "notes" -> {
                                    val notes by viewModel.allNotes.collectAsStateWithLifecycle()
                                    NotesScreen(
                                        notes = notes,
                                        onSaveNote = { title, content, folder, color ->
                                            viewModel.saveNote(title, content, folder, color)
                                        },
                                        onDeleteNote = { id -> viewModel.deleteNote(id) }
                                    )
                                }

                                "bookmarks" -> {
                                    val bookmarkedLessons by viewModel.bookmarkedLessons.collectAsStateWithLifecycle()
                                    val bookmarkedWords by viewModel.bookmarkedWords.collectAsStateWithLifecycle()
                                    val bookmarkedExamples by viewModel.bookmarkedExamples.collectAsStateWithLifecycle()
                                    BookmarksScreen(
                                        bookmarkedLessons = bookmarkedLessons,
                                        bookmarkedWords = bookmarkedWords,
                                        bookmarkedExamples = bookmarkedExamples,
                                        onSelectLesson = { id -> viewModel.selectLesson(id) },
                                        onToggleLessonBookmark = { id, status -> viewModel.toggleLessonBookmark(id, status) },
                                        onToggleWordBookmark = { id, status -> viewModel.toggleWordBookmark(id, status) },
                                        onToggleExampleBookmark = { id, status -> viewModel.toggleExampleBookmark(id, status) }
                                    )
                                }

                                "profile" -> {
                                    val achievements by viewModel.allAchievements.collectAsStateWithLifecycle()
                                    ProfileStatsScreen(
                                        userProgress = userProgress,
                                        achievements = achievements
                                    )
                                }

                                "admin" -> {
                                    AdminPanelScreen(
                                        onAddLesson = { t, c, s, d, e, ex, m ->
                                            viewModel.addAdminLesson(t, c, s, d, e, ex, m)
                                        },
                                        onAddWord = { w, p, mb, me, pos, ex ->
                                            viewModel.addAdminWord(w, p, mb, me, pos, ex)
                                        },
                                        onAddQuestion = { q, c, a, b, gc, d, ci, exp ->
                                            viewModel.addAdminQuestion(q, c, a, b, gc, d, ci, exp)
                                        }
                                    )
                                }

                                "settings" -> {
                                    SettingsScreen(
                                        userProgress = userProgress,
                                        onUpdateSettings = { font, fontScale, isDark, isVoice ->
                                            viewModel.updateSettings(font, fontScale, isDark, isVoice)
                                        }
                                    )
                                }

                                "search" -> {
                                    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
                                    val lessonsList by viewModel.lessonsList.collectAsStateWithLifecycle()
                                    val dictionaryWords by viewModel.dictionaryWords.collectAsStateWithLifecycle()
                                    val examplesList by viewModel.examplesList.collectAsStateWithLifecycle()

                                    SearchScreen(
                                        searchQuery = searchQuery,
                                        onSearchQueryChange = { q -> viewModel.updateSearchQuery(q) },
                                        lessons = lessonsList,
                                        words = dictionaryWords,
                                        examples = examplesList,
                                        onSelectLesson = { id -> viewModel.selectLesson(id) },
                                        onToggleLessonBookmark = { id, status -> viewModel.toggleLessonBookmark(id, status) },
                                        onToggleExampleBookmark = { id, status -> viewModel.toggleExampleBookmark(id, status) }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
