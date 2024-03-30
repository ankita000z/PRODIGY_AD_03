package com.example.stopwatch

sealed interface StopWatchEvent{
    object onStart: StopWatchEvent
    object onStop: StopWatchEvent
    object onReset: StopWatchEvent
}