package com.example.apiapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.apiapi.ui.MainViewModel
import com.example.apiapi.ui.PokemonUiState
import com.example.apiapi.ui.WeatherUiState

// ============================
// Paleta de colores personalizados
// ============================
val PrimaryDark = Color(0xFF0D1117)
val SurfaceDark = Color(0xFF161B22)
val CardDark = Color(0xFF21262D)
val AccentBlue = Color(0xFF58A6FF)
val AccentGreen = Color(0xFF3FB950)
val AccentOrange = Color(0xFFF0883E)
val AccentPurple = Color(0xFFBC8CFF)
val TextPrimary = Color(0xFFE6EDF3)
val TextSecondary = Color(0xFF8B949E)

// Colores por tipo de Pokémon
fun getPokemonTypeColor(typeName: String): Color = when (typeName.lowercase()) {
    "fire" -> Color(0xFFFF6B35)
    "water" -> Color(0xFF4FC3F7)
    "grass" -> Color(0xFF66BB6A)
    "electric" -> Color(0xFFFFD54F)
    "psychic" -> Color(0xFFF48FB1)
    "ice" -> Color(0xFF80DEEA)
    "dragon" -> Color(0xFF7E57C2)
    "dark" -> Color(0xFF546E7A)
    "fairy" -> Color(0xFFF06292)
    "normal" -> Color(0xFFBDBDBD)
    "fighting" -> Color(0xFFEF5350)
    "flying" -> Color(0xFF90CAF9)
    "poison" -> Color(0xFFCE93D8)
    "ground" -> Color(0xFFFFCC80)
    "rock" -> Color(0xFFA1887F)
    "bug" -> Color(0xFF8BC34A)
    "ghost" -> Color(0xFF9575CD)
    "steel" -> Color(0xFF90A4AE)
    else -> Color(0xFF78909C)
}

fun getPokemonTypeNameEs(typeName: String): String = when (typeName.lowercase()) {
    "fire" -> "Fuego"
    "water" -> "Agua"
    "grass" -> "Planta"
    "electric" -> "Eléctrico"
    "psychic" -> "Psíquico"
    "ice" -> "Hielo"
    "dragon" -> "Dragón"
    "dark" -> "Siniestro"
    "fairy" -> "Hada"
    "normal" -> "Normal"
    "fighting" -> "Lucha"
    "flying" -> "Volador"
    "poison" -> "Veneno"
    "ground" -> "Tierra"
    "rock" -> "Roca"
    "bug" -> "Bicho"
    "ghost" -> "Fantasma"
    "steel" -> "Acero"
    else -> typeName.replaceFirstChar { it.uppercase() }
}

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = AccentBlue,
                    background = PrimaryDark,
                    surface = SurfaceDark,
                    onBackground = TextPrimary,
                    onSurface = TextPrimary
                )
            ) {
                ApiAppScreen(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiAppScreen(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Consumo de APIs",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "OpenWeather + PokéAPI",
                            fontSize = 12.sp,
                            color = TextSecondary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = SurfaceDark
                )
            )
        },
        containerColor = PrimaryDark
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // ===== SECCIÓN CLIMA =====
            WeatherSection(viewModel)

            // ===== SECCIÓN POKÉMON =====
            PokemonSection(viewModel)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// ============================================================
// SECCIÓN CLIMA
// ============================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherSection(viewModel: MainViewModel) {
    var cityInput by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val quickCities = listOf("Guatemala", "Madrid", "México", "Miami", "Tokyo")

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Encabezado
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(AccentBlue.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🌤", fontSize = 20.sp)
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Clima", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
                    Text("OpenWeather API", fontSize = 12.sp, color = TextSecondary)
                }
            }

            // Chips de ciudades rápidas
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                quickCities.take(3).forEach { city ->
                    FilterChip(
                        selected = false,
                        onClick = {
                            cityInput = city
                            viewModel.fetchWeather(city)
                            focusManager.clearFocus()
                        },
                        label = { Text(city, fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = CardDark,
                            labelColor = TextSecondary
                        )
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                quickCities.drop(3).forEach { city ->
                    FilterChip(
                        selected = false,
                        onClick = {
                            cityInput = city
                            viewModel.fetchWeather(city)
                            focusManager.clearFocus()
                        },
                        label = { Text(city, fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = CardDark,
                            labelColor = TextSecondary
                        )
                    )
                }
            }

            // Campo de búsqueda
            OutlinedTextField(
                value = cityInput,
                onValueChange = { cityInput = it },
                placeholder = { Text("Buscar ciudad...", color = TextSecondary, fontSize = 14.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    if (cityInput.isNotBlank()) {
                        viewModel.fetchWeather(cityInput.trim())
                        focusManager.clearFocus()
                    }
                }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AccentBlue,
                    unfocusedBorderColor = CardDark,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = AccentBlue
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        if (cityInput.isNotBlank()) {
                            viewModel.fetchWeather(cityInput.trim())
                            focusManager.clearFocus()
                        }
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar", tint = AccentBlue)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // Botón principal
            Button(
                onClick = {
                    val city = cityInput.ifBlank { "Guatemala" }
                    viewModel.fetchWeather(city)
                    focusManager.clearFocus()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AccentBlue
                )
            ) {
                Text("Ver Clima", fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 4.dp))
            }

            // Resultado
            AnimatedContent(
                targetState = viewModel.weatherState,
                transitionSpec = {
                    (fadeIn(tween(300)) + slideInVertically(tween(300)) { it / 2 })
                        .togetherWith(fadeOut(tween(150)))
                },
                label = "weather_content"
            ) { state ->
                when (state) {
                    is WeatherUiState.Idle -> {
                        /* nada */
                    }
                    is WeatherUiState.Loading -> {
                        Box(Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = AccentBlue)
                        }
                    }
                    is WeatherUiState.Error -> {
                        ErrorCard(state.message)
                    }
                    is WeatherUiState.Success -> {
                        WeatherResultCard(state.data)
                    }
                }
            }
        }
    }
}

@Composable
fun WeatherResultCard(data: com.example.apiapi.data.WeatherResponse) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF1565C0).copy(alpha = 0.6f),
                            Color(0xFF0288D1).copy(alpha = 0.4f)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                // Ciudad y país
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = data.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            color = TextPrimary
                        )
                        if (data.country.isNotEmpty()) {
                            Text(
                                text = data.country,
                                fontSize = 13.sp,
                                color = TextSecondary
                            )
                        }
                    }
                    // Icono del clima
                    if (data.iconUrl != null) {
                        AsyncImage(
                            model = data.iconUrl,
                            contentDescription = "Ícono del clima",
                            modifier = Modifier.size(52.dp)
                        )
                    }
                }

                // Temperatura grande
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "${"%.1f".format(data.main.temp)}°",
                        fontSize = 52.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = TextPrimary,
                        lineHeight = 52.sp
                    )
                    Text(
                        text = "C",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary,
                        modifier = Modifier.padding(bottom = 8.dp, start = 2.dp)
                    )
                }

                // Descripción
                Text(
                    text = data.primaryDescription,
                    fontSize = 14.sp,
                    color = TextSecondary
                )

                // Métricas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    WeatherMetricChip("🌡 Sens.", "${"%.1f".format(data.main.feelsLike)}°C")
                    WeatherMetricChip("💧 Humedad", "${data.main.humidity}%")
                    WeatherMetricChip("💨 Viento", "${"%.1f".format(data.wind?.speed ?: 0.0)} m/s")
                }

                // Temp mín/máx
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    WeatherMetricChip("⬇ Mín", "${"%.1f".format(data.main.tempMin)}°C", Modifier.weight(1f))
                    WeatherMetricChip("⬆ Máx", "${"%.1f".format(data.main.tempMax)}°C", Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun WeatherMetricChip(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(CardDark.copy(alpha = 0.7f))
            .padding(horizontal = 10.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, fontSize = 11.sp, color = TextSecondary, textAlign = TextAlign.Center)
        Text(value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
    }
}

// ============================================================
// SECCIÓN POKÉMON
// ============================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonSection(viewModel: MainViewModel) {
    var pokemonInput by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    val quickPokemon = listOf("Pikachu", "Charizard", "Mewtwo", "Eevee", "Gengar")

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = SurfaceDark),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Encabezado
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(AccentYellow.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("⚡", fontSize = 20.sp)
                }
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Pokémon", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = TextPrimary)
                    Text("PokéAPI", fontSize = 12.sp, color = TextSecondary)
                }
            }

            // Chips de Pokémon rápidos
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                quickPokemon.take(3).forEach { poke ->
                    FilterChip(
                        selected = false,
                        onClick = {
                            pokemonInput = poke
                            viewModel.fetchPokemon(poke)
                            focusManager.clearFocus()
                        },
                        label = { Text(poke, fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = CardDark,
                            labelColor = TextSecondary
                        )
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                quickPokemon.drop(3).forEach { poke ->
                    FilterChip(
                        selected = false,
                        onClick = {
                            pokemonInput = poke
                            viewModel.fetchPokemon(poke)
                            focusManager.clearFocus()
                        },
                        label = { Text(poke, fontSize = 11.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            containerColor = CardDark,
                            labelColor = TextSecondary
                        )
                    )
                }
            }

            // Campo de búsqueda
            OutlinedTextField(
                value = pokemonInput,
                onValueChange = { pokemonInput = it },
                placeholder = { Text("Nombre o número...", color = TextSecondary, fontSize = 14.sp) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    if (pokemonInput.isNotBlank()) {
                        viewModel.fetchPokemon(pokemonInput.trim())
                        focusManager.clearFocus()
                    }
                }),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AccentYellow,
                    unfocusedBorderColor = CardDark,
                    focusedTextColor = TextPrimary,
                    unfocusedTextColor = TextPrimary,
                    cursorColor = AccentYellow
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        if (pokemonInput.isNotBlank()) {
                            viewModel.fetchPokemon(pokemonInput.trim())
                            focusManager.clearFocus()
                        }
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar", tint = AccentYellow)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            // Botones: buscar y aleatorio
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        val poke = pokemonInput.ifBlank { "pikachu" }
                        viewModel.fetchPokemon(poke)
                        focusManager.clearFocus()
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentYellow)
                ) {
                    Text(
                        "Ver Pokémon",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A1A),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                OutlinedButton(
                    onClick = { viewModel.fetchRandomPokemon() },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = AccentYellow),
                    border = androidx.compose.foundation.BorderStroke(1.dp, AccentYellow)
                ) {
                    Text(
                        "🎲 Aleatorio",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            // Resultado
            AnimatedContent(
                targetState = viewModel.pokemonState,
                transitionSpec = {
                    (fadeIn(tween(300)) + slideInVertically(tween(300)) { it / 2 })
                        .togetherWith(fadeOut(tween(150)))
                },
                label = "pokemon_content"
            ) { state ->
                when (state) {
                    is PokemonUiState.Idle -> { /* nada */ }
                    is PokemonUiState.Loading -> {
                        Box(Modifier.fillMaxWidth().padding(16.dp), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator(color = AccentYellow)
                        }
                    }
                    is PokemonUiState.Error -> ErrorCard(state.message)
                    is PokemonUiState.Success -> PokemonResultCard(state.data)
                }
            }
        }
    }
}

val AccentYellow = Color(0xFFFFD700)

@Composable
fun PokemonResultCard(data: com.example.apiapi.data.PokemonResponse) {
    val dominantTypeColor = data.types.firstOrNull()?.type?.name
        ?.let { getPokemonTypeColor(it) } ?: AccentPurple

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            dominantTypeColor.copy(alpha = 0.3f),
                            dominantTypeColor.copy(alpha = 0.1f)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                // ID + Nombre + Imagen
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = data.formattedId,
                            fontSize = 12.sp,
                            color = dominantTypeColor,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = data.name.replaceFirstChar { it.uppercase() },
                            fontSize = 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = TextPrimary
                        )
                        Spacer(Modifier.height(8.dp))
                        // Tipos
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            data.types.forEach { slot ->
                                val typeColor = getPokemonTypeColor(slot.type.name)
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(typeColor.copy(alpha = 0.8f))
                                        .padding(horizontal = 12.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = getPokemonTypeNameEs(slot.type.name),
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }

                    // Imagen oficial
                    if (data.imageUrl != null) {
                        AsyncImage(
                            model = data.imageUrl,
                            contentDescription = data.name,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(dominantTypeColor.copy(alpha = 0.15f)),
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                // Estadísticas físicas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PokemonStatChip("⚖ Peso", "${"%.1f".format(data.weightInKg)} kg")
                    PokemonStatChip("📏 Altura", "${"%.1f".format(data.heightInMeters)} m")
                }
            }
        }
    }
}

@Composable
fun PokemonStatChip(label: String, value: String) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(CardDark.copy(alpha = 0.7f))
            .padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, fontSize = 11.sp, color = TextSecondary)
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
    }
}

@Composable
fun ErrorCard(message: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3A0A0A)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("❌", fontSize = 18.sp)
            Text(
                text = message,
                fontSize = 13.sp,
                color = Color(0xFFFF8A80),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
