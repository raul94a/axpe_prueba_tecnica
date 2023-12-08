import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.annotations.SerializedName
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

/*
data class UserDataResponse(
    @SerializedName("results")
    val results: List<UserData>,

    @SerializedName("info")
    val info: Info
)

data class UserData(
    @SerializedName("gender")
    val gender: String,

    @SerializedName("name")
    val name: UserName,

    @SerializedName("location")
    val location: UserLocation,

    @SerializedName("email")
    val email: String,

    @SerializedName("dob")
    val dob: UserDob,

    @SerializedName("picture")
    val picture: UserPicture
) {
    fun getFullName() : String{
        return "${name.first} ${name.last}"
    }
    fun isFemale(): Boolean {
        return gender == "female";
    }
    fun getGenderInSpanish(): String {
        // Esto debería está localizado para traducirlo pero por el bien de la simplicidad he decidido hacerlo así
        return if(isFemale()) "Mujer" else "Hombre";
    }
}

data class UserName(
    @SerializedName("title")
    val title: String,

    @SerializedName("first")
    val first: String,

    @SerializedName("last")
    val last: String
)

data class UserLocation(
    @SerializedName("street")
    val street: UserStreet,

    @SerializedName("city")
    val city: String,

    @SerializedName("state")
    val state: String,

    @SerializedName("country")
    val country: String,

    @SerializedName("postcode")
    val postcode: String
)

data class UserStreet(
    @SerializedName("number")
    val number: Int,

    @SerializedName("name")
    val name: String
)

data class UserDob(
    @SerializedName("date")
    val date: String,

    @SerializedName("age")
    val age: Int
)

data class UserPicture(
    @SerializedName("large")
    val large: String,

    @SerializedName("medium")
    val medium: String,

    @SerializedName("thumbnail")
    val thumbnail: String
)

data class Info(
    @SerializedName("seed")
    val seed: String,

    @SerializedName("results")
    val results: Int,

    @SerializedName("page")
    val page: Int,

    @SerializedName("version")
    val version: String
)
*/


data class UserDataResponse (
    val results: List<UserData>,
    val info: Info
)

data class Info (
    @SerializedName("seed")
    val seed: String,

    @SerializedName("results")
    val results: Int,

    @SerializedName("page")
    val page: Int,

    @SerializedName("version")
    val version: String
)

data class UserData (
    val gender: String,
    val name: Name,
    val location: Location,
    val email: String,
    val login: Login,
    val dob: Dob,
    val registered: Dob,
    val phone: String,
    val cell: String,
    val id: ID,
    val picture: Picture,
    val nat: String
){
    fun getFullName() : String{
        return "${name.first} ${name.last}"
    }
    fun isFemale(): Boolean {
        return gender == "female";
    }
    fun getGenderInSpanish(): String {
        // Esto debería está localizado para traducirlo pero por el bien de la simplicidad he decidido hacerlo así
        return if(isFemale()) "Mujer" else "Hombre";
    }
}

data class Dob (
    val date: String,
    val age: Int
){

    @SuppressLint("SimpleDateFormat")
    fun getDateFormatted(): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX")
        val outputFormat = SimpleDateFormat("dd/MM/yyyy")

        return try {
            val inputDate: Date = inputFormat.parse(date)
            outputFormat.format(inputDate)
        } catch (e: ParseException) {
            // Handle the exception, e.g., by returning the original string
            date
        }
    }
}

data class ID (
    val name: String,
    val value: Any? = null
)

data class Location (
    val street: Street,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val coordinates: Coordinates,
    val timezone: Timezone
)

data class Coordinates (
    val latitude: String,
    val longitude: String
)

data class Street (
    val number: Int,
    val name: String
)

data class Timezone (
    val offset: String,
    val description: String
)

data class Login (
    val uuid: String,
    val username: String,
    val password: String,
    val salt: String,
    val md5: String,
    val sha1: String,
    val sha256: String
)

data class Name (
    val title: String,
    val first: String,
    val last: String
)

data class Picture (
    val large: String,
    val medium: String,
    val thumbnail: String
)
