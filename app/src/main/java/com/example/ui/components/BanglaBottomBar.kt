package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)

@Composable
fun BanglaBottomBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem("home", "হোম", Icons.Default.Home),
        BottomNavItem("search", "সন্ধান", Icons.Default.Search),
        BottomNavItem("quiz", "কুইজ", Icons.Default.Quiz),
        BottomNavItem("dictionary", "অভিধান", Icons.Default.Translate),
        BottomNavItem("profile", "প্রোফাইল", Icons.Default.Person)
    )

    NavigationBar(
        modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = MaterialTheme.appTonalElevation
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route ||
                (item.route == "home" && (currentRoute == "grammar" || currentRoute == "lesson_detail"))
            NavigationBarItem(
                selected = isSelected,
                onClick = { onNavigate(item.route) },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

val MaterialTheme.appTonalElevation: androidx.compose.ui.unit.Dp
    get() = androidx.compose.ui.unit.Dp(6f)
