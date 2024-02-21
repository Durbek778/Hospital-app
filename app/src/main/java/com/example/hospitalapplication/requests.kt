package com.example.hospitalapplication

import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: Int = 0,
    val name: String = "",
    val password: String = "",
    val email: String = "",
    val phone: String = "",
    val company: String = "",
    val department: String = "",
    val userType: String = "",
    val isDeleted: Boolean = false
)


@Serializable
data class Art(
    val artid: Int = 0,
    val category: Int = 0,
    val name: String = "",
    val description: String = "",
)

@Serializable
data class Game(
    val gameid: Int = 0,
    val gamesrc: String = "",
    val gameImgSrc: String = "",
    val game_name: String = "",
)

@Serializable
data class Media(
    val mediaid: Int = 0,
    val mediatitle: String = "",
    val mediadescription: String = "",
    val mediasrc: String = "",
)

@Serializable
data class Patient(
    val patientid: Int = 0,
    val name: String = "",
    val bloodpressure: Int = 0,
    val bloodglucose: Int = 0,
    val bodytemperature: Int = 0,
    val heartrate: Int = 0,
    val itemstobeinpected: String = "",
    val medicationonhand: String = "",
    val recentdiagnosticfindings: String = "",
)

val handler = CoroutineExceptionHandler { context, throwable ->
    Log.d("LifeScycleScope", "$throwable")
}

class Requests {
    lateinit var supabase: SupabaseClient;
    lateinit var games: Game;

    init {
        supabase = getClient()
    }

    private fun getClient(): SupabaseClient {
        val supabase = createSupabaseClient(
            supabaseUrl = "https://ntkxrjryfffbtpbtzevx.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im50a3hyanJ5ZmZmYnRwYnR6ZXZ4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MDY1OTE1MDAsImV4cCI6MjAyMjE2NzUwMH0.N99GQwoQ4FZg4KhKx9JxZ3qdIFU_gWi-4brpyiV_TJM",
        ) {
            install(Auth)
            install(Postgrest)
        }

        return supabase

    }

    public suspend fun getUsers(): List<User> {
        val supabase = getClient()
        val supabaseResponse = supabase.postgrest.from("user").select(Columns.ALL)
        val data = supabaseResponse.decodeList<User>()
        Log.e("supabase-user", data.toString())
        return data
    }

    public suspend fun getArts(): List<Art> {
        val supabase = getClient()
        val supabaseResponse = supabase.postgrest.from("art").select(Columns.ALL)
        val data = supabaseResponse.decodeList<Art>()
        Log.e("supabase", data.toString())
        return data;

    }

    public suspend fun getGames(): List<Game> {
        val supabase = getClient()
        val supabaseResponse = supabase.postgrest.from("game").select(Columns.ALL)
        val data = supabaseResponse.decodeList<Game>()
        Log.e("supabase", data.toString())
        return data;

    }

    public suspend fun getMedia(): List<Media> {
        val supabase = getClient()
        val supabaseResponse = supabase.postgrest.from("media").select(Columns.ALL)
        val data = supabaseResponse.decodeList<Media>()
        Log.e("supabase", data.toString())
        return data;

    }

//    public suspend fun getPatientInfo(): Patient {
//        val supabase = getClient()
//        val supabaseResponse = supabase.postgrest.from("patient").select(Columns.ALL)
//        val data = supabaseResponse.decodeSingle<Patient>()
//        Log.e("supabase", data.toString())
////        games = data
//        return data;

//    }
}