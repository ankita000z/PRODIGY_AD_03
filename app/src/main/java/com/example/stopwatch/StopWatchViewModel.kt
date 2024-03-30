package com.example.stopwatch

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StopWatchViewModel: ViewModel() {

    var minutes = mutableStateOf(0)
        private set
    var seconds = mutableStateOf(0)
        private set
    var milliseconds = mutableStateOf(0)
        private set
    private var isCounting = false

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while(true) {
                if (isCounting) {
                    if (milliseconds.value < 100) {
                        milliseconds.value++
                    } else {
                        if (seconds.value < 60) {
                            seconds.value++
                            milliseconds.value = 0
                        } else {
                            minutes.value++
                            seconds.value = 0
                            milliseconds.value = 0
                        }
                    }
                }
                delay(7)
            }
        }
    }

    fun onEvent(event: StopWatchEvent){
        when(event){
            StopWatchEvent.onReset -> {
                isCounting = false
                minutes.value = 0
                seconds.value = 0
                milliseconds.value = 0
            }
            StopWatchEvent.onStart -> {
                isCounting = true
            }
            StopWatchEvent.onStop -> {
                isCounting = false
            }
        }
    }

}