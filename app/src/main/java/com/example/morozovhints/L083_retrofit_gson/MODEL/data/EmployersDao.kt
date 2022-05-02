package com.example.morozovhints.L083_retrofit_gson.MODEL.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.morozovhints.L083_retrofit_gson.MODEL.pojo.Employer

@Dao
interface EmployersDao {
    @Query("SELECT * FROM Employers")
    fun getAllEmployers(): LiveData<MutableList<Employer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEmployer(employer: List<Employer>?)

    @Query("DELETE FROM Employers")
    fun deleteAllEmployers()
}