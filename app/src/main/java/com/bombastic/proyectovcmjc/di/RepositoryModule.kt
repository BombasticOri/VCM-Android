package com.bombastic.proyectovcmjc.di

import com.bombastic.proyectovcmjc.repository.PersonaRepository
import com.bombastic.proyectovcmjc.repository.PersonaRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun personaRepository(perRepos: PersonaRepositoryImp): PersonaRepository
}