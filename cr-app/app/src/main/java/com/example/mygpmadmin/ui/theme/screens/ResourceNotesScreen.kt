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
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

private val Ink = Color(0xFF1A2340)
private val Surface = Color(0xFFF9F6F0)
private val Blue = Color(0xFF81A6C6)
private val PaleBlue = Color(0xFFE5F0F7)
private val Sand = Color(0xFFF3E3D0)
private val Taupe = Color(0xFFD2C4B4)
private val SoftGreen = Color(0xFFE4F0E8)
private val SoftRed = Color(0xFFFFEDED)
private val ErrorRed = Color(0xFF9B3D3D)

private data class ResourceItem(
    val id: Int,
    var title: String,
    var subject: String,
    var year: String,
    var type: String,
    val uploadedBy: String,
    val date: String
)

@Composable
fun ResourceNotesScreen(
    navController: NavController,
    role: String
) {
    val isTeacher = role.equals("teacher", ignoreCase = true)
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val resources = remember {
        mutableStateListOf(
            ResourceItem(1, "DBMS Unit 1 Notes", "DBMS", "Second Year", "PDF", "Teacher", "Today"),
            ResourceItem(2, "Computer Networks PPT", "Computer Networks", "Second Year", "PPT", "Teacher", "Yesterday"),
            ResourceItem(3, "Python Practical Paper", "Python", "Second Year", "Previous Paper", "CR", "2 days ago"),
            ResourceItem(4, "Operating System Diagram", "Operating System", "Second Year", "Image", "Teacher", "4 days ago")
        )
    }

    var searchText by remember { mutableStateOf("") }
    var subjectFilter by remember { mutableStateOf("All Subjects") }
    var yearFilter by remember { mutableStateOf("All Years") }
    var typeFilter by remember { mutableStateOf("All Types") }
    var selectedResource by remember { mutableStateOf<ResourceItem?>(null) }
    var infoMessage by remember { mutableStateOf<String?>(null) }

    val filteredResources = resources.filter { item ->
        val matchesSearch = item.title.contains(searchText, true) ||
                item.subject.contains(searchText, true)
        val matchesSubject = subjectFilter == "All Subjects" || item.subject == subjectFilter
        val matchesYear = yearFilter == "All Years" || item.year == yearFilter
        val matchesType = typeFilter == "All Types" || item.type == typeFilter
        matchesSearch && matchesSubject && matchesYear && matchesType
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = Surface,
                modifier = Modifier.width(310.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(22.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.size(48.dp).clip(CircleShape).background(Ink),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Folder, null, tint = Sand)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("MYGPM", color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text("ADMIN", color = Blue, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = { scope.launch { drawerState.close() } }) {
                        Icon(Icons.Default.Close, "Close menu", tint = Ink)
                    }
                }

                Divider(color = Taupe)
                Spacer(Modifier.height(10.dp))

                ResourceDrawerItem("Home", Icons.Default.Home) {
                    navController.navigate(if (isTeacher) "teacher_dashboard" else "cr_dashboard")
                }
                ResourceDrawerItem("Attendance", Icons.Default.Assignment) {
                    navController.navigate("attendance")
                }
                ResourceDrawerItem("Students", Icons.Default.People) {
                    navController.navigate("students")
                }
                ResourceDrawerItem("Notices", Icons.Default.NotificationsNone) {
                    navController.navigate("notices")
                }
                ResourceDrawerItem("Assignments", Icons.Default.Assignment) {
                    navController.navigate("assignments")
                }
                ResourceDrawerItem("Resources / Notes", Icons.Default.Folder) {
                    scope.launch { drawerState.close() }
                }
                ResourceDrawerItem("Timetable", Icons.Default.Assignment) {
                    navController.navigate("timetable")
                }
                ResourceDrawerItem("MIS Portal", Icons.Default.Visibility) {
                    navController.navigate("mis_portal")
                }
                ResourceDrawerItem("Notifications", Icons.Default.NotificationsNone) {
                    navController.navigate("notifications")
                }
                Spacer(Modifier.weight(1f))
                Divider(color = Taupe)
                ResourceDrawerItem("Logout", Icons.Default.Close) {
                    navController.navigate("login")
                }
                Spacer(Modifier.height(12.dp))
            }
        }
    ) {
        Scaffold(
            containerColor = Surface,
            bottomBar = {
                ResourceBottomBar(
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
                        Icon(Icons.Default.Menu, "Open menu", tint = Ink)
                    }
                    Column(Modifier.weight(1f)) {
                        Text("Resources / Notes", color = Ink, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text("Keep every academic file in one place.", color = Ink.copy(alpha = 0.65f), fontSize = 12.sp)
                    }
                    Icon(Icons.Default.Folder, contentDescription = null, tint = Blue, modifier = Modifier.size(30.dp))
                }

                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                ) {
                    OutlinedTextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        leadingIcon = { Icon(Icons.Default.Search, null, tint = Ink) },
                        placeholder = { Text("Search notes, papers or files") },
                        shape = RoundedCornerShape(16.dp)
                    )

                    Spacer(Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterDropdown(
                            label = subjectFilter,
                            options = listOf("All Subjects", "DBMS", "Computer Networks", "Python", "Operating System"),
                            onSelected = { subjectFilter = it },
                            modifier = Modifier.weight(1f)
                        )
                        FilterDropdown(
                            label = yearFilter,
                            options = listOf("All Years", "First Year", "Second Year", "Third Year"),
                            onSelected = { yearFilter = it },
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    FilterDropdown(
                        label = typeFilter,
                        options = listOf("All Types", "PDF", "PPT", "Image", "Previous Paper", "Document"),
                        onSelected = { typeFilter = it },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Recent uploads", color = Ink, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.weight(1f))
                        Text("${filteredResources.size} files", color = Blue, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }

                    Spacer(Modifier.height(8.dp))
                }

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(filteredResources, key = { it.id }) { item ->
                        ResourceCard(
                            item = item,
                            onOpen = { selectedResource = item }
                        )
                    }
                }


            }
        }
    }

    selectedResource?.let { item ->
        if (true) {
            AlertDialog(
                onDismissRequest = { selectedResource = null },
                title = { Text(item.title, color = Ink, fontWeight = FontWeight.Bold) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("Subject: ${item.subject}")
                        Text("Year: ${item.year}")
                        Text("Type: ${item.type}")
                        Text("Uploaded by: ${item.uploadedBy}")
                        Text("Uploaded: ${item.date}")
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        selectedResource = null
                        infoMessage = "Open/download selected. Real file URL will come from Supabase Storage."
                    }) {
                        Text("Open / Download", color = Ink)
                    }
                },
                dismissButton = {
                    TextButton(onClick = { selectedResource = null }) {
                        Text("Close", color = Ink)
                    }
                }
            )
        }
    }

    infoMessage?.let { message ->
        AlertDialog(
            onDismissRequest = { infoMessage = null },
            title = { Text("Mock mode", color = Ink, fontWeight = FontWeight.Bold) },
            text = { Text(message) },
            confirmButton = {
                TextButton(onClick = { infoMessage = null }) {
                    Text("Okay", color = Ink)
                }
            }
        )
    }
}

@Composable
private fun ResourceCard(
    item: ResourceItem,
    onOpen: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { onOpen() }
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(54.dp).clip(RoundedCornerShape(16.dp)).background(PaleBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (item.type == "Image") Icons.Default.Image else Icons.Default.Description,
                contentDescription = null,
                tint = Ink,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(Modifier.weight(1f)) {
            Text(item.title, color = Ink, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("${item.subject} • ${item.year}", color = Ink.copy(alpha = 0.65f), fontSize = 12.sp)
            Text("${item.type} • ${item.date}", color = Blue, fontSize = 11.sp)
        }

        Icon(Icons.Default.Visibility, contentDescription = "Open resource", tint = Ink)
    }
}

@Composable
private fun FilterDropdown(
    label: String,
    options: List<String>,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = label,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth().clickable { expanded = true },
            singleLine = true,
            shape = RoundedCornerShape(14.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Sand)
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, color = Color.Black) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun ResourceDrawerItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(horizontal = 22.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Ink, modifier = Modifier.size(21.dp))
        Spacer(Modifier.width(14.dp))
        Text(label, color = Ink, fontSize = 14.sp)
    }
}

@Composable
private fun ResourceBottomBar(
    navController: NavController,
    isTeacher: Boolean,
    onMoreClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 14.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color.White)
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ResourceBottomItem("Home", Icons.Default.Home) {
            navController.navigate(if (isTeacher) "teacher_dashboard" else "cr_dashboard")
        }
        ResourceBottomItem("Attendance", Icons.Default.Assignment) {
            navController.navigate("attendance")
        }
        ResourceBottomItem("Notes", Icons.Default.Folder) {}
        ResourceBottomItem("Notices", Icons.Default.NotificationsNone) {
            navController.navigate("notices")
        }
        ResourceBottomItem("More", Icons.Default.MoreHoriz, onMoreClick)
    }
}

@Composable
private fun ResourceBottomItem(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clip(RoundedCornerShape(16.dp)).clickable { onClick() }.padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = label, tint = Ink, modifier = Modifier.size(23.dp))
        Text(label, color = Ink, fontSize = 10.sp)
    }
}
