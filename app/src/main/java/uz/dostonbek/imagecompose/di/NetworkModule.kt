package uz.dostonbek.imagecompose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import uz.dostonbek.imagecompose.BuildConfig
import uz.dostonbek.imagecompose.network.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun baseUrl():String{
        return if (BuildConfig.DEBUG){
            "https://api.unsplash.com/search/photos/"
        }else{
            "https://api.unsplash.com/search/photos/"
        }
    }

    @Singleton
    @Provides
    fun providesRetrofit(baseUrl:String):Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit):ApiService = retrofit.create(ApiService::class.java)
}