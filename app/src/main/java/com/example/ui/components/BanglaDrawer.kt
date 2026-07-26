package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.data.model.UserProgressEntity

data class DrawerItemData(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val badgeText: String? = null
)

@Composable
fun BanglaDrawerContent(
    currentRoute: String,
    userProgress: UserProgressEntity?,
    onNavigate: (String) -> Unit,
    onCloseDrawer: () -> Unit
) {
    val items = listOf(
        DrawerItemData("home", "হোম (Home)", Icons.Default.Home),
        DrawerItemData("grammar", "ব্যাকরণ অধ্যায় (Grammar)", Icons.AutoMirrored.Filled.MenuBook, "১০+"),
        DrawerItemData("dictionary", "অভিধান (Dictionary)", Icons.Default.Translate),
        DrawerItemData("quiz", "কুইজ (Quiz)", Icons.Default.Quiz, "দৈনিক"),
        DrawerItemData("practice", "অনুশীলন (Practice)", Icons.Default.FitnessCenter),
        DrawerItemData("mock_test", "মক টেস্ট (Mock Test)", Icons.Default.Assignment, "SSC/BCS"),
        DrawerItemData("flashcards", "ফ্ল্যাশকার্ড (Flashcards)", Icons.Default.Style),
        DrawerItemData("notes", "আমার নোটস (Notes)", Icons.AutoMirrored.Filled.Notes),
        DrawerItemData("bookmarks", "বুকমার্কস (Bookmarks)", Icons.Default.Bookmark),
        DrawerItemData("achievements", "অর্জন ও ব্যাজ (Badges)", Icons.Default.EmojiEvents),
        DrawerItemData("profile", "পরিসংখ্যান (Progress)", Icons.Default.BarChart),
        DrawerItemData("admin", "অ্যাডমিন প্যানেল (Admin)", Icons.Default.AdminPanelSettings),
        DrawerItemData("settings", "সেটিংস (Settings)", Icons.Default.Settings)
    )

    ModalDrawerSheet(
        modifier = Modifier.width(300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(52.dp),
                            shape = CircleShape,
                            color = MaterialTheme.colorScheme.secondary
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = "বঁ",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = "বাংলা জিনিয়াস",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "সম্পূর্ণ অফলাইন ব্যাকরণ ও প্রস্তুতি",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // User Level & XP Banner
                    userProgress?.let { progress ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color.White.copy(alpha = 0.15f)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.LocalFireDepartment,
                                        contentDescription = "Streak",
                                        tint = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${progress.dailyStreak} দিন স্ট্রাইক",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Text(
                                    text = "লেভেল ${progress.level} (${progress.xp} XP)",
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Navigation Items
            items.forEach { item ->
                val isSelected = currentRoute == item.id
                NavigationDrawerItem(
                    label = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.title,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            item.badgeText?.let { badge ->
                                Surface(
                                    shape = RoundedCornerShape(10.dp),
                                    color = MaterialTheme.colorScheme.primaryContainer
                                ) {
                                    Text(
                                        text = badge,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        onNavigate(item.id)
                        onCloseDrawer()
                    },
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
