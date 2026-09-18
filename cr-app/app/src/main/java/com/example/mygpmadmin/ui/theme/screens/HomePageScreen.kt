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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.EventAvailable
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.TableChart
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
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

private val Ink = Color(0xFF1A2340)
private val Surface = Color(0xFFF9F6F0)
private val Blue = Color(0xFF81A6C6)
private val PaleBlue = Color(0xFFE5F0F7)
private val Sand = Color(0xFFF3E3D0)
private val Taupe = Color(0xFFD2C4B4)
private val SoftGreen = Color(0xFFE4F0E8)
private val SoftLavender = Color(0xFFEDE8F7)
private val White = Color.White

@Composable
fun HomePageScreen(
    navController: NavController,
    role: String,
    fullName: String,
    hasAssignedSubjects: Boolean = false
) {
    val isTeacher = role.equals("teacher", ignoreCase = true)
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

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
                        Icon(Icons.Default.Dashboard, null, tint = Sand)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("MYGPM", color = Ink, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Text("ADMIN", color = Blue, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = { scope.launch { drawerState.close() } }) {
                        Icon(Icons.Default.Close, contentDescription = "Close menu", tint = Ink)
                    }
                }
                Divider(color = Taupe)
                Spacer(Modifier.height(10.dp))

                DrawerItem("Home", Icons.Default.Home) { navController.navigate(if (isTeacher) "teacher_dashboard" else "cr_dashboard") }
                DrawerItem("Attendance", Icons.Default.EventAvailable) {navController.navigate(
                    "attendance"
                )}
                DrawerItem("Students", Icons.Default.People) { navController.navigate("students") }
                DrawerItem("Notices", Icons.Default.NotificationsNone) { navController.navigate("notices") }
                DrawerItem("Assignments", Icons.Default.Assignment) { navController.navigate("assignments") }
                DrawerItem("Resources / Notes", Icons.Default.Folder) { navController.navigate("resources") }
                DrawerItem("Timetable", Icons.Default.CalendarMonth) { navController.navigate("timetable") }
                DrawerItem("Curriculum Tracker", Icons.Default.TableChart) { navController.navigate("curriculum") }
                DrawerItem("Analytics", Icons.Default.Analytics) { navController.navigate("analytics") }

                if (isTeacher) {
                    DrawerItem("Manage CR Access", Icons.Default.Key) {
                        navController.navigate("cr_permissions")
                    }
                } else {
                    DrawerItem("My CR Permissions", Icons.Default.Security) {
                        navController.navigate("my_permissions")
                    }
                }

                DrawerItem("MIS Portal", Icons.Default.Visibility) { navController.navigate("mis_portal") }
                DrawerItem("Notifications", Icons.Default.NotificationsNone) { navController.navigate("notifications") }
                DrawerItem("Profile / Settings", Icons.Default.Person) { navController.navigate("profile_settings") }
                DrawerItem("Settings", Icons.Default.Settings) { navController.navigate("settings") }
                Spacer(Modifier.weight(1f))
                Divider(color = Taupe)
                DrawerItem("Logout", Icons.Default.SwapHoriz) { navController.navigate("login") }
                Spacer(Modifier.height(12.dp))
            }
        }
    ) {
        Scaffold(
            containerColor = Surface,
            bottomBar = {
                BottomPillBar(
                    navController = navController,
                    isTeacher = isTeacher,
                    onMoreClick = { scope.launch { drawerState.open() } }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(innerPadding).verticalScroll(rememberScrollState())
            ) {
                TopHeader(
                    fullName = fullName,
                    role = role,
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onGeofenceClick = { navController.navigate("geofencing") },
                    onNotificationsClick = { navController.navigate("notifications") }
                )
                DashboardContent(
                    navController = navController,
                    isTeacher = isTeacher,
                    fullName = fullName,
                    hasAssignedSubjects = hasAssignedSubjects
                )
            }
        }
    }
}

@Composable
private fun TopHeader(
    fullName: String,
    role: String,
    onMenuClick: () -> Unit,
    onGeofenceClick: () -> Unit,
    onNotificationsClick: () -> Unit
) {
    Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 14.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onMenuClick) { Icon(Icons.Default.Menu, "Open menu", tint = Ink) }
            Spacer(Modifier.width(4.dp))
            Column(Modifier.weight(1f)) {
                Text("MYGPM ADMIN", color = Ink, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                Text(role.replaceFirstChar { it.uppercase() }, color = Blue, fontSize = 12.sp)
            }
            IconButton(onClick = onGeofenceClick) {
                Icon(Icons.Default.LocationOn, "Geofencing", tint = Ink)
            }
            IconButton(onClick = onNotificationsClick) {
                Icon(Icons.Default.NotificationsNone, "Notifications", tint = Ink)
            }
            Box(
                modifier = Modifier.size(40.dp).clip(CircleShape).background(Sand),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Person, "Profile", tint = Ink)
            }
        }
        Spacer(Modifier.height(18.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(PaleBlue)
                .padding(20.dp)
        ) {
            Text(
                "Good morning,",
                color = Ink.copy(alpha = 0.65f),
                fontSize = 16.sp
            )
            Text(
                text = fullName.ifBlank { "Welcome back" },
                color = Ink,
                fontSize = 31.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Here is your academic dashboard for today.",
                color = Ink.copy(alpha = 0.65f),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
private fun DashboardContent(
    navController: NavController,
    isTeacher: Boolean,
    fullName: String,
    hasAssignedSubjects: Boolean
) {
    Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            MetricCard("Classes today", "4", "View timetable", PaleBlue, Icons.Default.Today, Modifier.weight(1f)) {
                navController.navigate("timetable")
            }
            MetricCard("Attendance", "2 / 4", "View records", SoftGreen, Icons.Default.EventAvailable, Modifier.weight(1f)) {
                navController.navigate(
                    "attendance"
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            MetricCard("Students", "128", "View students", Sand, Icons.Default.Group, Modifier.weight(1f)) {
                navController.navigate("students")
            }
            MetricCard("Pending tasks", "3", "View tasks", SoftLavender, Icons.Default.Assignment, Modifier.weight(1f)) {
                navController.navigate("assignments")
            }
        }

        if (hasAssignedSubjects) {
            SectionTitle("Today's schedule", "View full timetable") {
                navController.navigate("timetable")
            }
            ScheduleCard(
                "09:00 AM",
                "Network Information & Security",
                "TY IT · Room 204",
                PaleBlue
            )
            ScheduleCard(
                "11:00 AM",
                "Python Programming",
                "TY IT · Lab 2",
                Sand
            )
        }

        SectionTitle("Quick actions", "") {}
        if (isTeacher) {
            ActionCard("Manage CR access", "Grant, review or revoke scoped permissions", Icons.Default.Key, SoftLavender) {
                navController.navigate("cr_permissions")
            }
            ActionCard("Start attendance", "Create and manage a subject session", Icons.Default.EventAvailable, PaleBlue) {
                navController.navigate(
                    "attendance"
                )
            }
            ActionCard("Upload academic material", "Add assignments, PDFs, images or documents", Icons.Default.UploadFile, Sand) {
                navController.navigate("assignments")
            }
        } else {
            ActionCard("My CR permissions", "View actions currently assigned to you", Icons.Default.Security, SoftLavender) {
                navController.navigate("my_permissions")
            }
            ActionCard("Attendance tools", "Start or manage attendance when authorized", Icons.Default.EventAvailable, PaleBlue) {
                navController.navigate(
                    "attendance"
                )
            }
            ActionCard("Class updates", "Share permitted notices and learning material", Icons.Default.UploadFile, Sand) {
                navController.navigate("assignments")
            }
        }

        SectionTitle("Recent activity", "View all") { navController.navigate("notifications") }
        ActivityRow("Attendance session updated", "Today · 10 minutes ago", Icons.Default.EventAvailable)
        ActivityRow("New academic resource uploaded", "Today · 35 minutes ago", Icons.Default.Folder)
        ActivityRow("Class notice published", "Yesterday", Icons.Default.NotificationsNone)
        Spacer(Modifier.height(20.dp))
    }
}

@Composable
private fun MetricCard(
    title: String,
    value: String,
    action: String,
    color: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.clip(RoundedCornerShape(22.dp)).background(color).clickable { onClick() }.padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = Ink, modifier = Modifier.size(20.dp))
            Spacer(Modifier.weight(1f))
            Icon(Icons.Default.ChevronRight, null, tint = Ink.copy(alpha = 0.6f), modifier = Modifier.size(18.dp))
        }
        Spacer(Modifier.height(14.dp))
        Text(value, color = Ink, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Text(title, color = Ink, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(6.dp))
        Text(action, color = Ink.copy(alpha = 0.65f), fontSize = 11.sp)
    }
}

@Composable
private fun SectionTitle(title: String, action: String, onClick: () -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(title, color = Ink, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        if (action.isNotBlank()) {
            Text(action, color = Ink.copy(alpha = 0.65f), fontSize = 11.sp, modifier = Modifier.clickable { onClick() })
        }
    }
}

@Composable
private fun ScheduleCard(time: String, subject: String, details: String, color: Color) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(White).padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.width(5.dp).height(62.dp).clip(RoundedCornerShape(5.dp)).background(color))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(time, color = Blue, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(subject, color = Ink, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            Text(details, color = Ink.copy(alpha = 0.6f), fontSize = 12.sp)
        }
        Icon(Icons.Default.ChevronRight, null, tint = Ink.copy(alpha = 0.55f))
    }
}

@Composable
private fun ActionCard(title: String, description: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(20.dp)).background(color).clickable { onClick() }.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(46.dp).clip(CircleShape).background(White.copy(alpha = 0.75f)), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = Ink)
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = Ink, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Text(description, color = Ink.copy(alpha = 0.65f), fontSize = 12.sp)
        }
        Icon(Icons.Default.ChevronRight, null, tint = Ink.copy(alpha = 0.65f))
    }
}

@Composable
private fun ActivityRow(title: String, time: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    Row(Modifier.fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(White).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.size(40.dp).clip(CircleShape).background(PaleBlue), contentAlignment = Alignment.Center) {
            Icon(icon, null, tint = Ink, modifier = Modifier.size(19.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = Ink, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text(time, color = Ink.copy(alpha = 0.55f), fontSize = 11.sp)
        }
    }
}

@Composable
private fun DrawerItem(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).clickable { onClick() }.padding(horizontal = 22.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, null, tint = Ink.copy(alpha = 0.8f), modifier = Modifier.size(20.dp))
        Spacer(Modifier.width(14.dp))
        Text(label, color = Ink, fontSize = 14.sp)
    }
}

@Composable
private fun BottomPillBar(
    navController: NavController,
    isTeacher: Boolean,
    onMoreClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth().background(Surface).navigationBarsPadding().padding(horizontal = 18.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(32.dp)).background(Ink).padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomItem("Home", Icons.Default.Home) { navController.navigate(if (isTeacher) "teacher_dashboard" else "cr_dashboard") }
            BottomItem("Attendance", Icons.Default.EventAvailable) { navController.navigate(
                "attendance"
            )}
            BottomItem("Resources", Icons.Default.Folder) { navController.navigate("resources") }
            BottomItem("Notices", Icons.Default.NotificationsNone) { navController.navigate("notices") }
            BottomItem("More", Icons.Default.MoreHoriz, onMoreClick)
        }
    }
}

@Composable
private fun BottomItem(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Column(
        modifier = Modifier.clip(RoundedCornerShape(22.dp)).clickable { onClick() }.padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(icon, contentDescription = label, tint = Sand, modifier = Modifier.size(20.dp))
        Text(label, color = White, fontSize = 9.sp)
    }
}

