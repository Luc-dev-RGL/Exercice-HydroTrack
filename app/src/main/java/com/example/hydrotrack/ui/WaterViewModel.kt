package com.example.hydrotrack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Data class représentant l'état UI immuable du suivi d'hydratation.
 */
data class WaterUiState(
    val currentIntakeMl: Int = 0,
    val goalMl: Int = 2000,
    val streakDays: Int = 1,
    val isGoalReached: Boolean = false
) {
    val progressFraction: Float
        get() = if (goalMl > 0) (currentIntakeMl.toFloat() / goalMl.toFloat()).coerceIn(0f, 1f) else 0f
    
    val remainingMl: Int
        get() = (goalMl - currentIntakeMl).coerceAtLeast(0)
}

/**
 * ViewModel gérant la logique métier et l'état réactif via StateFlow.
 */
class WaterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WaterUiState())
    val uiState: StateFlow<WaterUiState> = _uiState.asStateFlow()

    /**
     * Ajoute une quantité d'eau en millilitres (par défaut 250ml pour un verre).
     */
    fun addIntake(amountMl: Int = 250) {
        _uiState.update { currentState ->
            val newIntake = currentState.currentIntakeMl + amountMl
            currentState.copy(
                currentIntakeMl = newIntake,
                isGoalReached = newIntake >= currentState.goalMl
            )
        }
    }

    /**
     * Réinitialise la consommation du jour à zéro.
     */
    fun resetIntake() {
        _uiState.update { currentState ->
            currentState.copy(
                currentIntakeMl = 0,
                isGoalReached = false
            )
        }
    }

    /**
     * Permet de modifier l'objectif quotidien (par défaut 2000 ml = 2L).
     */
    fun updateGoal(newGoalMl: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                goalMl = newGoalMl,
                isGoalReached = currentState.currentIntakeMl >= newGoalMl
            )
        }
    }
}