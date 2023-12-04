package com.nsteuerberg.simondice

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MyViewModel: ViewModel(){

    fun generateRandomNumber(max:Int): Int {
        return (0..max-1).random()
    }


    fun initGame(){
        resetRound()
        resetUserSecuence()
        resetBotSecuence()
        Data.state = State.START
    }

    fun resetRound(){
        Data.round.value = 0
    }


    fun resetUserSecuence(){
        Data.UserSecuence.clear()
    }


    fun resetBotSecuence(){
        Data.botSecuence.clear()
    }


    fun increaseShowBotSecuence(){
        Data.state = State.SEQUENCE
        Log.d("ESTADO",Data.state.toString())
        addBotSecuence()
        showSecuence()
    }


    fun addBotSecuence(){
        Data.botSecuence.add(generateRandomNumber(4))
    }

    fun showSecuence() = runBlocking {
        showBotSequence()
    }


    fun darkenColor(color: Color, factor: Float): Color {
        val r = (color.red * (1 - factor)).coerceIn(0f, 1f)
        val g = (color.green * (1 - factor)).coerceIn(0f, 1f)
        val b = (color.blue * (1 - factor)).coerceIn(0f, 1f)
        return Color(r, g, b, color.alpha)
    }


    fun lightenColor(color: Color, factor: Float): Color {
        val r = (color.red * 255 * (1 - factor) / 255 + factor).coerceIn(0f, 1f)
        val g = (color.green * 255 * (1 - factor) / 255 + factor).coerceIn(0f, 1f)
        val b = (color.blue * 255 * (1 - factor) / 255 + factor).coerceIn(0f, 1f)
        return Color(r, g, b, color.alpha)
    }


    fun showBotSequence(){
        viewModelScope.launch {
            // we need to do the coroutines in the _viewModelScope.launch_
            for (colorIndex in Data.botSecuence) {
                Data.colorFlag = Data.colors[colorIndex].value
                Data.colorsMyColors[colorIndex].color.value = darkenColor(Data.colorFlag, 0.5f)
                Data.sounds[colorIndex].start()
                delay(500L)
                Data.colorsMyColors[colorIndex].color.value = Data.colorFlag
                delay(250L)
            }
            Data.state = State.WAITING
            Log.d("ESTADO",Data.state.toString())
        }
        Log.d("ESTADO",Data.botSecuence.toString())
    }
    fun showButtonPressed(color: MutableState<Color>){
        viewModelScope.launch {
            Data.state = State.INPUT
            Data.colorUserFlag = color.value
            color.value = darkenColor(Data.colorUserFlag, 0.5f)
            delay(250L)
            color.value = Data.colorUserFlag
            Data.state = State.WAITING
        }
    }

    /**
     * Increase the User secuence
     * @param color color introduced by the user
     */
    fun increaseUserSecuence(color: Int) {
        Data.state = State.INPUT
        Log.d("ESTADO",Data.state.toString())
        Data.UserSecuence.add(color)
        Data.state = State.WAITING
        Log.d("ESTADO",Data.state.toString())
    }



    fun checkSecuence(){
        Data.state = State.CHECKING
        Log.d("ESTADO",Data.state.toString())
        if (Data.UserSecuence == Data.botSecuence){
            Data.round.value ++
            if (Data.round.value > Data.record.value){
                Data.record.value = Data.round.value
            }
            Data.UserSecuence.clear()
            increaseShowBotSecuence()
        } else{
            Data.state = State.FINISH
            Data.playStatus.value = "Start"
            initGame()
            Log.d("ESTADO",Data.state.toString())
        }
    }

    fun getRound(): Int {
        return Data.round.value
    }


    fun getRecord(): Int {
        return Data.record.value
    }


    fun changePlayStatus(){
        if (Data.playStatus.value == "Start"){
            Data.playStatus.value = "Reset"
            Data.round.value ++
            increaseShowBotSecuence()
        } else{
            Data.playStatus.value = "Start"
            initGame()
        }

    }


    fun getPlayStatus(): String {
        return Data.playStatus.value
    }
}