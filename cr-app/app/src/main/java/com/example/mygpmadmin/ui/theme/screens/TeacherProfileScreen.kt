package com.example.mygpmadmin.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.Locale

// ---------------------------------------------------------
// MYGPM COLORS
// ---------------------------------------------------------

private val MyGPMBlue = Color(0xFF81A6C6)
private val MyGPMDeepBlue = Color(0xFF244B70)
private val MyGPMText = Color(0xFF172333)
private val MyGPMBorder = Color(0xFFD5DEE7)
private val MyGPMLightBlue = Color(0xFFF7FAFD)

private val MyGPMBeige = Color(0xFFF3E3D0)
private val MyGPMErrorBackground = Color(0xFFFFEDED)
private val MyGPMErrorBorder = Color(0xFFE8A8A8)
private val MyGPMErrorText = Color(0xFF9B3D3D)


// ---------------------------------------------------------
// TEACHER ASSIGNMENT DATA
// ---------------------------------------------------------

data class TeacherAssignmentInput(
    val subject: String = "",
    val classYear: String = "",
    val shift: String = ""
)

data class TeacherAssignmentErrors(
    val subject: String = "",
    val classYear: String = "",
    val shift: String = ""
)


// ---------------------------------------------------------
// TEACHER PROFILE SCREEN
// ---------------------------------------------------------

@Composable
fun TeacherProfileScreen(
    navController: NavController,
    username: String
) {

    var fullName by remember {
        mutableStateOf("")
    }

    var fullNameError by remember {
        mutableStateOf("")
    }

    // Starts with exactly ONE subject entry
    val assignments = remember {
        mutableStateListOf(
            TeacherAssignmentInput()
        )
    }

    val assignmentErrors = remember {
        mutableStateListOf(
            TeacherAssignmentErrors()
        )
    }

    // Allows the entire page to scroll
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 48.dp,
                    bottom = 32.dp
                )
        ) {

            // =====================================================
            // HEADER
            // =====================================================

            Text(
                text = "Let’s set up\nyour profile",
                modifier = Modifier.fillMaxWidth(),
                color = MyGPMText,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            Text(
                text = "Please enter your details below to continue.",
                modifier = Modifier.fillMaxWidth(),
                color = MyGPMText.copy(alpha = 0.60f),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "This helps us personalize your experience.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                color = MyGPMText.copy(alpha = 0.60f),
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )


            // =====================================================
            // ROLE SELECTION
            // =====================================================

            Text(
                text = "I am a",
                color = MyGPMText,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(9.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        width = 1.5.dp,
                        color = MyGPMBorder,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                // CR
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .clickable {
                            // CR selection will be connected later
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MyGPMText.copy(alpha = 0.55f),
                            modifier = Modifier.size(22.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(7.dp)
                        )

                        Text(
                            text = "Class Representative (CR)",
                            color = MyGPMText.copy(alpha = 0.70f),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }


                // Teacher selected
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            Color(0xFFF1F7FC)
                        )
                        .border(
                            width = 1.5.dp,
                            color = MyGPMBlue,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = MyGPMBlue,
                            modifier = Modifier.size(22.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(7.dp)
                        )

                        Text(
                            text = "Teacher",
                            color = MyGPMDeepBlue,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )


            // =====================================================
            // FULL NAME
            // =====================================================

            Text(
                text = "Full Name",
                color = MyGPMText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    fullNameError = ""
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Enter your official full name",
                        color = MyGPMText.copy(alpha = 0.45f)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null,
                        tint = MyGPMBlue
                    )
                },
                isError = fullNameError.isNotEmpty(),
                supportingText = {
                    if (fullNameError.isNotEmpty()) {
                        Text(
                            text = fullNameError,
                            color = MyGPMErrorText
                        )
                    }
                },
                shape = RoundedCornerShape(14.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MyGPMText,
                    unfocusedTextColor = MyGPMText,

                    errorContainerColor = MyGPMErrorBackground,

                    focusedContainerColor = if (fullNameError.isNotEmpty()) {
                        MyGPMErrorBackground
                    } else {
                        Color.White
                    },

                    unfocusedContainerColor = if (fullNameError.isNotEmpty()) {
                        MyGPMErrorBackground
                    } else {
                        Color.White
                    },

                    focusedIndicatorColor = if (fullNameError.isNotEmpty()) {
                        MyGPMErrorBorder
                    } else {
                        MyGPMBlue
                    },

                    unfocusedIndicatorColor = if (fullNameError.isNotEmpty()) {
                        MyGPMErrorBorder
                    } else {
                        MyGPMBorder
                    },

                    cursorColor = MyGPMDeepBlue
                )
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )


            // =====================================================
            // YOUR SUBJECTS
            // =====================================================

            Text(
                text = "Your Subjects",
                color = MyGPMDeepBlue,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Add the subjects you teach and the classes / shifts.",
                color = MyGPMText.copy(alpha = 0.60f),
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )


            // =====================================================
            // SUBJECT CONTAINER
            // =====================================================

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MyGPMLightBlue)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFDCE6EF),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            ) {

                assignments.forEachIndexed { index, assignment ->

                    TeacherAssignmentRow(
                        assignment = assignment,
                        errors = assignmentErrors[index],
                        showDelete = assignments.size > 1,

                        onSubjectChange = { value ->
                            assignments[index] =
                                assignments[index].copy(
                                    subject = value
                                )

                            assignmentErrors[index] =
                                assignmentErrors[index].copy(
                                    subject = ""
                                )
                        },

                        onClassChange = { value ->
                            assignments[index] =
                                assignments[index].copy(
                                    classYear = value
                                )

                            assignmentErrors[index] =
                                assignmentErrors[index].copy(
                                    classYear = ""
                                )
                        },

                        onShiftChange = { value ->
                            assignments[index] =
                                assignments[index].copy(
                                    shift = value
                                )

                            assignmentErrors[index] =
                                assignmentErrors[index].copy(
                                    shift = ""
                                )
                        },

                        onDelete = {
                            assignments.removeAt(index)
                            assignmentErrors.removeAt(index)
                        }
                    )

                    if (index != assignments.lastIndex) {

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MyGPMBorder)
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )
                    }
                }


                // =================================================
                // ADD ANOTHER SUBJECT
                // =================================================

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .border(
                            width = 1.3.dp,
                            color = MyGPMBlue,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {

                            assignments.add(
                                TeacherAssignmentInput()
                            )

                            assignmentErrors.add(
                                TeacherAssignmentErrors()
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null,
                            tint = MyGPMDeepBlue,
                            modifier = Modifier.size(21.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text(
                            text = "Add Another Subject",
                            color = MyGPMDeepBlue,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // =====================================================
            // INFORMATION BOX
            // =====================================================

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFF8FAFC))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE0E8F0),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(15.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Badge,
                        contentDescription = null,
                        tint = MyGPMBlue,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(11.dp)
                    )

                    Text(
                        text = "You can add multiple subjects, classes and shifts. You can manage them later.",
                        color = MyGPMText.copy(alpha = 0.70f),
                        fontSize = 13.sp,
                        lineHeight = 19.sp
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(28.dp)
            )


            // =====================================================
            // CONTINUE
            // =====================================================

            Button(
                onClick = {

                    // Clear previous errors
                    fullNameError = ""

                    for (index in assignmentErrors.indices) {
                        assignmentErrors[index] =
                            TeacherAssignmentErrors()
                    }


                    // -------------------------------------------------
                    // FULL NAME VALIDATION
                    // -------------------------------------------------

                    val cleanedName =
                        fullName.trim()

                    val nameParts =
                        cleanedName
                            .split(Regex("\\s+"))
                            .filter {
                                it.isNotBlank()
                            }

                    val nameCharacterRegex =
                        Regex("^[A-Za-z.' ]+$")


                    if (cleanedName.isBlank()) {

                        fullNameError =
                            "Please enter your official full name."

                    } else if (nameParts.size < 2) {

                        fullNameError =
                            "Please enter your full name."

                    } else if (!nameCharacterRegex.matches(cleanedName)) {

                        fullNameError =
                            "Name can only contain letters and normal name characters."

                    } else {

                        // -------------------------------------------------
                        // USERNAME FIRST TWO LETTERS VALIDATION
                        // -------------------------------------------------

                        val usernameLetters =
                            username
                                .substringBefore("@")
                                .filter {
                                    it.isLetter()
                                }
                                .lowercase(Locale.getDefault())

                        val nameLetters =
                            cleanedName
                                .filter {
                                    it.isLetter()
                                }
                                .lowercase(Locale.getDefault())

                        if (
                            usernameLetters.length >= 2 &&
                            nameLetters.length >= 2 &&
                            nameLetters.take(2) != usernameLetters.take(2)
                        ) {

                            fullNameError =
                                "Your name must start with the first two letters of your username."
                        }
                    }


                    // -------------------------------------------------
                    // SUBJECT / YEAR / SHIFT VALIDATION
                    // -------------------------------------------------

                    assignments.forEachIndexed { index, assignment ->

                        var subjectError = ""
                        var classYearError = ""
                        var shiftError = ""


                        if (assignment.subject.trim().isBlank()) {

                            subjectError =
                                "Please enter your subject."

                        }


                        if (assignment.classYear.trim().isBlank()) {

                            classYearError =
                                "Please select the year."
                        }


                        if (assignment.shift.trim().isBlank()) {

                            shiftError =
                                "Please select the shift."
                        }


                        assignmentErrors[index] =
                            TeacherAssignmentErrors(
                                subject = subjectError,
                                classYear = classYearError,
                                shift = shiftError
                            )
                    }


                    // -------------------------------------------------
                    // CONTINUE ONLY IF EVERYTHING IS VALID
                    // -------------------------------------------------

                    val hasAssignmentErrors =
                        assignmentErrors.any {
                            it.subject.isNotEmpty() ||
                                    it.classYear.isNotEmpty() ||
                                    it.shift.isNotEmpty()
                        }

                    if (
                        fullNameError.isEmpty() &&
                        !hasAssignmentErrors
                    ) {

                        navController.navigate("success/teacher")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MyGPMDeepBlue
                )
            ) {

                Text(
                    text = "Continue",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )
        }
    }
}


// =============================================================
// TEACHER ASSIGNMENT ROW
// =============================================================

@Composable
private fun TeacherAssignmentRow(
    assignment: TeacherAssignmentInput,
    errors: TeacherAssignmentErrors,
    showDelete: Boolean,
    onSubjectChange: (String) -> Unit,
    onClassChange: (String) -> Unit,
    onShiftChange: (String) -> Unit,
    onDelete: () -> Unit
) {

    var yearExpanded by remember {
        mutableStateOf(false)
    }

    var shiftExpanded by remember {
        mutableStateOf(false)
    }

    val years = listOf(
        "First Year",
        "Second Year",
        "Third Year"
    )

    val shifts = listOf(
        "First Shift",
        "Second Shift"
    )

    Column {

        // -----------------------------------------------------
        // SUBJECT
        // -----------------------------------------------------

        Text(
            text = "Subject",
            color = MyGPMText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(
            modifier = Modifier.height(7.dp)
        )

        OutlinedTextField(
            value = assignment.subject,
            onValueChange = onSubjectChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = {
                Text(
                    text = "Enter subject",
                    fontSize = 14.sp,
                    color = MyGPMText.copy(alpha = 0.45f)
                )
            },
            isError = errors.subject.isNotEmpty(),
            supportingText = {
                if (errors.subject.isNotEmpty()) {
                    Text(
                        text = errors.subject,
                        color = MyGPMErrorText
                    )
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MyGPMText,
                unfocusedTextColor = MyGPMText,
                errorContainerColor = MyGPMErrorBackground,
                focusedContainerColor = if (errors.subject.isNotEmpty()) {
                    MyGPMErrorBackground
                } else {
                    Color.White
                },
                unfocusedContainerColor = if (errors.subject.isNotEmpty()) {
                    MyGPMErrorBackground
                } else {
                    Color.White
                },
                focusedIndicatorColor = if (errors.subject.isNotEmpty()) {
                    MyGPMErrorBorder
                } else {
                    MyGPMBlue
                },
                unfocusedIndicatorColor = if (errors.subject.isNotEmpty()) {
                    MyGPMErrorBorder
                } else {
                    MyGPMBorder
                },
                cursorColor = MyGPMDeepBlue
            )
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        // -----------------------------------------------------
        // CLASS / YEAR + SHIFT
        // -----------------------------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Top
        ) {

            // =================================================
            // YEAR
            // =================================================

            Box(
                modifier = Modifier.weight(1f)
            ) {

                Column {

                    Text(
                        text = "Class / Year",
                        color = MyGPMText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(
                        modifier = Modifier.height(7.dp)
                    )

                    Box {

                        OutlinedTextField(
                            value = assignment.classYear,
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(58.dp),
                            readOnly = true,
                            singleLine = true,
                            placeholder = {
                                Text(
                                    text = "Select year",
                                    fontSize = 13.sp,
                                    color = MyGPMText.copy(alpha = 0.45f)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.School,
                                    contentDescription = null,
                                    tint = MyGPMDeepBlue
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.Transparent
                                )
                            },
                            isError = errors.classYear.isNotEmpty(),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = MyGPMText,
                                unfocusedTextColor = MyGPMText,
                                errorContainerColor = MyGPMErrorBackground,
                                focusedContainerColor = if (errors.classYear.isNotEmpty()) {
                                    MyGPMErrorBackground
                                } else {
                                    Color.White
                                },
                                unfocusedContainerColor = if (errors.classYear.isNotEmpty()) {
                                    MyGPMErrorBackground
                                } else {
                                    Color.White
                                },
                                focusedIndicatorColor = if (errors.classYear.isNotEmpty()) {
                                    MyGPMErrorBorder
                                } else {
                                    MyGPMBlue
                                },
                                unfocusedIndicatorColor = if (errors.classYear.isNotEmpty()) {
                                    MyGPMErrorBorder
                                } else {
                                    MyGPMBorder
                                },
                                cursorColor = MyGPMDeepBlue
                            )
                        )

                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clickable {
                                    yearExpanded = true
                                }
                        )

                        Text(
                            text = "▼",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 13.dp),
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )

                        DropdownMenu(
                            expanded = yearExpanded,
                            onDismissRequest = {
                                yearExpanded = false
                            },
                            modifier = Modifier
                                .background(MyGPMBeige)
                        ) {
                            years.forEach { selectedYear ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = selectedYear,
                                            color = Color.Black
                                        )
                                    },
                                    onClick = {
                                        onClassChange(selectedYear)
                                        yearExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    if (errors.classYear.isNotEmpty()) {
                        Text(
                            text = errors.classYear,
                            color = MyGPMErrorText,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(
                                start = 4.dp,
                                top = 3.dp
                            )
                        )
                    }
                }
            }

            // =================================================
            // SHIFT
            // =================================================

            Box(
                modifier = Modifier.weight(1f)
            ) {

                Column {

                    Text(
                        text = "Shift",
                        color = MyGPMText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(
                        modifier = Modifier.height(7.dp)
                    )

                    Box {

                        OutlinedTextField(
                            value = assignment.shift,
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(58.dp),
                            readOnly = true,
                            singleLine = true,
                            placeholder = {
                                Text(
                                    text = "Select shift",
                                    fontSize = 13.sp,
                                    color = MyGPMText.copy(alpha = 0.45f)
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Badge,
                                    contentDescription = null,
                                    tint = MyGPMDeepBlue
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowForward,
                                    contentDescription = null,
                                    tint = Color.Transparent
                                )
                            },
                            isError = errors.shift.isNotEmpty(),
                            shape = RoundedCornerShape(12.dp),
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = MyGPMText,
                                unfocusedTextColor = MyGPMText,
                                errorContainerColor = MyGPMErrorBackground,
                                focusedContainerColor = if (errors.shift.isNotEmpty()) {
                                    MyGPMErrorBackground
                                } else {
                                    Color.White
                                },
                                unfocusedContainerColor = if (errors.shift.isNotEmpty()) {
                                    MyGPMErrorBackground
                                } else {
                                    Color.White
                                },
                                focusedIndicatorColor = if (errors.shift.isNotEmpty()) {
                                    MyGPMErrorBorder
                                } else {
                                    MyGPMBlue
                                },
                                unfocusedIndicatorColor = if (errors.shift.isNotEmpty()) {
                                    MyGPMErrorBorder
                                } else {
                                    MyGPMBorder
                                },
                                cursorColor = MyGPMDeepBlue
                            )
                        )

                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clickable {
                                    shiftExpanded = true
                                }
                        )

                        Text(
                            text = "▼",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 13.dp),
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )

                        DropdownMenu(
                            expanded = shiftExpanded,
                            onDismissRequest = {
                                shiftExpanded = false
                            },
                            modifier = Modifier
                                .background(MyGPMBeige)
                        ) {
                            shifts.forEach { selectedShift ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = selectedShift,
                                            color = Color.Black
                                        )
                                    },
                                    onClick = {
                                        onShiftChange(selectedShift)
                                        shiftExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    if (errors.shift.isNotEmpty()) {
                        Text(
                            text = errors.shift,
                            color = MyGPMErrorText,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(
                                start = 4.dp,
                                top = 3.dp
                            )
                        )
                    }
                }
            }

            // -------------------------------------------------
            // DELETE
            // -------------------------------------------------

            if (showDelete) {

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .align(Alignment.Bottom)
                        .clickable {
                            onDelete()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.DeleteOutline,
                        contentDescription = "Remove subject",
                        tint = Color(0xFFB94A48),
                        modifier = Modifier.size(23.dp)
                    )
                }
            }
        }
    }
}

