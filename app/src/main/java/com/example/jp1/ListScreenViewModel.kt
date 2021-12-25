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
class ListScreenViewModel @Inject constructor(
    private val repository: GamesRepo
): ViewModel(){

    private val _games = MutableLiveData<List<Games>>()
    private val _loot = MutableLiveData<List<Games>>()

    fun getGames(): LiveData<List<Games>>{
        viewModelScope.launch(Dispatchers.IO) {
            val games = repository.getAllGiveaways()
            _games.postValue(games)
        }
        return _games
    }
    fun getOnlyGames(): LiveData<List<Games>>{
        viewModelScope.launch(Dispatchers.IO) {
            val games = repository.freeGames()
            _games.postValue(games)
        }
        return _games
    }
    fun getFreeLoot(): LiveData<List<Games>>{
        viewModelScope.launch(Dispatchers.IO) {
            val games = repository.getFreeLoot()
            _loot.postValue(games)
        }
        return _loot
    }


}