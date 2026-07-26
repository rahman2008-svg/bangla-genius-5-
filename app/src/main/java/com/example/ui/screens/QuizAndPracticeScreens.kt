package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.QuizQuestionEntity

@Composable
fun QuizHubScreen(
    onStartQuiz: (category: String) -> Unit,
    onStartMockTest: () -> Unit
) {
    val quizTypes = listOf(
        QuizCategoryItem("দৈনিক কুইজ (Daily Quiz)", "১০টি দৈনন্দিন বাছাইকৃত প্রশ্ন", Icons.Default.Today, "দৈনিক", Color(0xFF00695C)),
        QuizCategoryItem("BCS প্রস্তুতি কুইজ", "BCS প্রিলিমিনারি মডেল প্রশ্ন", Icons.Default.School, "BCS", Color(0xFF0288D1)),
        QuizCategoryItem("SSC & HSC ব্যাকরণ", "বোর্ড পরীক্ষার ব্যাকরণ MCQ", Icons.Default.MenuBook, "SSC/HSC", Color(0xFF7E57C2)),
        QuizCategoryItem("সমাস ও সন্ধি কুইজ", "সমাস, সন্ধি ও কারক স্পেশাল", Icons.Default.AutoAwesome, "ব্যাকরণ", Color(0xFFFF7043)),
        QuizCategoryItem("বিশ্ববিদ্যালয় ভর্তি কুইজ", "ঢাকা বিশ্ববিদ্যালয় ও গুচ্ছ ভর্তি", Icons.Default.AccountBalance, "ভর্তি", Color(0xFF2E7D32)),
        QuizCategoryItem("চাকরি প্রস্তুতি কুইজ", "ব্যাংক ও প্রাথমিক শিক্ষক নিয়োগ", Icons.Default.Work, "চাকরি", Color(0xFFC2185B))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Hero Mock Test Banner
        Card(
            onClick = onStartMockTest,
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(52.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Assignment,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "পূর্ণাঙ্গ মক টেস্ট (Full Mock Test)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "সময়ভিত্তিক পূর্ণাঙ্গ পরীক্ষা (SSC, BCS, Bank)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )
                }

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "কুইজ বিষয়শ্রেণী (Quiz Categories)",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(quizTypes) { quiz ->
                Card(
                    onClick = { onStartQuiz(quiz.categoryKey) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = quiz.color.copy(alpha = 0.15f),
                            modifier = Modifier.size(44.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = quiz.icon,
                                    contentDescription = quiz.title,
                                    tint = quiz.color
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = quiz.title,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = quiz.desc,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }

                        Button(
                            onClick = { onStartQuiz(quiz.categoryKey) },
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(text = "কুইজ দিন")
                        }
                    }
                }
            }
        }
    }
}

data class QuizCategoryItem(
    val title: String,
    val desc: String,
    val icon: ImageVector,
    val categoryKey: String,
    val color: Color
)

@Composable
fun ActiveQuizScreen(
    questions: List<QuizQuestionEntity>,
    currentIndex: Int,
    selectedAnswers: Map<Int, Int>,
    isSubmitted: Boolean,
    score: Int,
    onSelectAnswer: (questionId: Int, optionIndex: Int) -> Unit,
    onNextQuestion: () -> Unit,
    onPreviousQuestion: () -> Unit,
    onSubmitQuiz: () -> Unit,
    onFinishQuiz: () -> Unit
) {
    if (questions.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "কোনো প্রশ্ন পাওয়া যায়নি")
        }
        return
    }

    if (isSubmitted) {
        QuizResultView(
            totalQuestions = questions.size,
            score = score,
            onRestart = onFinishQuiz
        )
        return
    }

    val question = questions[currentIndex]
    val selectedOption = selectedAnswers[question.id]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            // Header Progress
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "প্রশ্ন ${currentIndex + 1} / ${questions.size}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Text(
                        text = question.category,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / questions.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Question Box
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = question.question,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 30.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Options List
            val options = listOf(question.optionA, question.optionB, question.optionC, question.optionD)
            options.forEachIndexed { index, optionText ->
                if (optionText.isNotEmpty()) {
                    val isSelected = selectedOption == index
                    OutlinedCard(
                        onClick = { onSelectAnswer(question.id, index) },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                        ),
                        border = CardDefaults.outlinedCardBorder(isSelected),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = ('ক' + index).toString(),
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Text(
                                text = optionText,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }

        // Navigation Footer Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (currentIndex > 0) {
                OutlinedButton(
                    onClick = onPreviousQuestion,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "পূর্ববর্তী")
                }
            } else {
                Spacer(modifier = Modifier.width(8.dp))
            }

            if (currentIndex < questions.size - 1) {
                Button(
                    onClick = onNextQuestion,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "পরবর্তী")
                }
            } else {
                Button(
                    onClick = onSubmitQuiz,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text(text = "কুইজ সাবমিট করুন")
                }
            }
        }
    }
}

@Composable
fun QuizResultView(
    totalQuestions: Int,
    score: Int,
    onRestart: () -> Unit
) {
    val percentage = if (totalQuestions > 0) (score.toFloat() / totalQuestions * 100).toInt() else 0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(90.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(52.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "কুইজ সম্পন্ন হয়েছে!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "আপনার স্কোর: $score / $totalQuestions ($percentage%)",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                val xpEarned = score * 15 + 10
                Text(
                    text = "+$xpEarned XP অর্জিত হয়েছে! 🌟",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = onRestart,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "হোমে ফিরে যান", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun MockTestScreen(
    onStartTest: (String) -> Unit
) {
    val mockTests = listOf(
        "SSC বাংলা ব্যাকরণ মডেল টেস্ট (৫০ নম্বর)",
        "HSC ব্যাকরণ ও নির্মিতি মক টেস্ট (৫০ নম্বর)",
        "BCS প্রিলিমিনারি বাংলা স্পেশাল টেস্ট (২০০ প্রশ্ন)",
        "বিশ্ববিদ্যালয় 'ক' ও 'খ' ইউনিট ভর্তি মক টেস্ট",
        "প্রাথমিক সহকারী শিক্ষক নিয়োগ মক টেস্ট",
        "ব্যাংক জবস বাংলা ব্যাকরণ স্পেশাল মডেল টেস্ট"
    )

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(mockTests) { test ->
            Card(
                onClick = { onStartTest(test) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = test,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "সময়: ২০ মিনিট • নেগেটিভ মার্কিং সহ",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                    Button(
                        onClick = { onStartTest(test) },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "পরীক্ষা দিন")
                    }
                }
            }
        }
    }
}
