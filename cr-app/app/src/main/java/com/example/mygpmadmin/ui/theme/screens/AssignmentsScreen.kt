package com.example.mygpmadmin.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

private val AssignmentInk = Color(0xFF1A2340)
private val AssignmentSurface = Color(0xFFF9F6F0)
private val AssignmentBlue = Color(0xFF81A6C6)
private val AssignmentSand = Color(0xFFF3E3D0)
private val AssignmentPaleBlue = Color(0xFFE5F0F7)

private data class AssignmentItem(
    val title: String,
    val subject: String,
    val year: String,
    val dueDate: String,
    val status: String,
    val uploadedFile: String
)

@Composable
fun AssignmentsScreen(
    navController: NavController,
    role: String
) {
    val isTeacher = role.equals("teacher", ignoreCase = true)
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val assignments = remember {
        listOf(
            AssignmentItem("DBMS Assignment 2", "DBMS", "Second Year", "25 May 2025", "Published", "PDF"),
            AssignmentItem("Python Practical File", "Python", "Second Year", "30 May 2025", "Draft", "Images"),
            AssignmentItem("Computer Networks Experiment 3", "Computer Networks", "Second Year", "02 June 2025", "Published", "Document")
        )
    }

    var searchText by remember { mutableStateOf("") }
    var selectedAssignment by remember { mutableStateOf<AssignmentItem?>(null) }
    var showUploadDialog by remember { mutableStateOf(false) }
    var infoMessage by remember { mutableStateOf<String?>(null) }

    val filteredAssignments = assignments.filter {
        it.title.contains(searchText, true) || it.subject.contains(searchText, true)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = AssignmentSurface,
                modifier = Modifier.width(310.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(22.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(48.dp).clip(CircleShape).background(AssignmentInk),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Assignment, null, tint = AssignmentSand)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("MYGPM", color = AssignmentInk, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text("ASSIGNMENTS", color = AssignmentBlue, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = { scope.launch { drawerState.close() } }) {
                        Icon(Icons.Default.Close, "Close menu", tint = AssignmentInk)
                    }
                }

                Divider(color = AssignmentSand)
                DrawerAssignmentItem("Home", Icons.Default.Home) {
                    navController.navigate(if (isTeacher) "teacher_dashboard" else "cr_dashboard")
                }
                DrawerAssignmentItem("Attendance", Icons.Default.Assignment) {
                    navController.navigate("attendance")
                }
                DrawerAssignmentItem("Students", Icons.Default.People) {
                    navController.navigate("students")
                }
                DrawerAssignmentItem("Notices", Icons.Default.NotificationsNone) {
                    navController.navigate("notices")
                }
                DrawerAssignmentItem("Assignments", Icons.Default.Assignment) {
                    scope.launch { drawerState.close() }
                }
                DrawerAssignmentItem("Resources / Notes", Icons.Default.Folder) {
                    navController.navigate("resources")
                }
                DrawerAssignmentItem("Timetable", Icons.Default.Assignment) {
                    navController.navigate("timetable")
                }
                DrawerAssignmentItem("Notifications", Icons.Default.NotificationsNone) {
                    navController.navigate("notifications")
                }
                Spacer(Modifier.weight(1f))
                Divider(color = AssignmentSand)
                DrawerAssignmentItem("Logout", Icons.Default.Close) {
                    navController.navigate("login")
                }
                Spacer(Modifier.height(12.dp))
            }
        }
    ) {
        Scaffold(
            containerColor = AssignmentSurface,
            bottomBar = {
                AssignmentBottomBar(
                    navController = navController,
                    isTeacher = isTeacher,
                    onMoreClick = { scope.launch { drawerState.open() } }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                        Icon(Icons.Default.Menu, "Open menu", tint = AssignmentInk)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Assignments",
                            color = Color.White, // Changed to solid white
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Create, upload and manage class work.",
                            color = Color.White.copy(alpha = 0.65f), // Changed to white with 65% opacity
                            fontSize = 12.sp
                        )
                    }

                    Icon(Icons.Default.Assignment, null, tint = AssignmentBlue, modifier = Modifier.size(30.dp))
                }

                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    singleLine = true,
                    leadingIcon = { Icon(Icons.Default.Search, null, tint = AssignmentInk) },
                    placeholder = { Text("Search assignments") },
                    shape = RoundedCornerShape(16.dp)
                )

                Text(
                    "Recent assignments",
                    color = AssignmentInk,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filteredAssignments) { item ->
                        AssignmentCard(
                            item = item,
                            onClick = { selectedAssignment = item }
                        )
                    }
                }

                Button(
                    onClick = { showUploadDialog = true },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AssignmentInk)
                ) {
                    Icon(Icons.Default.UploadFile, null)
                    Spacer(Modifier.width(8.dp))
                    Text(if (isTeacher) "Create / Upload Assignment" else "Upload Assignment Work")
                }
            }
        }
    }

    selectedAssignment?.let { item ->
        AlertDialog(
            onDismissRequest = { selectedAssignment = null },
            title = { Text(item.title, color = AssignmentInk, fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Subject: ${item.subject}")
                    Text("Year: ${item.year}")
                    Text("Due date: ${item.dueDate}")
                    Text("Status: ${item.status}")
                    Text("File type: ${item.uploadedFile}")
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    selectedAssignment = null
                    infoMessage = "Open/download is a placeholder. Real file URLs will come from Supabase Storage."
                }) {
                    Text("Open / Download", color = AssignmentInk)
                }
            },
            dismissButton = {
                TextButton(onClick = { selectedAssignment = null }) {
                    Text("Close", color = AssignmentInk)
                }
            }
        )
    }

    if (showUploadDialog) {
        AssignmentUploadDialog(
            isTeacher = isTeacher,
            onDismiss = { showUploadDialog = false },
            onAction = { action ->
                showUploadDialog = false
                infoMessage = "$action selected. Android file picker, camera permissions, PDF processing and Supabase Storage integration will be connected later."
            }
        )
    }

    infoMessage?.let { message ->
        AlertDialog(
            onDismissRequest = { infoMessage = null },
            title = { Text("Mock mode", color = AssignmentInk, fontWeight = FontWeight.Bold) },
            text = { Text(message) },
            confirmButton = {
                TextButton(onClick = { infoMessage = null }) {
                    Text("Okay", color = AssignmentInk)
                }
            }
        )
    }
}

@Composable
private fun AssignmentCard(
    item: AssignmentItem,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(54.dp).clip(RoundedCornerShape(16.dp)).background(AssignmentPaleBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Description, null, tint = AssignmentInk, modifier = Modifier.size(28.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(item.title, color = AssignmentInk, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("${item.subject} • ${item.year}", color = AssignmentInk.copy(alpha = 0.65f), fontSize = 12.sp)
            Text("Due ${item.dueDate} • ${item.status}", color = AssignmentBlue, fontSize = 11.sp)
        }
        Icon(Icons.Default.Visibility, null, tint = AssignmentInk)
    }
}

@Composable
private fun AssignmentUploadDialog(
    isTeacher: Boolean,
    onDismiss: () -> Unit,
    onAction: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (isTeacher) "Create assignment" else "Upload assignment work", color = AssignmentInk, fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("This is currently a UI mock. Permission checks and file storage will be connected to the approved backend contract.")
                Button(
                    onClick = { onAction("PDF / document upload") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AssignmentInk)
                ) {
                    Icon(Icons.Default.UploadFile, null)
                    Spacer(Modifier.width(8.dp))
                    Text("Upload PDF / Document")
                }
                Button(
                    onClick = { onAction("Gallery image upload") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AssignmentBlue)
                ) {
                    Icon(Icons.Default.Image, null)
                    Spacer(Modifier.width(8.dp))
                    Text("Choose from Gallery")
                }
                Button(
                    onClick = { onAction("Smart camera scan") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AssignmentInk)
                ) {
                    Icon(Icons.Default.CameraAlt, null)
                    Spacer(Modifier.width(8.dp))
                    Text("Smart Scan with Camera")
                }
                Button(
                    onClick = { onAction("Multi-page PDF scan") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AssignmentBlue)
                ) {
                    Icon(Icons.Default.Description, null)
                    Spacer(Modifier.width(8.dp))
                    Text("Scan Multiple Pages to PDF")
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = AssignmentInk)
            }
        }
    )
}

@Composable
private fun DrawerAssignmentItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(horizontal = 22.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = AssignmentInk, modifier = Modifier.size(21.dp))
        Spacer(Modifier.width(14.dp))
        Text(label, color = AssignmentInk, fontSize = 14.sp)
    }
}

@Composable
private fun AssignmentBottomBar(
    navController: NavController,
    isTeacher: Boolean,
    onMoreClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().navigationBarsPadding().padding(horizontal = 14.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(28.dp)).background(Color.White).padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AssignmentBottomItem("Home", Icons.Default.Home) {
            navController.navigate(if (isTeacher) "teacher_dashboard" else "cr_dashboard")
        }
        AssignmentBottomItem("Attendance", Icons.Default.Assignment) {
            navController.navigate("attendance")
        }
        AssignmentBottomItem("Notes", Icons.Default.Folder) {
            navController.navigate("resources")
        }
        AssignmentBottomItem("Notices", Icons.Default.NotificationsNone) {
            navController.navigate("notices")
        }
        AssignmentBottomItem("More", Icons.Default.MoreHoriz, onMoreClick)
    }
}

@Composable
private fun AssignmentBottomItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clip(RoundedCornerShape(16.dp)).clickable { onClick() }.padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, label, tint = AssignmentInk, modifier = Modifier.size(23.dp))
        Text(label, color = AssignmentInk, fontSize = 10.sp)
    }
}
