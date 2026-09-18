package com.example.mygpmadmin.ui.theme.screens

import android.view.WindowManager
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

private val Ink = Color(0xFF1A2340)
private val Surface = Color(0xFFF9F6F0)
private val Blue = Color(0xFF81A6C6)
private val PaleBlue = Color(0xFFE5F0F7)
private val Sand = Color(0xFFF3E3D0)
private val Taupe = Color(0xFFD2C4B4)
private val SoftGreen = Color(0xFFE4F0E8)
private val SoftRed = Color(0xFFFFEDED)
private val ErrorRed = Color(0xFF9B3D3D)

@Composable
fun AttendanceScreen(
    navController: NavController,
    role: String,
    canStartAttendance: Boolean
) {
    val view = LocalView.current
    val context = LocalContext.current
    val isTeacher = role.equals("teacher", ignoreCase = true)
    var subject by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var shift by remember { mutableStateOf("") }
    var batch by remember { mutableStateOf("") }
    var subjectError by remember { mutableStateOf("") }
    var yearError by remember { mutableStateOf("") }
    var shiftError by remember { mutableStateOf("") }
    var sessionStarted by remember { mutableStateOf(false) }
    var sessionCode by remember { mutableStateOf("") }
    var remainingSeconds by remember { mutableIntStateOf(30) }
    var showYearMenu by remember { mutableStateOf(false) }
    var showShiftMenu by remember { mutableStateOf(false) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        val window = (view.context as? android.app.Activity)?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        onDispose { window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE) }
    }

    LaunchedEffect(sessionStarted) {
        if (sessionStarted) {
            remainingSeconds = 30
            while (remainingSeconds > 0) {
                delay(1_000)
                remainingSeconds -= 1
            }
            sessionStarted = false
        }
    }

    BackHandler { navController.popBackStack() }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(310.dp), drawerContainerColor = Surface) {
                AttendanceDrawer(navController = navController, isTeacher = isTeacher)
            }
        }
    ) {
        Scaffold(
            containerColor = Surface,
            topBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Blue)
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = Color.White)
                    }
                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                        Icon(Icons.Default.Menu, "Open menu", tint = Color.White)
                    }
                    Column(Modifier.weight(1f)) {
                        Text("Attendance", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold)
                        Text("Secure session management", color = Color.White.copy(alpha = 0.8f), fontSize = 11.sp)
                    }
                    Icon(Icons.Default.LocationOn, "Geofence status", tint = Color.White)
                }
            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(horizontal = 18.dp, vertical = 10.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .background(Ink)
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AttendanceBottomItem("Home", false) {
                        navController.navigate(if (isTeacher) "teacher_dashboard" else "cr_dashboard")
                    }
                    AttendanceBottomItem("Attendance", true) {}
                    AttendanceBottomItem("Resources", false) { navController.navigate("resources") }
                    AttendanceBottomItem("Notices", false) { navController.navigate("notices") }
                    AttendanceBottomItem("More", false) { navController.navigate("more") }
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 18.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text("Attendance management", color = Ink, fontSize = 27.sp, fontWeight = FontWeight.Bold)
                Text(
                    "Create a short-lived attendance session for the selected class.",
                    color = Ink.copy(alpha = 0.65f),
                    fontSize = 13.sp
                )

                SecurityNotice()

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.EventAvailable, null, tint = Ink)
                            Spacer(Modifier.width(8.dp))
                            Text("Session details", color = Ink, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                        }

                        OutlinedTextField(
                            value = subject,
                            onValueChange = { subject = it; subjectError = "" },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            label = { Text("Subject") },
                            isError = subjectError.isNotEmpty(),
                            supportingText = { if (subjectError.isNotEmpty()) Text(subjectError, color = ErrorRed) },
                            shape = RoundedCornerShape(14.dp),
                            colors = fieldColors(subjectError.isNotEmpty())
                        )

                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                            Box(Modifier.weight(1f)) {
                                OutlinedButton(
                                    onClick = { showYearMenu = true },
                                    modifier = Modifier.fillMaxWidth().height(56.dp),
                                    shape = RoundedCornerShape(14.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Ink),
                                    border = ButtonDefaults.outlinedButtonBorder
                                ) { Text(year.ifBlank { "Year" }) }
                                DropdownMenu(expanded = showYearMenu, onDismissRequest = { showYearMenu = false }, modifier = Modifier.background(Sand)) {
                                    listOf("First Year", "Second Year", "Third Year").forEach { option ->
                                        DropdownMenuItem(text = { Text(option, color = Color.Black) }, onClick = { year = option; yearError = ""; showYearMenu = false })
                                    }
                                }
                            }
                            Box(Modifier.weight(1f)) {
                                OutlinedButton(
                                    onClick = { showShiftMenu = true },
                                    modifier = Modifier.fillMaxWidth().height(56.dp),
                                    shape = RoundedCornerShape(14.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Ink),
                                    border = ButtonDefaults.outlinedButtonBorder
                                ) { Text(shift.ifBlank { "Shift" }) }
                                DropdownMenu(expanded = showShiftMenu, onDismissRequest = { showShiftMenu = false }, modifier = Modifier.background(Sand)) {
                                    listOf("First Shift", "Second Shift").forEach { option ->
                                        DropdownMenuItem(text = { Text(option, color = Color.Black) }, onClick = { shift = option; shiftError = ""; showShiftMenu = false })
                                    }
                                }
                            }
                        }

                        if (yearError.isNotEmpty()) Text(yearError, color = ErrorRed, fontSize = 12.sp)
                        if (shiftError.isNotEmpty()) Text(shiftError, color = ErrorRed, fontSize = 12.sp)

                        OutlinedTextField(
                            value = batch,
                            onValueChange = { batch = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            label = { Text("Batch / Practical group (optional)") },
                            shape = RoundedCornerShape(14.dp),
                            colors = fieldColors(false)
                        )

                        Text(
                            "The session ID is generated by the backend. Do not enter or manually create one.",
                            color = Ink.copy(alpha = 0.6f),
                            fontSize = 12.sp
                        )

                        if (!isTeacher && !canStartAttendance) {
                            Row(
                                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(SoftRed).padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(Icons.Default.Lock, null, tint = ErrorRed)
                                Spacer(Modifier.width(8.dp))
                                Text("You can view attendance, but starting a session requires an active CR permission.", color = ErrorRed, fontSize = 12.sp)
                            }
                        }

                        Button(
                            onClick = {
                                subjectError = if (subject.isBlank()) "Please select or enter a subject." else ""
                                yearError = if (year.isBlank()) "Please select a year." else ""
                                shiftError = if (shift.isBlank()) "Please select a shift." else ""
                                if (canStartAttendance && subjectError.isEmpty() && yearError.isEmpty() && shiftError.isEmpty()) {
                                    sessionCode = "MOCK-${System.currentTimeMillis().toString().takeLast(6)}"
                                    sessionStarted = true
                                }
                            },
                            enabled = canStartAttendance && !sessionStarted,
                            modifier = Modifier.fillMaxWidth().height(62.dp),
                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Blue, disabledContainerColor = Taupe)
                        ) {
                            Icon(Icons.Default.Timer, null)
                            Spacer(Modifier.width(8.dp))
                            Text(if (sessionStarted) "SESSION ACTIVE" else "START ATTENDANCE", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                if (sessionStarted && sessionCode.isNotBlank()) {
                    ActiveSessionCard(sessionCode, remainingSeconds) {
                        sessionStarted = false
                        sessionCode = ""
                    }
                }

                AttendanceOverviewCard()

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = PaleBlue)
                ) {
                    Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Backend enforcement checklist", color = Ink, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                        ChecklistRow("30-second server-side session expiry")
                        ChecklistRow("Fresh student location inside college geofence")
                        ChecklistRow("One attendance record per student per session")
                        ChecklistRow("Role and active CR permission checked at request time")
                        ChecklistRow("Reject suspicious mock-location or integrity signals")
                    }
                }
            }
        }
    }
}

@Composable
private fun AttendanceDrawer(navController: NavController, isTeacher: Boolean) {
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("MYGPM ADMIN", color = Ink, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(if (isTeacher) "Teacher" else "CR", color = Blue, fontSize = 12.sp)
        Spacer(Modifier.height(12.dp))
        DrawerLink("Home") { navController.navigate(if (isTeacher) "teacher_dashboard" else "cr_dashboard") }
        DrawerLink("Attendance") { navController.navigate("attendance/${if (isTeacher) "teacher" else "cr"}") }
        DrawerLink("Students") { navController.navigate("students") }
        DrawerLink("Notices") { navController.navigate("notices") }
        DrawerLink("Assignments") { navController.navigate("assignments") }
        DrawerLink("Resources / Notes") { navController.navigate("resources") }
        DrawerLink("Timetable") { navController.navigate("timetable") }
        DrawerLink("Settings") { navController.navigate("settings") }
        if (isTeacher) DrawerLink("Manage CR Access") { navController.navigate("cr_permissions") }
        else DrawerLink("My CR Permissions") { navController.navigate("my_permissions") }
    }
}

@Composable
private fun DrawerLink(label: String, onClick: () -> Unit) {
    Text(label, color = Ink, fontSize = 15.sp, modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).clickable { onClick() }.padding(14.dp))
}

@Composable
private fun SecurityNotice() {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(Sand).padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Security, null, tint = Ink)
        Spacer(Modifier.width(10.dp))
        Column(Modifier.weight(1f)) {
            Text("Protected attendance", color = Ink, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text("Location checks, server validation and screenshot protection are applied where supported.", color = Ink.copy(alpha = 0.7f), fontSize = 11.sp)
        }
    }
}

@Composable
private fun ActiveSessionCard(code: String, remainingSeconds: Int, onStop: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = SoftGreen)) {
        Column(Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("SESSION ACTIVE", color = Ink, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text(code, color = Ink, fontSize = 29.sp, fontWeight = FontWeight.ExtraBold)
            Text("Display this code only to the current class.", color = Ink.copy(alpha = 0.65f), fontSize = 12.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Timer, null, tint = Ink)
                Spacer(Modifier.width(6.dp))
                Text("Expires in ${remainingSeconds}s", color = Ink, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            OutlinedButton(onClick = onStop, shape = RoundedCornerShape(14.dp)) {
                Icon(Icons.Default.Close, null)
                Spacer(Modifier.width(6.dp))
                Text("End session")
            }
        }
    }
}

@Composable
private fun AttendanceOverviewCard() {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("Attendance overview", color = Ink, fontSize = 19.sp, fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                OverviewItem("Present", "--", SoftGreen, Modifier.weight(1f))
                OverviewItem("Absent", "--", SoftRed, Modifier.weight(1f))
                OverviewItem("Total", "--", PaleBlue, Modifier.weight(1f))
            }
            Text("Live counts will come from attendance_records after backend integration.", color = Ink.copy(alpha = 0.6f), fontSize = 11.sp)
        }
    }
}

@Composable
private fun OverviewItem(label: String, value: String, color: Color, modifier: Modifier) {
    Column(modifier.clip(RoundedCornerShape(15.dp)).background(color).padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = Ink, fontSize = 21.sp, fontWeight = FontWeight.Bold)
        Text(label, color = Ink, fontSize = 11.sp)
    }
}

@Composable
private fun ChecklistRow(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.CheckCircle, null, tint = Ink, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(8.dp))
        Text(text, color = Ink.copy(alpha = 0.75f), fontSize = 12.sp)
    }
}

@Composable
private fun AttendanceBottomItem(label: String, selected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clip(RoundedCornerShape(22.dp)).background(if (selected) Blue else Color.Transparent).clickable { onClick() }.padding(horizontal = 9.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.EventAvailable, contentDescription = label, tint = Sand, modifier = Modifier.size(19.dp))
        Text(label, color = Color.White, fontSize = 9.sp)
    }
}

@Composable
private fun fieldColors(isError: Boolean) = TextFieldDefaults.colors(
    focusedTextColor = Ink,
    unfocusedTextColor = Ink,
    focusedContainerColor = if (isError) SoftRed else Color.Transparent,
    unfocusedContainerColor = if (isError) SoftRed else Color.Transparent,
    errorContainerColor = SoftRed,
    focusedIndicatorColor = if (isError) Color(0xFFE8A8A8) else Ink,
    unfocusedIndicatorColor = if (isError) Color(0xFFE8A8A8) else Ink.copy(alpha = 0.3f),
    cursorColor = Ink
)
