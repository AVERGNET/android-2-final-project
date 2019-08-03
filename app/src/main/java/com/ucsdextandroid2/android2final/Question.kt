package com.ucsdextandroid2.android2final

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
): Parcelable