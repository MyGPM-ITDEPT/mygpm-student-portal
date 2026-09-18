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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

private val Ink = Color(0xFF1A2340)
private val Surface = Color(0xFFF9F6F0)
private val Blue = Color(0xFF81A6C6)
private val PaleBlue = Color(0xFFE5F0F7)
private val Sand = Color(0xFFF3E3D0)
private val Taupe = Color(0xFFD2C4B4)
private val SoftGreen = Color(0xFFE4F0E8)
private val SoftRed = Color(0xFFFFEDED)

private data class CrMember(
    val name: String,
    val division: String,
    val enrollment: String,
    val active: Boolean
)

@Composable
fun ManageCRAccessScreen(navController: NavController) {
    val members = remember {
        mutableStateListOf(
            CrMember("Siya Malhotra", "TY IT · First Shift", "SM24IF001", true),
            CrMember("Reeva Jaiswal", "TY IT · Second Shift", "SM24IF002", true),
            CrMember("Akash Rawat", "TY IT · First Shift", "SM24IF003", false)
        )
    }

    var selectedMember by remember { mutableStateOf<CrMember?>(null) }

    Scaffold(
        containerColor = Surface,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Ink)
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text("Manage CR Access", color = Ink, fontSize = 23.sp, fontWeight = FontWeight.Bold)
                    Text("Grant and review scoped permissions", color = Ink.copy(alpha = 0.6f), fontSize = 12.sp)
                }
                Icon(Icons.Default.Security, contentDescription = null, tint = Blue)
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(Modifier.height(4.dp))
                InfoCard()
            }

            item {
                Text(
                    "Class Representatives",
                    color = Ink,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Select a CR to manage their permissions.",
                    color = Ink.copy(alpha = 0.6f),
                    fontSize = 12.sp
                )
            }

            items(members) { member ->
                CrCard(member = member, onClick = { selectedMember = member })
            }

            item {
                Spacer(Modifier.height(18.dp))
            }
        }
    }

    selectedMember?.let { member ->
        PermissionDialog(
            member = member,
            onDismiss = { selectedMember = null },
            onSaved = { selectedMember = null }
        )
    }
}

@Composable
private fun InfoCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(PaleBlue)
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(Icons.Default.Key, contentDescription = null, tint = Ink)
        Spacer(Modifier.width(12.dp))
        Column {
            Text("Permission-based access", color = Ink, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(Modifier.height(4.dp))
            Text(
                "CR access must be action-specific, scope-specific and checked by the backend at the time of each action.",
                color = Ink.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun CrCard(member: CrMember, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Sand),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Person, contentDescription = null, tint = Ink)
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(member.name, color = Ink, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            Text(member.division, color = Ink.copy(alpha = 0.65f), fontSize = 12.sp)
            Text(member.enrollment, color = Ink.copy(alpha = 0.5f), fontSize = 11.sp)
            Spacer(Modifier.height(5.dp))
            Text(
                if (member.active) "Access currently active" else "No active access",
                color = if (member.active) Color(0xFF39734A) else Color(0xFF9B3D3D),
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Icon(Icons.Default.ChevronRight, contentDescription = "Manage permissions", tint = Ink.copy(alpha = 0.6f))
    }
}

@Composable
private fun PermissionDialog(
    member: CrMember,
    onDismiss: () -> Unit,
    onSaved: () -> Unit
) {
    var startAttendance by remember { mutableStateOf(member.active) }
    var correctAttendance by remember { mutableStateOf(false) }
    var uploadResources by remember { mutableStateOf(false) }
    var publishNotices by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Surface,
        title = {
            Column {
                Text("CR Permissions", color = Ink, fontWeight = FontWeight.Bold)
                Text(member.name, color = Ink.copy(alpha = 0.6f), fontSize = 12.sp)
            }
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                PermissionSwitch("Start attendance", "START_ATTENDANCE", startAttendance) { startAttendance = it }
                PermissionSwitch("Correct attendance", "CORRECT_ATTENDANCE", correctAttendance) { correctAttendance = it }
                PermissionSwitch("Upload resources", "UPLOAD_RESOURCES", uploadResources) { uploadResources = it }
                PermissionSwitch("Publish notices", "PUBLISH_NOTICES", publishNotices) { publishNotices = it }

                Divider(color = Taupe)
                Text(
                    "These controls are UI placeholders. Aman must connect them to the cr_permissions table and backend authorization checks.",
                    color = Ink.copy(alpha = 0.65f),
                    fontSize = 11.sp
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onSaved,
                colors = ButtonDefaults.buttonColors(containerColor = Ink)
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel", color = Ink)
            }
        }
    )
}

@Composable
private fun PermissionSwitch(
    title: String,
    permissionCode: String,
    enabled: Boolean,
    onChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = Ink, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text(permissionCode, color = Ink.copy(alpha = 0.5f), fontSize = 10.sp)
        }
        Switch(checked = enabled, onCheckedChange = onChange)
    }
}
