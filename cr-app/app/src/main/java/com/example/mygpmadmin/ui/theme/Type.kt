package com.example.mygpmadmin.ui.theme

import com.example.mygpmadmin.R

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// =====================================================
// MYGPM ADMIN - FONT FAMILIES
// =====================================================

// -----------------------------------------------------
// CORMORANT GARAMOND
// -----------------------------------------------------
// Used for:
// • MyGPM branding
// • Main page headings
// • Important section titles
//
// NOT used for normal teacher-facing text.
// -----------------------------------------------------

val CormorantGaramond = FontFamily(

    Font(
        R.font.cormorant_garamond_regular,
        FontWeight.Normal
    ),

    Font(
        R.font.cormorant_garamond_semibold,
        FontWeight.SemiBold
    ),

    Font(
        R.font.cormorant_garamond_bold,
        FontWeight.Bold
    )
)


// -----------------------------------------------------
// INTER
// -----------------------------------------------------
// Main functional font of the application.
//
// Used for:
// • Navigation
// • Buttons
// • Forms
// • Attendance
// • Tables
// • Student information
// • Assignments
// • Notifications
// • General text
//
// Inter is intentionally used for most of the app
// because teachers need maximum readability.
// -----------------------------------------------------

val Inter = FontFamily(

    Font(
        R.font.inter_regular,
        FontWeight.Normal
    ),

    Font(
        R.font.inter_medium,
        FontWeight.Medium
    ),

    Font(
        R.font.inter_semibold,
        FontWeight.SemiBold
    ),

    Font(
        R.font.inter_bold,
        FontWeight.Bold
    )
)


// =====================================================
// MYGPM ADMIN - TYPOGRAPHY SYSTEM
// =====================================================

val MyGPMTypography = Typography(

    // -------------------------------------------------
    // LARGE DISPLAY TITLE
    // Example:
    // "MyGPM Admin"
    // -------------------------------------------------

    displayLarge = TextStyle(
        fontFamily = CormorantGaramond,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 38.sp
    ),


    // -------------------------------------------------
    // MAIN PAGE HEADING
    // Example:
    // "Attendance Analytics"
    // -------------------------------------------------

    headlineLarge = TextStyle(
        fontFamily = CormorantGaramond,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp
    ),


    // -------------------------------------------------
    // SECTION HEADING
    // Example:
    // "Today's Attendance"
    // -------------------------------------------------

    headlineMedium = TextStyle(
        fontFamily = CormorantGaramond,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),


    // -------------------------------------------------
    // SMALL SECTION HEADING
    // -------------------------------------------------

    headlineSmall = TextStyle(
        fontFamily = CormorantGaramond,
        fontWeight = FontWeight.SemiBold,
        fontSize = 19.sp,
        lineHeight = 24.sp
    ),


    // -------------------------------------------------
    // TITLES / NAVIGATION
    // -------------------------------------------------

    titleMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),


    // -------------------------------------------------
    // NORMAL LARGE TEXT
    // -------------------------------------------------

    bodyLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),


    // -------------------------------------------------
    // NORMAL APPLICATION TEXT
    // -------------------------------------------------

    bodyMedium = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),


    // -------------------------------------------------
    // SMALL SUPPORTING TEXT
    // -------------------------------------------------

    bodySmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),


    // -------------------------------------------------
    // BUTTON TEXT
    // -------------------------------------------------

    labelLarge = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),


    // -------------------------------------------------
    // SMALL LABELS / CAPTIONS
    // -------------------------------------------------

    labelSmall = TextStyle(
        fontFamily = Inter,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp
    )
)