package com.example.exercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.platform.LocalUriHandler

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CVScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CVScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Profile", "Experience", "Skills", "Projects")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        // Top App Bar
        TopAppBar(
            title = { Text("My CV", style = MaterialTheme.typography.headlineMedium) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        // Tab Row
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    text = { Text(title) }
                )
            }
        }

        // Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                AnimatedVisibility(
                    visible = selectedTab == 0,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    ProfileSection()
                }
            }
            
            item {
                AnimatedVisibility(
                    visible = selectedTab == 1,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    ExperienceSection()
                }
            }

            item {
                AnimatedVisibility(
                    visible = selectedTab == 2,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    SkillsSection()
                }
            }

            item {
                AnimatedVisibility(
                    visible = selectedTab == 3,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    ProjectsSection()
                }
            }
        }
    }
}

@Composable
fun ProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(160.dp)
                .shadow(12.dp, CircleShape)
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .border(4.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "Le Nguyen Gia Hung",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary
        )
        
        Text(
            text = "Fourth-year student at UIT, VNU-HCM",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
        
        ContactInfo(
            icon = Icons.Default.Email,
            text = "lngiahung1232003@gmail.com"
        )
        ContactInfo(
            icon = Icons.Default.Phone,
            text = "+84 912 502 280"
        )
        ContactInfo(
            icon = Icons.Default.LocationOn,
            text = "Ho Chi Minh City, Vietnam"
        )
    }
}

@Composable
fun ContactInfo(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun ExperienceSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        SectionHeader(title = "Education", icon = Icons.Default.AccountBox)
        ExperienceItem(
            title = "UIT, VNU-HCM",
            subtitle = "Information System",
            period = "2021 - 2025",
            description = "Major in Information Systems with focus on software development and database management."
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        SectionHeader(title = "Work Experience", icon = Icons.Default.Person)
        ExperienceItem(
            title = "AppCyclone",
            subtitle = "Intern Web Developer",
            period = "2024 - 2025",
            description = "Developed and maintained web applications using modern technologies."
        )
        ExperienceItem(
            title = "Ephata Education",
            subtitle = "Teaching Assistant",
            period = "2021 - Present",
            description = "Assisted in teaching programming fundamentals."
        )
    }
}

@Composable
fun ExperienceItem(
    title: String,
    subtitle: String,
    period: String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = subtitle,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = period,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = description,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun SkillsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        SectionHeader(title = "Technical Skills", icon = Icons.Default.Settings)
        SkillCategory("Programming Languages", listOf("Kotlin", "Java", "Python", "JavaScript"))
        SkillCategory("Frameworks", listOf("Android", "Spring Boot", "React", "Node.js"))
        SkillCategory("Tools & Technologies", listOf("Git", "Docker", "AWS", "Firebase"))
    }
}

@Composable
fun SkillCategory(category: String, skills: List<String>) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = category,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        FlowRow(
            modifier = Modifier.padding(top = 8.dp),
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 8.dp
        ) {
            skills.forEach { skill ->
                Chip(skill)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chip(text: String) {
    Surface(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        shadowElevation = 0.dp
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun ProjectsSection() {
    val uriHandler = LocalUriHandler.current
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        SectionHeader(title = "Projects", icon = Icons.Default.Build)
        
        // GitHub Link
        val annotatedString = buildAnnotatedString {
            pushStyle(
                SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp
                )
            )
            append("GitHub Profile: @LNGiaHung")
            pop()
        }
        
        ClickableText(
            text = annotatedString,
            onClick = { uriHandler.openUri("https://github.com/LNGiaHung") },
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        ProjectItem(
            title = "CV Mobile App",
            description = "A modern CV application built with Jetpack Compose",
            technologies = listOf("Kotlin", "Jetpack Compose", "Material Design 3")
        )
        
        ProjectItem(
            title = "Movie Platform",
            description = "Web-based Movie Trailer Streamming",
            technologies = listOf("React", "Node.js", "MongoDB")
        )
    }
}

@Composable
fun ProjectItem(
    title: String,
    description: String,
    technologies: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = description,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        FlowRow(
            modifier = Modifier.padding(top = 4.dp),
            mainAxisSpacing = 4.dp,
            crossAxisSpacing = 4.dp
        ) {
            technologies.forEach { tech ->
                Chip(tech)
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, icon: ImageVector) {
    Row(
        modifier = Modifier.padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun FlowRow(
    modifier: Modifier = Modifier,
    mainAxisSpacing: Dp = 0.dp,
    crossAxisSpacing: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val sequences = mutableListOf<List<androidx.compose.ui.layout.Placeable>>()
        val crossAxisSizes = mutableListOf<Int>()
        val crossAxisPositions = mutableListOf<Int>()

        var crossAxisSpace = 0
        val currentSequence = mutableListOf<androidx.compose.ui.layout.Placeable>()
        var currentMainAxisSize = 0
        var currentCrossAxisSize = 0

        val maxWidth = constraints.maxWidth

        for (measurable in measurables) {
            val placeable = measurable.measure(constraints)

            if (currentMainAxisSize > 0 && currentMainAxisSize + mainAxisSpacing.roundToPx() + placeable.width > maxWidth) {
                sequences += currentSequence.toList()
                crossAxisSizes += currentCrossAxisSize
                crossAxisPositions += crossAxisSpace
                crossAxisSpace += currentCrossAxisSize + crossAxisSpacing.roundToPx()
                currentSequence.clear()
                currentMainAxisSize = 0
                currentCrossAxisSize = 0
            }

            currentSequence.add(placeable)
            currentMainAxisSize += placeable.width
            if (currentSequence.size > 1) {
                currentMainAxisSize += mainAxisSpacing.roundToPx()
            }
            currentCrossAxisSize = maxOf(currentCrossAxisSize, placeable.height)
        }

        if (currentSequence.isNotEmpty()) {
            sequences += currentSequence
            crossAxisSizes += currentCrossAxisSize
            crossAxisPositions += crossAxisSpace
            crossAxisSpace += currentCrossAxisSize
        }

        layout(
            width = maxWidth,
            height = if (crossAxisSpace > 0) crossAxisSpace else 0
        ) {
            sequences.forEachIndexed { i, sequence ->
                var currentMainAxisPosition = 0
                sequence.forEach { placeable ->
                    placeable.place(
                        x = currentMainAxisPosition,
                        y = crossAxisPositions[i]
                    )
                    currentMainAxisPosition += placeable.width + mainAxisSpacing.roundToPx()
                }
            }
        }
    }
}