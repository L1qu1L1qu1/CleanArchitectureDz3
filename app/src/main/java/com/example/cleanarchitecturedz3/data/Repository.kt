package com.example.cleanarchitecturedz3.data

import com.example.cleanarchitecturedz3.domain.House
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor() {
    var list = listOf(House("room 1"), House("room 2"), House("room 3"), House("room 4"), House("room 5"), House("room 6"))
    fun getHouse() = flow{
        for (i in list){
            delay(800)
            emit(i)
        }
    }
}