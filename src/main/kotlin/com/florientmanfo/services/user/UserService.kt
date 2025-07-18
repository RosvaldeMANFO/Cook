package com.florientmanfo.com.florientmanfo.services.user

import com.florientmanfo.com.florientmanfo.models.user.LoginDTO
import com.florientmanfo.com.florientmanfo.models.user.RegisterDTO
import com.florientmanfo.com.florientmanfo.models.user.UserModel
import com.florientmanfo.com.florientmanfo.models.user.UserRepository

class UserService(
    private val repository: UserRepository,
    private val validation: UserValidationService
) {
    suspend fun login(dto: LoginDTO): Result<String> {
        val result = validation.validateCredential(dto.email, dto.password)
        if(result.isValid.not()){
            throw Exception(result.message)
        }
        return repository.login(dto)
    }

    suspend fun register(dto:RegisterDTO): Result<UserModel> {
        val result = validation.validateCredential(dto.email, dto.password)
        if(result.isValid.not()){
            throw Exception(result.message)
        }
        return repository.register(dto)
    }
}