package dev.cianjur.ngopi

import android.app.Application
import dev.cianjur.ngopi.core.di.databaseModule
import dev.cianjur.ngopi.core.di.networkModule
import dev.cianjur.ngopi.core.di.repositoryModule
import dev.cianjur.ngopi.di.useCaseModule
import dev.cianjur.ngopi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Suppress("unused") // as used in manifest file
class NgopiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@NgopiApplication)
            modules(databaseModule, networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }
}
