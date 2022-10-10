package com.bombastic.proyectovcmjc.modelo

data class User(
    var email:String,
    var username:String,
    var password:String
)

data class UserResponse(
    var message:String,
    var email: String,
    var username: String,
    var password: String
)

data class  TokenResponse(
    var message: String,
    var token: String
)