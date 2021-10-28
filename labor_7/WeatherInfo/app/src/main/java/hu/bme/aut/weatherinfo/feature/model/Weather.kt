package hu.bme.aut.weatherinfo.feature.model

data class Weather (
    val id: Long = 0,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null
)
