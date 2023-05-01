package com.example.cleanarchitecturedz3.domain

import com.example.cleanarchitecturedz3.data.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HouseUseCase @Inject constructor(var repository: Repository) {
fun invoke(): Flow<House> {
return repository.getHouse()
}
}