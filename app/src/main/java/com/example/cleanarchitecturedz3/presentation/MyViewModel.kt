package com.example.cleanarchitecturedz3.presentation



import android.os.Message
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturedz3.domain.House
import com.example.cleanarchitecturedz3.domain.HouseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(var houseUseCase: HouseUseCase): ViewModel(){
    var myMutable = mutableStateListOf<House>()
    var myMutableState = mutableStateOf<ViewModelState>(ViewModelState.Loading)
    fun getHouses(){
        myMutableState.value = ViewModelState.Loading
        viewModelScope.launch {
            houseUseCase.invoke( )
                .catch{myMutableState.value = ViewModelState.Error(it.message?:"Error")  }
                .collect{
                myMutable.add(it)
                myMutableState.value = ViewModelState.Sucsess(myMutable)
            }
        }

    }
}
sealed class ViewModelState{
    object Loading:ViewModelState()
    class Sucsess(var list: List<House>):ViewModelState()
    class Error(var text: String):ViewModelState()
}