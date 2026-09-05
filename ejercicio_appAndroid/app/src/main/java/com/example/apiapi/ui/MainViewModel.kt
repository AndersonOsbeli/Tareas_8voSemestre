package com.example.apiapi.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiapi.data.PokemonResponse
import com.example.apiapi.data.RetrofitClient
import com.example.apiapi.data.WeatherResponse
import kotlinx.coroutines.launch

sealed class WeatherUiState {
    object Idle : WeatherUiState()
    object Loading : WeatherUiState()
    data class Success(val data: WeatherResponse) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}

sealed class PokemonUiState {
    object Idle : PokemonUiState()
    object Loading : PokemonUiState()
    data class Success(val data: PokemonResponse) : PokemonUiState()
    data class Error(val message: String) : PokemonUiState()
}

class MainViewModel : ViewModel() {

    var weatherState: WeatherUiState by mutableStateOf(WeatherUiState.Idle)
        private set

    var pokemonState: PokemonUiState by mutableStateOf(PokemonUiState.Idle)
        private set

    // API Key de OpenWeatherMap
    private val WEATHER_API_KEY = "e5e7725c75b79eb97a21cb56da237e2c"

    fun fetchWeather(city: String = "Guatemala") {
        viewModelScope.launch {
            weatherState = WeatherUiState.Loading
            try {
                val response = RetrofitClient.weatherApi.getWeather(city, WEATHER_API_KEY)
                weatherState = WeatherUiState.Success(response)
            } catch (e: Exception) {
                weatherState = WeatherUiState.Error(
                    e.message ?: "Error de conexión. Verifica tu internet."
                )
            }
        }
    }

    fun fetchPokemon(nameOrId: String = "pikachu") {
        viewModelScope.launch {
            pokemonState = PokemonUiState.Loading
            try {
                val response = RetrofitClient.pokeApi.getPokemon(nameOrId.lowercase().trim())
                pokemonState = PokemonUiState.Success(response)
            } catch (e: Exception) {
                pokemonState = PokemonUiState.Error(
                    e.message ?: "Error al obtener Pokémon."
                )
            }
        }
    }

    fun fetchRandomPokemon() {
        val randomId = (1..1025).random()
        fetchPokemon(randomId.toString())
    }
}
