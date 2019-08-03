package com.ucsdextandroid2.android2final

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "games")
@Parcelize
data class Game(

    @ColumnInfo(name = "score") val score: Float?,

    @ColumnInfo(name = "finalScorePercentage") val finalScorePercentage: Float?,

    @PrimaryKey @ColumnInfo(name = "timesStamp") val timesStamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "token") val token: String? = null

//    val score: Int,
//    val finalScorePercentage: Int,
//    val winner: String,
//    val token: String,
//    val timestamp: Long = System.currentTimeMillis() //primarykey

): Parcelable













