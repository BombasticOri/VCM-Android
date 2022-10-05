package com.bombastic.proyectovcmjc.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bombastic.proyectovcmjc.data.local.dao.PersonaDao
import com.bombastic.proyectovcmjc.modelo.Persona

@Database(entities = [Persona::class], version = 1)
abstract class DbDataSource:RoomDatabase() {
    abstract fun personaDao():PersonaDao
}