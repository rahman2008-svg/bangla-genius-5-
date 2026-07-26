package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ExampleEntity
import com.example.data.model.LessonEntity
import com.example.data.model.UserProgressEntity

data class CategoryItemData(
    val name: String,
    val countText: String,
    val icon: ImageVector,
    val badgeColor: Color
)

@Composable
fun HomeScreen(
    userProgress: UserProgressEntity?,
    lessons: List<LessonEntity>,
    dailyExample: ExampleEntity? = null,
    onSelectCategory: (String) -> Unit,
    onSelectLesson: (Int) -> Unit,
    onStartQuiz: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val categories = listOf(
        CategoryItemData("বাংলা ভাষার পরিচয়", "৫০ অধ্যায় (১-৫০)", Icons.Default.HistoryEdu, Color(0xFF4F46E5)),
        CategoryItemData("বর্ণতত্ত্ব", "৪০ অধ্যায় (৫১-৯০)", Icons.Default.Abc, Color(0xFF059669)),
        CategoryItemData("ধ্বনিতত্ত্ব", "৪০ অধ্যায় (৯১-১৩০)", Icons.Default.RecordVoiceOver, Color(0xFF0284C7)),
        CategoryItemData("বানান ও বানান শুদ্ধি", "৫০ অধ্যায় (১৩১-১৮০)", Icons.Default.Rule, Color(0xFFE11D48)),
        CategoryItemData("শব্দতত্ত্ব", "৬০ অধ্যায় (১৮১-২৪০)", Icons.Default.Category, Color(0xFF2563EB)),
        CategoryItemData("পদ", "৬০ অধ্যায় (২৪১-৩০০)", Icons.Default.Class, Color(0xFF7C3AED)),
        CategoryItemData("বাক্যতত্ত্ব", "৬০ অধ্যায় (৩০১-৩৬০)", Icons.Default.ShortText, Color(0xFFDC2626)),
        CategoryItemData("কারক ও বিভক্তি", "৪০ অধ্যায় (৩৬১-৪০০)", Icons.Default.AccountTree, Color(0xFF047857)),
        CategoryItemData("সমাস", "৪০ অধ্যায় (৪০১-৪৪০)", Icons.Default.MergeType, Color(0xFFD97706)),
        CategoryItemData("সন্ধি", "৪০ অধ্যায় (৪৪১-৪৮০)", Icons.Default.CallMerge, Color(0xFF0891B2)),
        CategoryItemData("উপসর্গ", "২৫ অধ্যায় (৪৮১-৫০৫)", Icons.Default.Title, Color(0xFF4338CA)),
        CategoryItemData("প্রত্যয়", "২৫ অধ্যায় (৫০৬-৫৩০)", Icons.Default.PostAdd, Color(0xFF059669)),
        CategoryItemData("বাগধারা", "৫০ অধ্যায় (৫৩১-৫৮০)", Icons.Default.AutoAwesome, Color(0xFFDB2777)),
        CategoryItemData("প্রবাদ-প্রবচন", "৫০ অধ্যায় (৫৮১-৬৩০)", Icons.Default.FormatQuote, Color(0xFF0D9488)),
        CategoryItemData("এক কথায় প্রকাশ", "৫০ অধ্যায় (৬৩১-৬৮০)", Icons.Default.Spellcheck, Color(0xFF0284C7)),
        CategoryItemData("সমার্থক শব্দ", "৪০ অধ্যায় (৬৮১-৭২০)", Icons.Default.CompareArrows, Color(0xFF16A34A)),
        CategoryItemData("বিপরীত শব্দ", "৪০ অধ্যায় (৭২১-৭৬০)", Icons.Default.SwapHoriz, Color(0xFF2563EB)),
        CategoryItemData("শুদ্ধ বানান অনুশীলন", "৪০ অধ্যায় (৭৬১-৮০০)", Icons.Default.CheckCircle, Color(0xFF10B981)),
        CategoryItemData("বিরামচিহ্ন", "২০ অধ্যায় (৮০১-৮২০)", Icons.Default.MoreHoriz, Color(0xFFF59E0B)),
        CategoryItemData("অলংকার", "২০ অধ্যায় (৮২১-৮৪০)", Icons.Default.Brush, Color(0xFF8B5CF6)),
        CategoryItemData("ছন্দ", "২০ অধ্যায় (৮৪১-৮৬০)", Icons.Default.MusicNote, Color(0xFFEA580C)),
        CategoryItemData("রচনা ও ভাষা প্রয়োগ", "৩০ অধ্যায় (৮৬১-৮৯০)", Icons.Default.EditNote, Color(0xFF6366F1)),
        CategoryItemData("SSC প্রস্তুতি", "২০ অধ্যায় (৮৯১-৯১০)", Icons.Default.School, Color(0xFF10B981)),
        CategoryItemData("HSC প্রস্তুতি", "২০ অধ্যায় (৯১১-৯৩০)", Icons.Default.WorkspacePremium, Color(0xFF0284C7)),
        CategoryItemData("বিশ্ববিদ্যালয় ভর্তি", "২৫ অধ্যায় (৯৩১-৯৫৫)", Icons.Default.AccountBalance, Color(0xFF7C3AED)),
        CategoryItemData("BCS বাংলা", "২৫ অধ্যায় (৯৫৬-৯৮০)", Icons.Default.Psychology, Color(0xFFE11D48)),
        CategoryItemData("চাকরি প্রস্তুতি", "২০ অধ্যায় (৯৮১-১০০০)", Icons.Default.Work, Color(0xFFD97706))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Daily Streak & Progress Card
        userProgress?.let { progress ->
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate("profile") }
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocalFireDepartment,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "ধারাবাহিক স্ট্রাইক: ${progress.dailyStreak} দিন",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "আজকের পঠিত: ${progress.lessonsReadCount}টি পাঠ • নির্ভুলতা: 88%",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                        )
                    }

                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.secondary
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Lvl ${progress.level}",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondary,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "${progress.xp} XP",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondary,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quick Tools Row (Quiz, Mock Test, Flashcard, Dict, Notes)
        Text(
            text = "দ্রুত টুলস (Quick Tools)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                ToolChip("দৈনিক কুইজ", Icons.Default.Quiz, MaterialTheme.colorScheme.secondary) { onStartQuiz() }
            }
            item {
                ToolChip("মক টেস্ট", Icons.Default.Assignment, Color(0xFF0288D1)) { onNavigate("mock_test") }
            }
            item {
                ToolChip("ফ্ল্যাশকার্ড", Icons.Default.Style, Color(0xFF7E57C2)) { onNavigate("flashcards") }
            }
            item {
                ToolChip("অভিধান", Icons.Default.Translate, Color(0xFF2E7D32)) { onNavigate("dictionary") }
            }
            item {
                ToolChip("নোটস", Icons.AutoMirrored.Filled.Notes, Color(0xFFFF7043)) { onNavigate("notes") }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Daily Lesson & Today's Challenge Banner
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "আজকের চ্যালেঞ্জ",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "সমাস ও সন্ধি থেকে ১০টি কুইজ সমাধান করুন",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = onStartQuiz,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "শুরু করুন")
                }
            }
        }

        // Version 4.0 Vision Banner
        Spacer(modifier = Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(text = "4.0", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "২০,০০০+ উদাহরণের ব্যাংক",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "২৩টি বিশেষ বিভাগ • ৫,০০০+ MCQ • অফলাইন সার্চ",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        Text(
                            text = "২০,০০০+",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "১,০০০+", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                        Text(text = "অধ্যায়", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "২০,০০০+", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                        Text(text = "উদাহরণ", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "৫,০০০+", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                        Text(text = "MCQ", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "১০,০০০+", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
                        Text(text = "শব্দভাণ্ডার", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                    }
                }
            }
        }

        // Daily Example Card (Version 3.0/4.0)
        dailyExample?.let { ex ->
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.5f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate("grammar") }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "আজকের উদাহরণ (Daily Example)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onTertiaryContainer
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.secondary
                        ) {
                            Text(
                                text = ex.category.ifEmpty { "ব্যাকরণ" },
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSecondary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = ex.example,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )

                    if (ex.analysis.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "🔍 ${ex.analysis}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onTertiaryContainer.copy(alpha = 0.9f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Categories Grid Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "ব্যাকরণ অধ্যায়সমূহ (Categories)",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = { onNavigate("grammar") }) {
                Text(text = "সব দেখুন")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Category Cards Grid
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            categories.chunked(2).forEach { pair ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    pair.forEach { cat ->
                        CategoryCard(
                            item = cat,
                            onClick = {
                                onSelectCategory(cat.name)
                                onNavigate("grammar")
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    if (pair.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ToolChip(
    title: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = color.copy(alpha = 0.12f),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun CategoryCard(
    item: CategoryItemData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = item.badgeColor.copy(alpha = 0.15f),
                modifier = Modifier.size(42.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.name,
                        tint = item.badgeColor
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            Text(
                text = item.countText,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}
