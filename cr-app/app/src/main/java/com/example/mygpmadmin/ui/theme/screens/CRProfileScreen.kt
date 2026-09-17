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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// ---------------------------------------------------------
// MYGPM COLORS
// ---------------------------------------------------------

private val MyGPMBlue = Color(0xFF81A6C6)
private val MyGPMDeepBlue = Color(0xFF244B70)
private val MyGPMText = Color(0xFF172333)
private val MyGPMBorder = Color(0xFFD5DEE7)

private val MyGPMBeige = Color(0xFFF3E3D0)
private val MyGPMErrorBackground = Color(0xFFFFEDED)
private val MyGPMErrorBorder = Color(0xFFE8A8A8)
private val MyGPMErrorText = Color(0xFF9B3D3D)


// ---------------------------------------------------------
// CR PROFILE SCREEN
// ---------------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CRProfileScreen(
    navController: NavController
) {

    var fullName by remember {
        mutableStateOf("")
    }

    var enrollmentNumber by remember {
        mutableStateOf("")
    }

    var dateOfBirth by remember {
        mutableStateOf("")
    }

    var year by remember {
        mutableStateOf("")
    }

    var shift by remember {
        mutableStateOf("")
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var yearExpanded by remember {
        mutableStateOf(false)
    }

    var shiftExpanded by remember {
        mutableStateOf(false)
    }

    // ---------------------------------------------------------
    // VALIDATION STATES
    // ---------------------------------------------------------

    var fullNameError by remember {
        mutableStateOf("")
    }

    var enrollmentNumberError by remember {
        mutableStateOf("")
    }

    var dateOfBirthError by remember {
        mutableStateOf("")
    }

    var yearError by remember {
        mutableStateOf("")
    }

    var shiftError by remember {
        mutableStateOf("")
    }

    val scrollState = rememberScrollState()

    val years = listOf(
        "First Year",
        "Second Year",
        "Third Year"
    )

    val shifts = listOf(
        "First Shift",
        "Second Shift"
    )

    val blockedWords = listOf(
        "fuck",
        "shit",
        "ass",
        "ahh"
    )


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

                // CR SELECTED
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF1F7FC))
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
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = MyGPMBlue,
                            modifier = Modifier.size(22.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(7.dp)
                        )

                        Text(
                            text = "Class Representative (CR)",
                            color = MyGPMDeepBlue,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }


                // TEACHER
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = MyGPMText.copy(alpha = 0.55f),
                            modifier = Modifier.size(22.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(7.dp)
                        )

                        Text(
                            text = "Teacher",
                            color = MyGPMText.copy(alpha = 0.70f),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
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
                modifier = Modifier.height(24.dp)
            )


            // =====================================================
            // ENROLLMENT NUMBER
            // =====================================================

            Text(
                text = "Enrollment Number",
                color = MyGPMText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            OutlinedTextField(
                value = enrollmentNumber,
                onValueChange = {
                    enrollmentNumber = it
                    enrollmentNumberError = ""

                    val firstLetter = it.trim().firstOrNull()?.uppercaseChar()

                    when (firstLetter) {
                        'F' -> {
                            shift = "First Shift"
                            shiftError = ""
                        }

                        'S' -> {
                            shift = "Second Shift"
                            shiftError = ""
                        }

                        else -> {
                            shift = ""
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                placeholder = {
                    Text(
                        text = "Enter your enrollment number",
                        color = MyGPMText.copy(alpha = 0.45f)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Badge,
                        contentDescription = null,
                        tint = MyGPMBlue
                    )
                },
                isError = enrollmentNumberError.isNotEmpty(),
                supportingText = {
                    if (enrollmentNumberError.isNotEmpty()) {
                        Text(
                            text = enrollmentNumberError,
                            color = MyGPMErrorText
                        )
                    }
                },
                shape = RoundedCornerShape(14.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MyGPMText,
                    unfocusedTextColor = MyGPMText,
                    errorContainerColor = MyGPMErrorBackground,
                    focusedContainerColor = if (enrollmentNumberError.isNotEmpty()) {
                        MyGPMErrorBackground
                    } else {
                        Color.White
                    },
                    unfocusedContainerColor = if (enrollmentNumberError.isNotEmpty()) {
                        MyGPMErrorBackground
                    } else {
                        Color.White
                    },
                    focusedIndicatorColor = if (enrollmentNumberError.isNotEmpty()) {
                        MyGPMErrorBorder
                    } else {
                        MyGPMBlue
                    },
                    unfocusedIndicatorColor = if (enrollmentNumberError.isNotEmpty()) {
                        MyGPMErrorBorder
                    } else {
                        MyGPMBorder
                    },
                    cursorColor = MyGPMDeepBlue
                )
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )


            // =====================================================
            // DATE OF BIRTH
            // =====================================================

            Text(
                text = "Date of Birth",
                color = MyGPMText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDatePicker = true
                    }
            ) {

                OutlinedTextField(
                    value = dateOfBirth,
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    readOnly = true,
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Select your date of birth",
                            color = MyGPMText.copy(alpha = 0.45f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = null,
                            tint = MyGPMBlue
                        )
                    },
                    isError = dateOfBirthError.isNotEmpty(),
                    supportingText = {
                        if (dateOfBirthError.isNotEmpty()) {
                            Text(
                                text = dateOfBirthError,
                                color = MyGPMErrorText
                            )
                        }
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = TextFieldDefaults.colors(
                        disabledTextColor = MyGPMText,
                        disabledContainerColor = if (dateOfBirthError.isNotEmpty()) {
                            MyGPMErrorBackground
                        } else {
                            Color.White
                        },
                        disabledIndicatorColor = if (dateOfBirthError.isNotEmpty()) {
                            MyGPMErrorBorder
                        } else {
                            MyGPMBorder
                        },
                        disabledLeadingIconColor = MyGPMBlue,
                        disabledPlaceholderColor = MyGPMText.copy(alpha = 0.45f)
                    )
                )
            }


            // =====================================================
            // DATE PICKER
            // =====================================================

            if (showDatePicker) {

                val datePickerState = rememberDatePickerState()

                DatePickerDialog(
                    onDismissRequest = {
                        showDatePicker = false
                    },
                    confirmButton = {

                        TextButton(
                            onClick = {

                                val selectedDate =
                                    datePickerState.selectedDateMillis

                                if (selectedDate != null) {

                                    val selectedCalendar =
                                        Calendar.getInstance().apply {
                                            timeInMillis = selectedDate
                                        }

                                    val today =
                                        Calendar.getInstance()

                                    var age =
                                        today.get(Calendar.YEAR) -
                                                selectedCalendar.get(Calendar.YEAR)

                                    if (
                                        today.get(Calendar.DAY_OF_YEAR) <
                                        selectedCalendar.get(Calendar.DAY_OF_YEAR)
                                    ) {
                                        age--
                                    }

                                    if (age in 15..23) {

                                        val formatter =
                                            SimpleDateFormat(
                                                "dd / MM / yyyy",
                                                Locale.getDefault()
                                            )

                                        dateOfBirth =
                                            formatter.format(
                                                Date(selectedDate)
                                            )

                                        dateOfBirthError = ""

                                    } else {

                                        dateOfBirthError =
                                            "Age must be between 15 to 23 years."
                                    }
                                }

                                showDatePicker = false
                            }
                        ) {
                            Text(
                                text = "OK",
                                color = MyGPMDeepBlue
                            )
                        }
                    },
                    dismissButton = {

                        TextButton(
                            onClick = {
                                showDatePicker = false
                            }
                        ) {
                            Text(
                                text = "Cancel",
                                color = MyGPMDeepBlue
                            )
                        }
                    }
                ) {

                    DatePicker(
                        state = datePickerState
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(24.dp)
            )


            // =====================================================
            // YEAR
            // =====================================================

            Text(
                text = "Year",
                color = MyGPMText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            ExposedDropdownMenuBox(
                expanded = yearExpanded,
                onExpandedChange = {
                    yearExpanded = !yearExpanded
                }
            ) {

                OutlinedTextField(
                    value = year,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(
                            MenuAnchorType.PrimaryNotEditable
                        ),
                    readOnly = true,
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Select your year",
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
                    isError = yearError.isNotEmpty(),
                    supportingText = {
                        if (yearError.isNotEmpty()) {
                            Text(
                                text = yearError,
                                color = MyGPMErrorText
                            )
                        }
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = yearExpanded
                        )
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MyGPMText,
                        unfocusedTextColor = MyGPMText,
                        errorContainerColor = MyGPMErrorBackground,

                        focusedContainerColor = if (yearError.isNotEmpty()) {
                            MyGPMErrorBackground
                        } else {
                            Color.White
                        },
                        unfocusedContainerColor = if (yearError.isNotEmpty()) {
                            MyGPMErrorBackground
                        } else {
                            Color.White
                        },
                        focusedIndicatorColor = if (yearError.isNotEmpty()) {
                            MyGPMErrorBorder
                        } else {
                            MyGPMBlue
                        },
                        unfocusedIndicatorColor = if (yearError.isNotEmpty()) {
                            MyGPMErrorBorder
                        } else {
                            MyGPMBorder
                        },
                        cursorColor = MyGPMDeepBlue
                    )
                )

                ExposedDropdownMenu(
                    expanded = yearExpanded,
                    onDismissRequest = {
                        yearExpanded = false
                    },
                    modifier = Modifier.background(MyGPMBeige)
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

                                year = selectedYear
                                yearError = ""
                                yearExpanded = false
                            }
                        )
                    }
                }
            }


            Spacer(
                modifier = Modifier.height(24.dp)
            )


            // =====================================================
            // SHIFT
            // =====================================================

            Text(
                text = "Shift",
                color = MyGPMText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            ExposedDropdownMenuBox(
                expanded = shiftExpanded,
                onExpandedChange = {
                    shiftExpanded = !shiftExpanded
                }
            ) {

                OutlinedTextField(
                    value = shift,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(
                            MenuAnchorType.PrimaryNotEditable
                        ),
                    readOnly = true,
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Select your shift",
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
                    isError = shiftError.isNotEmpty(),
                    supportingText = {
                        if (shiftError.isNotEmpty()) {
                            Text(
                                text = shiftError,
                                color = MyGPMErrorText
                            )
                        }
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = shiftExpanded
                        )
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = MyGPMText,
                        unfocusedTextColor = MyGPMText,
                        errorContainerColor = MyGPMErrorBackground,
                        focusedContainerColor = if (shiftError.isNotEmpty()) {
                            MyGPMErrorBackground
                        } else {
                            Color.White
                        },
                        unfocusedContainerColor = if (shiftError.isNotEmpty()) {
                            MyGPMErrorBackground
                        } else {
                            Color.White
                        },
                        focusedIndicatorColor = if (shiftError.isNotEmpty()) {
                            MyGPMErrorBorder
                        } else {
                            MyGPMBlue
                        },
                        unfocusedIndicatorColor = if (shiftError.isNotEmpty()) {
                            MyGPMErrorBorder
                        } else {
                            MyGPMBorder
                        },
                        cursorColor = MyGPMDeepBlue
                    )
                )

                ExposedDropdownMenu(
                    expanded = shiftExpanded,
                    onDismissRequest = {
                        shiftExpanded = false
                    },
                    modifier = Modifier.background(MyGPMBeige)
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

                                shift = selectedShift
                                shiftError = ""
                                shiftExpanded = false
                            }
                        )
                    }
                }
            }


            Spacer(
                modifier = Modifier.height(30.dp)
            )


            // =====================================================
            // CONTINUE
            // =====================================================

            Button(
                onClick = {

                    // Clear previous errors
                    fullNameError = ""
                    enrollmentNumberError = ""
                    dateOfBirthError = ""
                    yearError = ""
                    shiftError = ""

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

                    val containsBlockedWord =
                        nameParts.any { word ->
                            blockedWords.contains(
                                word.lowercase(Locale.getDefault())
                            )
                        }

                    if (cleanedName.isBlank()) {

                        fullNameError =
                            "Please enter your official full name."

                    } else if (nameParts.size < 2) {

                        fullNameError =
                            "Please enter your full name."

                    } else if (!nameCharacterRegex.matches(cleanedName)) {

                        fullNameError =
                            "Name can only contain letters and normal name characters."

                    } else if (containsBlockedWord) {

                        fullNameError =
                            "Please enter your official full name."

                    }


                    // -------------------------------------------------
                    // ENROLLMENT NUMBER VALIDATION
                    // -------------------------------------------------

                    val enrollmentRegex =
                        Regex("^[FSfs][A-Za-z]\\d{2}[A-Za-z]{2}\\d{3}$")

                    val cleanedEnrollment =
                        enrollmentNumber.trim()

                    if (cleanedEnrollment.isBlank()) {

                        enrollmentNumberError =
                            "Please enter your enrollment number."

                    } else if (!enrollmentRegex.matches(cleanedEnrollment)) {

                        enrollmentNumberError =
                            "Enrollment number must be in the format SM24IF001 and start with F or S."
                    }


                    // -------------------------------------------------
                    // DATE OF BIRTH VALIDATION
                    // -------------------------------------------------

                    if (dateOfBirth.isBlank()) {

                        dateOfBirthError =
                            "Please select your date of birth."

                    }


                    // -------------------------------------------------
                    // YEAR VALIDATION
                    // -------------------------------------------------

                    if (year.isBlank()) {

                        yearError =
                            "Please select your year."

                    }


                    // -------------------------------------------------
                    // SHIFT VALIDATION
                    // -------------------------------------------------

                    if (shift.isBlank()) {

                        shiftError =
                            "Please select your shift."

                    }

                    val enrollmentFirstLetter =
                        enrollmentNumber.trim().firstOrNull()?.uppercaseChar()

                    if (
                        enrollmentFirstLetter == 'F' &&
                        shift != "First Shift"
                    ) {
                        shiftError =
                            "Enrollment number starting with F must use First Shift."
                    }

                    if (
                        enrollmentFirstLetter == 'S' &&
                        shift != "Second Shift"
                    ) {
                        shiftError =
                            "Enrollment number starting with S must use Second Shift."
                    }


                    // -------------------------------------------------
                    // CONTINUE ONLY IF EVERYTHING IS VALID
                    // -------------------------------------------------

                    if (
                        fullNameError.isEmpty() &&
                        enrollmentNumberError.isEmpty() &&
                        dateOfBirthError.isEmpty() &&
                        yearError.isEmpty() &&
                        shiftError.isEmpty()
                    ) {

                        navController.navigate("success/cr")
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