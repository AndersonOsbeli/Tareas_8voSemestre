package com.example.apiapi.data

import com.google.gson.annotations.SerializedName

// ==================== PokéAPI Models ====================

data class PokemonResponse(
    val id: Int,
    val name: String,
    val height: Int, // en decímetros
    val weight: Int, // en hectogramos
    val types: List<PokemonTypeSlot>,
    val sprites: PokemonSprites?
) {
    // Retorna la imagen de mayor calidad disponible
    val imageUrl: String?
        get() = sprites?.other?.officialArtwork?.frontDefault ?: sprites?.frontDefault

    val heightInMeters: Double
        get() = height / 10.0

    val weightInKg: Double
        get() = weight / 10.0

    val formattedId: String
        get() = "#" + id.toString().padStart(3, '0')
}

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)

data class PokemonType(
    val name: String
)

data class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault: String?,
    val other: PokemonOtherSprites?
)

data class PokemonOtherSprites(
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork?
)

data class OfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String?
)

// ==================== OpenWeather Models ====================

data class WeatherResponse(
    val id: Long,
    val name: String,
    val sys: SysWeather?,
    val main: MainWeather,
    val weather: List<WeatherDescription>,
    val wind: WindWeather?
) {
    val country: String
        get() = sys?.country ?: ""

    val primaryDescription: String
        get() = weather.firstOrNull()?.description?.replaceFirstChar { it.uppercase() } ?: "Desconocido"

    val iconUrl: String?
        get() = weather.firstOrNull()?.icon?.let { "https://openweathermap.org/img/wn/$it@2x.png" }
}

data class SysWeather(
    val country: String?
)

data class MainWeather(
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("temp_min")
    val tempMin: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    val humidity: Int,
    val pressure: Int
)

data class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class WindWeather(
    val speed: Double
)
