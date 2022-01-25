package dev.cianjur.ngopi.core.di

import androidx.room.Room
import dev.cianjur.ngopi.core.BuildConfig
import dev.cianjur.ngopi.core.data.NontonRepositoryImpl
import dev.cianjur.ngopi.core.data.source.local.LocalDataSource
import dev.cianjur.ngopi.core.data.source.local.room.NontonDatabase
import dev.cianjur.ngopi.core.data.source.remote.RemoteDataSource
import dev.cianjur.ngopi.core.data.source.remote.network.TmdbApi
import dev.cianjur.ngopi.core.domain.repository.NontonRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("agustiana".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            NontonDatabase::class.java, "nonton.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
    factory { get<NontonDatabase>().nontonDao() }
}

val networkModule = module {
    single {
        val hostname = BuildConfig.BASE_URL.removePrefix("https://").removeSuffix("/3/")
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/oD/WAoRPvbez1Y2dfYfuo4yujAcYHXdv1Ivb2v2MOKk=")
            .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(hostname, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        val apiKeyInterceptor = Interceptor {
            val newUrl = it.request().url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
            it.proceed(it.request().newBuilder().url(newUrl).build())
        }
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
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
