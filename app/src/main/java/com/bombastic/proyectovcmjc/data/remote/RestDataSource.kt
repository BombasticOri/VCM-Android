package com.bombastic.proyectovcmjc.data.remote

import com.bombastic.proyectovcmjc.modelo.MsgModelResponse
import com.bombastic.proyectovcmjc.modelo.Persona
import com.bombastic.proyectovcmjc.modelo.User
import com.bombastic.proyectovcmjc.modelo.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface RestDataSource {
    @GET("/apiv1/persona")
    fun reportarPersona(@Header("Authorization") token:String):Response<MsgModelResponse>

    @GET("/apiv1/persona/{id}")
    suspend fun getPersonaId(@Header("Authorization") token:String, @Query("id") id:Int):Response<MsgModelResponse>

    @DELETE("/apiv1/persona")
    suspend fun deletePersona(@Path("id") id:Int):Response<MsgModelResponse>

    @PATCH("/apiv1/persona")
    suspend fun actualizarPersona(@Body persona: Persona):Response<MsgModelResponse>

    @POST("/apiv1/persona")
    suspend fun insertarPersona(@Body persona: Persona):Response<MsgModelResponse>

    @GET("/apiv1/user/login")
    suspend fun login(@Body user: User):Response<UserResponse>

}