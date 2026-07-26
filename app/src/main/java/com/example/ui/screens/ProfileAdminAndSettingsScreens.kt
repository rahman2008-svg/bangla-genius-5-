package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AchievementEntity
import com.example.data.model.UserProgressEntity
import com.example.ui.theme.AppFontFamily

@Composable
fun ProfileStatsScreen(
    userProgress: UserProgressEntity?,
    achievements: List<AchievementEntity>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // User Rank Header Card
        userProgress?.let { progress ->
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(76.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Stars,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier.size(44.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "বাংলা ব্যাকরণবিদ (Rank)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = "লেভেল ${progress.level} • ${progress.xp} XP",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        StatTile(title = "ধারাবাহিক", value = "${progress.dailyStreak} দিন")
                        StatTile(title = "পঠিত পাঠ", value = "${progress.lessonsReadCount}টি")
                        StatTile(title = "কুইজ গ্রহণ", value = "${progress.quizzesTakenCount}টি")
                        StatTile(title = "অধ্যয়ন সময়", value = "${progress.totalStudyTimeMinutes} মিনিট")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Badges & Achievements Section
        Text(
            text = "অর্জন ও ব্যাজ (Achievements & Badges)",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            achievements.forEach { badge ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (badge.isUnlocked) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = when (badge.badgeType) {
                                "GOLD" -> Color(0xFFFFB300)
                                "DIAMOND" -> Color(0xFF00ACC1)
                                "SILVER" -> Color(0xFFB0BEC5)
                                else -> Color(0xFF8D6E63)
                            },
                            modifier = Modifier.size(48.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.EmojiEvents,
                                    contentDescription = badge.title,
                                    tint = Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = badge.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = badge.description,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                            )
                        }

                        if (badge.isUnlocked) {
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = Color(0xFF2E7D32).copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "আনলকড ✓",
                                    color = Color(0xFF2E7D32),
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatTile(title: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun AdminPanelScreen(
    onAddLesson: (title: String, category: String, subtopic: String, definition: String, explanation: String, examples: String, mnemonics: String) -> Unit,
    onAddWord: (word: String, pronunciation: String, meaningBangla: String, meaningEnglish: String, partOfSpeech: String, example: String) -> Unit,
    onAddQuestion: (question: String, category: String, optionA: String, optionB: String, optionC: String, optionD: String, correctIdx: Int, explanation: String) -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }

    // Lesson form state
    var lessonTitle by remember { mutableStateOf("") }
    var lessonCat by remember { mutableStateOf("সমাস") }
    var lessonSub by remember { mutableStateOf("প্রকারভেদ") }
    var lessonDef by remember { mutableStateOf("") }
    var lessonExp by remember { mutableStateOf("") }
    var lessonEx by remember { mutableStateOf("") }
    var lessonMnem by remember { mutableStateOf("") }

    // Word form state
    var wordText by remember { mutableStateOf("") }
    var wordPron by remember { mutableStateOf("") }
    var wordMeanBn by remember { mutableStateOf("") }
    var wordMeanEn by remember { mutableStateOf("") }
    var wordPos by remember { mutableStateOf("বিশেষ্য") }

    // Question form state
    var qText by remember { mutableStateOf("") }
    var qCat by remember { mutableStateOf("BCS Exam") }
    var qOpA by remember { mutableStateOf("") }
    var qOpB by remember { mutableStateOf("") }
    var qOpC by remember { mutableStateOf("") }
    var qOpD by remember { mutableStateOf("") }
    var qCorrIdx by remember { mutableStateOf(0) }
    var qExp by remember { mutableStateOf("") }

    var statusMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "অ্যাডমিন প্যানেল (Content Admin)",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "নতুন অধ্যায়, কুইজ প্রশ্ন ও অভিধান শব্দ অফলাইনে যুক্ত করুন।",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        TabRow(selectedTabIndex = selectedTab) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("নতুন অধ্যায়") })
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("নতুন শব্দ") })
            Tab(selected = selectedTab == 2, onClick = { selectedTab = 2 }, text = { Text("নতুন কুইজ") })
        }

        Spacer(modifier = Modifier.height(16.dp))

        statusMessage?.let { msg ->
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF2E7D32).copy(alpha = 0.15f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = msg,
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(12.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (selectedTab == 0) {
            // Add Lesson
            OutlinedTextField(value = lessonTitle, onValueChange = { lessonTitle = it }, label = { Text("অধ্যায় শিরোনাম") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = lessonCat, onValueChange = { lessonCat = it }, label = { Text("ক্যাটাগরি (যেমন: সমাস)") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = lessonSub, onValueChange = { lessonSub = it }, label = { Text("উপবিষয়") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = lessonDef, onValueChange = { lessonDef = it }, label = { Text("সংজ্ঞা") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = lessonExp, onValueChange = { lessonExp = it }, label = { Text("সহজ ব্যাখ্যা") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = lessonEx, onValueChange = { lessonEx = it }, label = { Text("উদাহরণ") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = lessonMnem, onValueChange = { lessonMnem = it }, label = { Text("মনে রাখার কৌশল") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (lessonTitle.isNotEmpty() && lessonDef.isNotEmpty()) {
                        onAddLesson(lessonTitle, lessonCat, lessonSub, lessonDef, lessonExp, lessonEx, lessonMnem)
                        statusMessage = "নতুন অধ্যায় সফলভাবে সংরক্ষণ করা হয়েছে!"
                        lessonTitle = ""
                        lessonDef = ""
                        lessonExp = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("অধ্যায় সেভ করুন")
            }
        } else if (selectedTab == 1) {
            // Add Word
            OutlinedTextField(value = wordText, onValueChange = { wordText = it }, label = { Text("শব্দ") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = wordPron, onValueChange = { wordPron = it }, label = { Text("উচ্চারণ") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = wordMeanBn, onValueChange = { wordMeanBn = it }, label = { Text("বাংলা অর্থ") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = wordMeanEn, onValueChange = { wordMeanEn = it }, label = { Text("ইংরেজি অর্থ") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = wordPos, onValueChange = { wordPos = it }, label = { Text("পদ (Part of Speech)") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (wordText.isNotEmpty() && wordMeanBn.isNotEmpty()) {
                        onAddWord(wordText, wordPron, wordMeanBn, wordMeanEn, wordPos, "")
                        statusMessage = "নতুন অভিধান শব্দ যুক্ত করা হয়েছে!"
                        wordText = ""
                        wordMeanBn = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("শব্দ সেভ করুন")
            }
        } else {
            // Add Quiz Question
            OutlinedTextField(value = qText, onValueChange = { qText = it }, label = { Text("প্রশ্ন") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = qCat, onValueChange = { qCat = it }, label = { Text("পরীক্ষা / ক্যাটাগরি") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = qOpA, onValueChange = { qOpA = it }, label = { Text("অপশন (ক)") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = qOpB, onValueChange = { qOpB = it }, label = { Text("অপশন (খ)") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = qOpC, onValueChange = { qOpC = it }, label = { Text("অপশন (গ)") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = qOpD, onValueChange = { qOpD = it }, label = { Text("অপশন (ঘ)") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = qExp, onValueChange = { qExp = it }, label = { Text("ব্যাখ্যা") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (qText.isNotEmpty() && qOpA.isNotEmpty()) {
                        onAddQuestion(qText, qCat, qOpA, qOpB, qOpC, qOpD, qCorrIdx, qExp)
                        statusMessage = "নতুন কুইজ প্রশ্ন যুক্ত করা হয়েছে!"
                        qText = ""
                        qOpA = ""
                        qOpB = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("প্রশ্ন সেভ করুন")
            }
        }
    }
}

@Composable
fun SettingsScreen(
    userProgress: UserProgressEntity?,
    onUpdateSettings: (font: AppFontFamily, fontScale: Float, isDarkMode: Boolean, isVoiceReadingEnabled: Boolean) -> Unit
) {
    var selectedFont by remember {
        mutableStateOf(
            try { AppFontFamily.valueOf(userProgress?.selectedFont ?: "SOLAIMAN_LIPI") }
            catch (e: Exception) { AppFontFamily.SOLAIMAN_LIPI }
        )
    }
    var fontScale by remember { mutableStateOf(userProgress?.selectedFontScale ?: 1.0f) }
    var isDarkMode by remember { mutableStateOf(userProgress?.isDarkMode ?: false) }
    var isVoiceReading by remember { mutableStateOf(userProgress?.isVoiceReadingEnabled ?: true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "সেটিংস (Settings)",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Dark Theme Switch
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "ডার্ক থিম (Dark Mode)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(text = "চোখের আরামের জন্য ডার্ক ক্যানভাস", style = MaterialTheme.typography.bodyMedium)
                }
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = {
                        isDarkMode = it
                        onUpdateSettings(selectedFont, fontScale, isDarkMode, isVoiceReading)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Text To Speech Switch
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "ভয়েস রিডিং (Voice Reader)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Text(text = "উচ্চস্বরে পাঠ পড়ে শোনানোর অপশন", style = MaterialTheme.typography.bodyMedium)
                }
                Switch(
                    checked = isVoiceReading,
                    onCheckedChange = {
                        isVoiceReading = it
                        onUpdateSettings(selectedFont, fontScale, isDarkMode, isVoiceReading)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Font Size Slider
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "ফন্ট সাইজ স্কেলিং (Text Size)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Slider(
                    value = fontScale,
                    onValueChange = {
                        fontScale = it
                        onUpdateSettings(selectedFont, fontScale, isDarkMode, isVoiceReading)
                    },
                    valueRange = 0.85f..1.30f
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Font Selection
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "ফন্ট স্টাইল (Font Choice)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                AppFontFamily.values().forEach { font ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedFont = font
                                onUpdateSettings(selectedFont, fontScale, isDarkMode, isVoiceReading)
                            }
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedFont == font,
                            onClick = {
                                selectedFont = font
                                onUpdateSettings(selectedFont, fontScale, isDarkMode, isVoiceReading)
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = font.displayName, style = MaterialTheme.typography.bodyLarge)
                    }
                }
            }
        }
    }
}
