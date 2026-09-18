package com.example.mygpmadmin.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

private val Ink = Color(0xFF243746)
private val SurfaceBlue = Color(0xFFF4F8FC)
private val Blue = Color(0xFF81A6C6)
private val PaleBlue = Color(0xFFEAF2F8)
private val Sand = Color(0xFFF3E3D0)
private val Green = Color(0xFFE4F3E8)
private val GreenText = Color(0xFF34734A)

private data class StudentRecord(
    val name: String,
    val enrollment: String,
    val year: String,
    val division: String,
    val shift: String,
    val attendance: Int
)

@Composable
fun ManageStudentsScreen(
    navController: NavController,
    role: String
) {
    val students = remember {
        listOf(
            StudentRecord(
                "Siya Malhotra",
                "SM24IF001",
                "Third Year",
                "Division B",
                "Second Shift",
                92
            ),
            StudentRecord(
                "Reeva Jaiswal",
                "SM24IF002",
                "Third Year",
                "Division A",
                "Second Shift",
                86
            ),
            StudentRecord(
                "Akash Jaiswal",
                "SM24IF003",
                "Third Year",
                "Division B",
                "Second Shift",
                74
            ),
            StudentRecord(
                "Reyansh Rathod",
                "SM24IF004",
                "Third Year",
                "Division B",
                "Second Shift",
                96
            )
        )
    }

    var searchText by remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf("All Years") }
    var yearMenuExpanded by remember { mutableStateOf(false) }
    var selectedStudent by remember { mutableStateOf<StudentRecord?>(null) }

    val filteredStudents = students.filter { student ->
        val matchesSearch =
            student.name.contains(searchText, ignoreCase = true) ||
                    student.enrollment.contains(searchText, ignoreCase = true)

        val matchesYear =
            selectedYear == "All Years" || student.year == selectedYear

        matchesSearch && matchesYear
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SurfaceBlue
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 18.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Ink
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Manage Students",
                        color = Ink,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "View students and attendance details",
                        color = Ink.copy(alpha = 0.65f),
                        fontSize = 13.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .background(PaleBlue, RoundedCornerShape(14.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Students",
                        tint = Ink
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = if (role.lowercase() == "teacher") {
                            "Teacher access"
                        } else {
                            "CR view access"
                        },
                        color = Ink,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = if (role.lowercase() == "teacher") {
                            "You can view student information within your assigned academic scope."
                        } else {
                            "You can view student information. Editing permissions depend on backend access."
                        },
                        color = Ink.copy(alpha = 0.65f),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Ink.copy(alpha = 0.7f)
                    )
                },
                placeholder = {
                    Text("Search name or enrollment number")
                },
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Box {
                OutlinedTextField(
                    value = selectedYear,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { yearMenuExpanded = true },
                    readOnly = true,
                    label = { Text("Filter by year") },
                    shape = RoundedCornerShape(16.dp)
                )

                DropdownMenu(
                    expanded = yearMenuExpanded,
                    onDismissRequest = { yearMenuExpanded = false },
                    modifier = Modifier.background(Sand)
                ) {
                    listOf(
                        "All Years",
                        "First Year",
                        "Second Year",
                        "Third Year"
                    ).forEach { year ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = year,
                                    color = Color.Black
                                )
                            },
                            onClick = {
                                selectedYear = year
                                yearMenuExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${filteredStudents.size} students found",
                color = Ink.copy(alpha = 0.65f),
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (filteredStudents.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No students found.",
                        color = Ink.copy(alpha = 0.65f)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filteredStudents) { student ->
                        StudentCard(
                            student = student,
                            onClick = { selectedStudent = student }
                        )
                    }
                }
            }
        }
    }

    selectedStudent?.let { student ->
        StudentDetailsDialog(
            student = student,
            onDismiss = { selectedStudent = null }
        )
    }
}

@Composable
private fun StudentCard(
    student: StudentRecord,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(PaleBlue, RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = student.name
                        .split(" ")
                        .take(2)
                        .mapNotNull { it.firstOrNull()?.toString() }
                        .joinToString(""),
                    color = Ink,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = student.name,
                    color = Ink,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp
                )

                Text(
                    text = student.enrollment,
                    color = Ink.copy(alpha = 0.65f),
                    fontSize = 12.sp
                )

                Text(
                    text = "${student.year} • ${student.division}",
                    color = Ink.copy(alpha = 0.65f),
                    fontSize = 12.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Box(
                    modifier = Modifier
                        .background(Green, RoundedCornerShape(10.dp))
                        .padding(horizontal = 9.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "${student.attendance}%",
                        color = GreenText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "View details",
                    tint = Ink.copy(alpha = 0.55f),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun StudentDetailsDialog(
    student: StudentRecord,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Student Details",
                color = Ink,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DetailRow("Full Name", student.name)
                DetailRow("Enrollment", student.enrollment)
                DetailRow("Year", student.year)
                DetailRow("Division", student.division)
                DetailRow("Shift", student.shift)
                DetailRow("Attendance", "${student.attendance}%")

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "This is mock data for UI testing. Connect it to the backend student and attendance queries before release.",
                    color = Ink.copy(alpha = 0.65f),
                    fontSize = 11.sp
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = Blue)
            ) {
                Text(
                    text = "Close",
                    color = Ink
                )
            }
        },
        containerColor = Color.White
    )
}

@Composable
private fun DetailRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = Ink.copy(alpha = 0.65f),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = value,
            color = Ink,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
