package com.example.studentapp2.ui.theme // TODO: replace with your actual package name

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// The reference uses a rounded, friendly sans-serif (looks like Poppins/Nunito) for the
// logo and headings. Using FontFamily.Default (Roboto) + bold weights gets ~90% of the way
// there with zero setup. If you want an exact match later, drop a Poppins .ttf into
// res/font/ and swap FontFamily.Default for FontFamily(Font(R.font.poppins_bold)) etc.
val MyGpmFontFamily = FontFamily.Default

val MyGpmTypography = Typography(
    // "MyGPM" logo wordmark
    displaySmall = TextStyle(
        fontFamily = MyGpmFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 40.sp,
        letterSpacing = 0.sp
    ),
    // "Student Login"
    headlineMedium = TextStyle(
        fontFamily = MyGpmFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    // "Welcome Back!"
    titleLarge = TextStyle(
        fontFamily = MyGpmFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    // "Login to your account" card header
    titleMedium = TextStyle(
        fontFamily = MyGpmFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp
    ),
    // Body copy / subtext
    bodyLarge = TextStyle(
        fontFamily = MyGpmFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = MyGpmFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    // Field labels, hints, "Secure & Private"
    labelLarge = TextStyle(
        fontFamily = MyGpmFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MyGpmFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)