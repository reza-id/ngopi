package dev.cianjur.ngopi.core.di

import androidx.room.Room
import dev.cianjur.ngopi.core.data.NontonRepositoryImpl
import dev.cianjur.ngopi.core.data.source.local.LocalDataSource
import dev.cianjur.ngopi.core.data.source.local.room.NontonDatabase
import dev.cianjur.ngopi.core.data.source.remote.RemoteDataSource
import dev.cianjur.ngopi.core.data.source.remote.network.TmdbApi
import dev.cianjur.ngopi.core.domain.repository.NontonRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            NontonDatabase::class.java, "nonton.db"
        ).fallbackToDestructiveMigration().build()
    }
    factory { get<NontonDatabase>().nontonDao() }
}

val networkModule = module {
    single {
        val apiKeyInterceptor = Interceptor {
            val newUrl = it.request().url.newBuilder().addQueryParameter("api_key", "758680e7a401d859b4984c0622a4cbae").build()
            it.proceed(it.request().newBuilder().url(newUrl).build())
        }
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(TmdbApi::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<NontonRepository> { NontonRepositoryImpl(get(), get()) }
}
