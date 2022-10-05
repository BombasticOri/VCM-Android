package com.bombastic.proyectovcmjc.repository

import androidx.lifecycle.LiveData
import com.bombastic.proyectovcmjc.data.local.dao.PersonaDao
import com.bombastic.proyectovcmjc.data.remote.RestDataSource
import com.bombastic.proyectovcmjc.modelo.Persona
import com.bombastic.proyectovcmjc.util.TokenUtils
import kotlinx.coroutines.delay
import javax.inject.Inject

interface PersonaRepository {
    suspend fun deletePersona(persona: Persona)
    fun reportarPersonas():LiveData<List<Persona>>
}

class PersonaRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val personaDao: PersonaDao
):PersonaRepository{
    override suspend fun deletePersona(persona: Persona)=personaDao.eliminarPersona(persona)

    override fun reportarPersonas(): LiveData<List<Persona>> {
        //delay(3000)
        val data = dataSource.reportarPersona(TokenUtils.TOKEN_CONTENT).body()!!.data
        personaDao.insertarPersona(data)
        return personaDao.reportarPersonas()
    }
}