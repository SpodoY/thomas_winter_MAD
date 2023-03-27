package com.example.movieappmad23.ui

import androidx.lifecycle.ViewModel
import com.example.movieappmad23.data.FavoriteUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState : StateFlow<FavoriteUiState> = _uiState.asStateFlow()


}