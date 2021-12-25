package com.example.jp1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jp1.model.Games
import com.example.jp1.repository.GamesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val repository: GamesRepo
): ViewModel(){

    private val _games = MutableLiveData<Games>()

    fun getGamesById(id: String): LiveData<Games> {
        viewModelScope.launch(Dispatchers.IO) {
            val games = repository.gamesById(id.toInt())
            _games.postValue(games)
        }
        return _games
    }

}