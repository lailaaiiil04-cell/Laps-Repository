package edu.iau.cshj.csc402.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.compose.ui.tooling.preview.Preview

private val Navy = Color(0xFF0B2545)
private val Green = Color(0xFF3DDC84)
private val Background = Color(0xFFF4F7FA)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState); setContent { MaterialTheme { Lab2Home() } } }
}
@Composable private fun Header(subtitle: String) {
    Column(Modifier.fillMaxWidth().background(Navy).padding(20.dp)) {
        Text("CSC 402 Lab 2", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Text(subtitle, color = Color.White)
    }
}
@Composable private fun GreenChip(label: String) {
    Text(label, modifier = Modifier.clip(RoundedCornerShape(18.dp)).background(Green).padding(horizontal = 12.dp, vertical = 6.dp), color = Navy)
}
@Composable private fun Avatar(initials: String) {
    Box(Modifier.size(60.dp).clip(CircleShape).background(Navy), contentAlignment = Alignment.Center) { Text(initials, color = Color.White, fontWeight = FontWeight.Bold) }
}
@Composable
fun Lab2Home() {
    var selected by remember { mutableIntStateOf(6) }

    Column(
        modifier = Modifier.fillMaxSize().background(Background)
    ) {
        Text(
            text = "Select a Task",
            modifier = Modifier.padding(start = 16.dp, top = 12.dp, bottom = 4.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        // Two rows of large buttons, so all six tasks are easy to tap.
        (6..11).toList().chunked(3).forEach { tasksInRow ->
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                tasksInRow.forEach { task ->
                    if (selected == task) {
                        Button(
                            onClick = { selected = task },
                            modifier = Modifier.weight(1f).height(52.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("Task $task")
                        }
                    } else {
                        OutlinedButton(
                            onClick = { selected = task },
                            modifier = Modifier.weight(1f).height(52.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("Task $task")
                        }
                    }
                }
            }
        }

        HorizontalDivider()
        Box(modifier = Modifier.fillMaxWidth().weight(1f)) {
            when (selected) {
                6 -> WelcomeScreen()
                7 -> StudentIdScreen()
                8 -> AttendanceCounter()
                9 -> GreetingCard()
                10 -> CourseListScreen()
                11 -> TaskTrackerScreen()
            }
        }
    }
}

@Composable fun WelcomeScreen(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().background(Background)) {
        Header("Task 6 - My First Screen")
        Card(Modifier.fillMaxWidth().padding(20.dp)) {
            Column(Modifier.fillMaxWidth().padding(25.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Avatar("JU"); Spacer(Modifier.height(15.dp))
                Text("Hello, Jubail!", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp)); Text("Welcome to mobile app programming", color = Color.Gray)
                Text("Build your UI with Kotlin", color = Color.Gray)
                Spacer(Modifier.height(18.dp)); GreenChip("Built with Jetpack Compose")
            }
        }
    }
}
@Composable fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) { Text(value, fontWeight = FontWeight.Bold, fontSize = 21.sp); Text(label, color = Color.Gray) }
}
@Composable fun StudentIdScreen() {
    Column(Modifier.fillMaxSize().background(Background)) {
        Header("Task 7 - Student ID Card")
        Card(Modifier.fillMaxWidth().padding(16.dp)) {
            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Avatar("LA"); Spacer(Modifier.width(12.dp))
                Column {
                    Text("Laila", fontWeight = FontWeight.Bold, fontSize = 19.sp)
                    Text("Computer Science"); Text("Student ID: YOUR_ID", fontFamily = FontFamily.Monospace)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { GreenChip("Level 4"); Text("CSHJ") }
                }
            }
        }
        HorizontalDivider()
        Card(Modifier.fillMaxWidth().padding(16.dp)) {
            Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                StatItem("4.42", "GPA"); StatItem("78", "Credits"); StatItem("Active", "Status")
            }
        }
    }
}
@Composable fun AttendanceCounter() {
    var count by remember { mutableIntStateOf(0) }
    Column(Modifier.fillMaxSize().background(Background)) {
        Header("Task 8 - Attendance Counter")
        Column(Modifier.fillMaxWidth().padding(25.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("STUDENTS PRESENT")
            Card(Modifier.padding(20.dp)) { Text("$count", Modifier.padding(30.dp), fontSize = 52.sp, fontWeight = FontWeight.Bold) }
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                Button(onClick = { if (count > 0) count-- }, enabled = count > 0) { Text("−") }
                Button(onClick = { count++ }) { Text("+") }
            }
            Spacer(Modifier.height(10.dp)); Button(onClick = { count = 0 }, enabled = count > 0) { Text("Reset") }
            Text(if (count == 0) "Tap + to check a student in." else "$count of 30 students checked in.")
        }
    }
}
@Composable fun GreetingCard() {
    var name by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize().background(Background)) {
        Header("Task 9 - Greeting Card")
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedTextField(name, { name = it }, label = { Text("Your name") }, singleLine = true, modifier = Modifier.fillMaxWidth())
            Button(onClick = { submitted = name.trim() }, enabled = name.isNotBlank(), modifier = Modifier.fillMaxWidth()) { Text("Show greeting") }
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    if (submitted.isEmpty()) Text("Your greeting will appear here") else {
                        Text("Marhaba, $submitted!", fontWeight = FontWeight.Bold)
                        Text("Your name has ${submitted.length} letters.")
                        GreenChip("CSC 402 - Lab 2")
                    }
                }
            }
        }
    }
}
data class Course(val code: String, val title: String, val credits: Int, val days: String)
private val initialCourses = listOf(
    Course("CSC 402", "Mobile Application Programming", 3, "Mon / Wed"),
    Course("CSC 311", "Database Systems", 4, "Sun / Tue"),
    Course("CSC 340", "Operating Systems", 3, "Mon / Wed"),
    Course("MATH 202", "Discrete Mathematics", 3, "Sun / Thu"),
    Course("ENG 214", "Technical Writing", 2, "Tue"),
    Course("PHYS 105", "General Physics II", 4, "Sun / Tue"),
    Course("ISLM 101", "Islamic Culture", 2, "Thu")
)
@Composable fun CourseRow(course: Course) {
    Card(Modifier.fillMaxWidth()) {
        Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.width(5.dp).height(65.dp).background(Green))
            Column(Modifier.weight(1f).padding(start = 12.dp)) {
                Text(course.code, fontWeight = FontWeight.Bold)
                Text(course.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(course.days, color = Color.Gray)
            }
            Text("${course.credits} cr", color = Navy)
        }
    }
}
@Composable fun CourseListScreen() {
    val courses = remember { initialCourses }
    Column(Modifier.fillMaxSize().background(Background)) {
        Header("Task 10 - Courses")
        LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(courses) { CourseRow(it) }
        }
    }
}
data class TeamTask(val id: Int, val title: String, val owner: String, val isDone: Boolean = false)
@Composable fun TaskRow(task: TeamTask, onToggle: () -> Unit) {
    Card(Modifier.fillMaxWidth()) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(task.isDone, onCheckedChange = { onToggle() })
            Column {
                Text(task.title, color = if (task.isDone) Color.Gray else Color.Black,
                    textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None)
                Text(task.owner, color = Color.Gray)
            }
        }
    }
}
@Composable fun TaskTrackerScreen() {
    val tasks = remember { mutableStateListOf(
        TeamTask(1, "Set up repository", "Laila", true), TeamTask(2, "Create UI design", "Sara", true),
        TeamTask(3, "Build home screen", "Laila"), TeamTask(4, "Test the app", "Noura")
    ) }
    Column(Modifier.fillMaxSize().background(Background)) {
        Header("Task 11 - Team Task Tracker")
        Row(Modifier.fillMaxWidth().background(Navy).padding(16.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
            listOf("Done" to tasks.count { it.isDone }, "Open" to tasks.count { !it.isDone }, "Total" to tasks.size).forEach { (name, count) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) { Text("$count", color = Color.White); Text(name, color = Green) }
            }
        }
        LazyColumn(Modifier.weight(1f), contentPadding = PaddingValues(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tasks, key = { it.id }) { task -> TaskRow(task, onToggle = {
                val index = tasks.indexOfFirst { it.id == task.id }
                if (index >= 0) tasks[index] = tasks[index].copy(isDone = !tasks[index].isDone)
            }) }
        }
        Button(onClick = { tasks.add(TeamTask((tasks.maxOfOrNull { it.id } ?: 0) + 1, "New task", "Laila")) }, Modifier.fillMaxWidth().padding(12.dp)) { Text("Add task") }
    }
}
@Preview(showBackground = true) @Composable private fun WelcomePreview() { MaterialTheme { WelcomeScreen() } }
@Preview(showBackground = true) @Composable private fun StudentPreview() { MaterialTheme { StudentIdScreen() } }
@Preview(showBackground = true) @Composable private fun CounterPreview() { MaterialTheme { AttendanceCounter() } }
@Preview(showBackground = true) @Composable private fun GreetingPreview() { MaterialTheme { GreetingCard() } }
@Preview(showBackground = true) @Composable private fun CoursesPreview() { MaterialTheme { CourseListScreen() } }
@Preview(showBackground = true) @Composable private fun TrackerPreview() { MaterialTheme { TaskTrackerScreen() } }
