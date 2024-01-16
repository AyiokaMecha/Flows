package com.androidpractice.flowdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class DemoViewModel : ViewModel() {
    val myFlow: Flow<Int> = flow {
        //producer block which is a coroutine suspend block containing the producer
        for (i in 0..90) {
            emit(i)
            delay(200)
        }
    }

    val newFlow = myFlow.map {
        "Current value = $it"
    }

    val _sharedFlow = MutableSharedFlow<Int>(
        replay = 10,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val sharedFlow = _sharedFlow.asSharedFlow()

    val _stateFlow = MutableStateFlow(0)
    val stateFLow = _stateFlow.asStateFlow()

}