package hu.bme.aut.weatherinfo.feature.model

data class Sys (
    var type: Int = 0,
    var id: Int = 0,
    var country: String? = null,
    var sunrise: Int = 0,
    var sunset: Int = 0
)
