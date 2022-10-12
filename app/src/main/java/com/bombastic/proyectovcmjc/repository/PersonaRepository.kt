package com.bombastic.proyectovcmjc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.bombastic.proyectovcmjc.data.local.dao.PersonaDao
import com.bombastic.proyectovcmjc.data.remote.RestDataSource
import com.bombastic.proyectovcmjc.modelo.Persona
import com.bombastic.proyectovcmjc.modelo.User
import com.bombastic.proyectovcmjc.util.TokenUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

interface PersonaRepository {
    suspend fun deletePersona(persona: Persona)
    fun reportarPersonas():LiveData<List<Persona>>
}

class PersonaRepositoryImp @Inject constructor(
    private val dataSource: RestDataSource,
    private val personaDao: PersonaDao
):PersonaRepository {
    override suspend fun deletePersona(persona: Persona){
        CoroutineScope(Dispatchers.IO).launch{
            dataSource.deletePersona(persona.id)
        }
        personaDao.eliminarPersona(persona)
    }
    override fun reportarPersonas(): LiveData<List<Persona>> {
        //delay(3000)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                Log.i("VERRX", "Llega aqui!!!")
                val totek = dataSource.login(User("bombastic@gmail.com", "", "12345678"))
                TokenUtils.TOKEN_CONTENT = "beader " + totek.token
                Log.i("VERX", "Token:" + TokenUtils.TOKEN_CONTENT)

                val data = dataSource.reportarPersona(TokenUtils.TOKEN_CONTENT).body()!!.data
                personaDao.insertarPersona(data)
            }
        } catch (e: Exception) {
            Log.i("ERRORX","ERROR:"+e.message)
        }
        return personaDao.reportarPersonas()
    }
}