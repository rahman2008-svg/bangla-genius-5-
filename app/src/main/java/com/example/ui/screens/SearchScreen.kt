package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.DictionaryEntity
import com.example.data.model.ExampleEntity
import com.example.data.model.LessonEntity

@Composable
fun SearchScreen(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    lessons: List<LessonEntity>,
    words: List<DictionaryEntity>,
    examples: List<ExampleEntity> = emptyList(),
    onSelectLesson: (Int) -> Unit,
    onToggleLessonBookmark: (Int, Boolean) -> Unit,
    onToggleExampleBookmark: (Int, Boolean) -> Unit = { _, _ -> }
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Search Input Field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = { Text("ব্যাকরণ অধ্যায়, সমাস, সন্ধি বা অভিধানের শব্দ খুঁজুন...") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
            trailingIcon = {
                Row {
                    IconButton(onClick = { /* Voice Search Simulation */ }) {
                        Icon(imageVector = Icons.Default.Mic, contentDescription = "Voice Search", tint = MaterialTheme.colorScheme.primary)
                    }
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchQueryChange("") }) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (searchQuery.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "যেকোনো শব্দ বা ব্যাকরণ টপিক লিখে সার্চ করুন",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (lessons.isNotEmpty()) {
                    item {
                        Text(
                            text = "ব্যাকরণ অধ্যায়সমূহ (${lessons.size}টি মিল পাওয়া গেছে)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    items(lessons) { lesson ->
                        LessonCardItem(
                            lesson = lesson,
                            onClick = { onSelectLesson(lesson.id) },
                            onBookmarkToggle = { onToggleLessonBookmark(lesson.id, lesson.isBookmarked) }
                        )
                    }
                }

                if (examples.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "ব্যাকরণিক উদাহরণসমূহ (${examples.size}টি মিল পাওয়া গেছে)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    items(examples) { ex ->
                        ExampleCardItem(
                            exampleItem = ex,
                            onToggleBookmark = { onToggleExampleBookmark(ex.id, ex.isBookmarked) }
                        )
                    }
                }

                if (words.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "অভিধান শব্দাবলী (${words.size}টি মিল পাওয়া গেছে)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    items(words) { word ->
                        DictionaryWordCard(
                            word = word,
                            selectedMode = 0,
                            onToggleBookmark = {},
                            onSpeak = {}
                        )
                    }
                }

                if (lessons.isEmpty() && words.isEmpty() && examples.isEmpty()) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "'$searchQuery' দিয়ে কোনো ফল পাওয়া যায়নি",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }
}
