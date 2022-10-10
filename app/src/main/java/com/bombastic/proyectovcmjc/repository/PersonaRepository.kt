package com.bombastic.proyectovcmjc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.bombastic.proyectovcmjc.data.local.dao.PersonaDao
import com.bombastic.proyectovcmjc.data.remote.RestDataSource
import com.bombastic.proyectovcmjc.modelo.Persona
import com.bombastic.proyectovcmjc.modelo.User
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
        val token = dataSource.login(User("asdasd@gmail.com","","123456"))
        TokenUtils.TOKEN_CONTENT = token.body()?.token_type+" "+token.body()?.acces_token
        Log.i("VERX","Token:"+TokenUtils.TOKEN_CONTENT)

        val data = dataSource.reportarPersona(TokenUtils.TOKEN_CONTENT).body()!!.data
        personaDao.insertarPersona(data)
        return personaDao.reportarPersonas()
    }
}