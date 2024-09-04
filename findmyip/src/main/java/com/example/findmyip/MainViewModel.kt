package com.example.findmyip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _ipInfo=MutableStateFlow<IPInfo?>(null)
    val ipInfo:StateFlow<IPInfo?> = _ipInfo

    private val _loading=MutableStateFlow(false)
    val loading: StateFlow<Boolean> =_loading

    private val _error=MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val apiService= APIService.create()

    fun fetchIPInfo(){
        viewModelScope.launch {
            _loading.value=true
            try {
                val response=apiService.getInfo()
                _ipInfo.value=response
            }catch (e: Exception){
                _error.value=e.message

            }finally {
                _loading.value=false
            }
        }
    }

}