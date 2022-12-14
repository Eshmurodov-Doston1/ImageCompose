package uz.dostonbek.imagecompose.network

import uz.dostonbek.imagecompose.models.Unsplash
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?client_id=IqIHr46UT9upGwwFvo0QvOdXP784rc_FC0GKaBFM_oQ&")
    suspend fun getImage(
        @Query("query")query:String,
        @Query("page")page:Int,
        @Query("per_page")per_page:Int=10,
    ): Unsplash
}