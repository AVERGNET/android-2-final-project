package com.ucsdextandroid2.android2final

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface GamesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: Game)

    @Delete
    fun deleteGame(game: Game)

    @Query("SELECT * FROM games ORDER BY timesStamp DESC")
    fun getAllGamesLiveData(): LiveData<List<Game>>


}