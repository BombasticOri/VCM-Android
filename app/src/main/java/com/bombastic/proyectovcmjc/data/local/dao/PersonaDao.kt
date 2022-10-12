package com.bombastic.proyectovcmjc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bombastic.proyectovcmjc.modelo.Persona
import java.net.IDN

@Dao
interface PersonaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPersona(persona: Persona)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarPersona(persona: List<Persona>)

    @Update
    suspend fun actualizarPersona(persona: Persona)

    @Delete
    suspend fun eliminarPersona(persona: Persona)

    @Query("select * from persona")
    fun reportarPersonas():LiveData<List<Persona>>

    @Query("select * from persona where id=:id")
    fun buscarPersona(id:Int):LiveData<Persona>
}