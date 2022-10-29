package com.example.kotlintopmovies2.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlintopmovies2.di.ModuleRoom.MOVIES_TABLE

@Entity(tableName = MOVIES_TABLE)
data class MoviesEntity (
    @PrimaryKey
    var id :Int = 0,
    var poster : String = "",
    var title : String = "",
    var rate : String = "",
    var country : String = "",
    var year : String = ""
)
