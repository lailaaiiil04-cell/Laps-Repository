package edu.iau.cshj.csc402.lab2

fun task1() {
    val university = "Imam Abdulrahman Bin Faisal University"
    val courseCode = "CSC 402"
    val creditHours = 3
    var studentsEnrolled = 28
    val isElective = false
    println("$courseCode has $studentsEnrolled students enrolled ($creditHours credit hours).")
    studentsEnrolled += 2
    println("$courseCode has $studentsEnrolled students enrolled ($creditHours credit hours).")
    println("University name length: ${university.length}")
    println("Elective? $isElective")
    // courseCode = "CSC 403" // Val cannot be reassigned
}
fun greetStudent(name: String, course: String = "CSC 402") = "Welcome to $course, $name!"
fun finalMark(quizzes: Double, project: Double, finalExam: Double): Double = quizzes * .10 + project * .70 + finalExam * .20
fun printBanner(title: String, width: Int = 40) { println("-".repeat(width)); println(title); println("-".repeat(width)) }
fun task2() {
    printBanner("CSC 402 Lab 2")
    println(greetStudent("Sara")); println(greetStudent("Sara", "CSC 311"))
    println(finalMark(90.0, 85.0, 80.0))
    println(finalMark(finalExam = 80.0, quizzes = 90.0, project = 85.0))
}
fun letterGrade(mark: Int) = when (mark) {
    !in 0..100 -> "Invalid"
    in 95..100 -> "A+"; in 90..94 -> "A"; in 85..89 -> "B+"
    in 80..84 -> "B"; in 75..79 -> "C+"; in 70..74 -> "C"
    in 65..69 -> "D+"; in 60..64 -> "D"; else -> "F"
}
fun status(mark: Int) = if (mark >= 60 && mark <= 100) "Pass" else "Fail"
fun task3() { listOf(97, 88, 74, 61, 45, 130).forEach { println("Mark $it -> ${letterGrade(it)} (${status(it)})") } }
fun describeTeam(teamName: String?): String = teamName?.let { "Team: $it (${it.length} characters)" } ?: "Team not registered yet"
fun task4() {
    val nickname: String? = null
    val fullName: String = "Abdullah Al-Qahtani"
    println(fullName.length); println(nickname?.length) // null because nickname is null
    println(nickname ?: "no nickname set")
    println(describeTeam("Team Falcon")); println(describeTeam(null))
    // println(nickname!!.length) // NullPointerException, only enable for experiment
}
val courses = listOf(
    Course("CSC 402", "Mobile Application Programming", 3, "Mon / Wed"),
    Course("CSC 311", "Database Systems", 4, "Sun / Tue"),
    Course("CSC 340", "Operating Systems", 3, "Mon / Wed"),
    Course("MATH 202", "Discrete Mathematics", 3, "Sun / Thu"),
    Course("ENG 214", "Technical Writing", 2, "Tue")
)
fun findCourse(list: List<Course>, code: String) = list.firstOrNull { it.code == code }?.title ?: "Course not found"
fun task5() {
    courses.forEach(::println)
    println(courses.filter { it.credits >= 3 }); println(courses.map { it.code })
    println(courses.sumOf { it.credits }); println(findCourse(courses, "CSC 402"))
    println(findCourse(courses, "CSC 999"))
    println(courses.first().copy(credits = 4)); println(courses.first())
}
fun main() { task1(); task2(); task3(); task4(); task5() }
