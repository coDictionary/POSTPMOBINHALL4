package com.farhan164.postpmobinhall4.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WargaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(warga: Warga)

    @Query("SELECT * FROM warga ORDER BY id ASC")
    fun getAllWarga(): LiveData<List<Warga>>

    @Query("DELETE FROM warga")
    fun deleteAll()
}