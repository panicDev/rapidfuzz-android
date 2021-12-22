package id.panicdev.autocompleteplaground.data


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class AcData(
    @SerializedName("abbreviations")
    val abbreviations: List<String> = listOf(),
    @SerializedName("city")
    val city: String = "",
    @SerializedName("default_cities")
    val defaultCities: List<String> = listOf(),
    @SerializedName("name")
    val name: List<String> = listOf(),
    @SerializedName("places")
    val places: List<String> = listOf(),
    @SerializedName("roadmap_cities")
    val roadmapCities: List<RoadmapCity> = listOf()
) {
    @Keep
    data class RoadmapCity(
        @SerializedName("abbreviation")
        val abbreviation: String = "",
        @SerializedName("city")
        val city: String = "",
        @SerializedName("favourite_cuisine")
        val favouriteCuisine: List<String> = listOf(),
        @SerializedName("favourite_location")
        val favouriteLocation: List<String> = listOf(),
        @SerializedName("latitude")
        val latitude: String = "",
        @SerializedName("longitude")
        val longitude: String = "",
        @SerializedName("preferred_cuisine")
        val preferredCuisine: List<String> = listOf(),
        @SerializedName("preferred_location")
        val preferredLocation: List<String> = listOf(),
        @SerializedName("value")
        val value: String = ""
    )
}