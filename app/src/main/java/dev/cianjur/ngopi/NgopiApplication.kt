package dev.cianjur.ngopi

import com.google.android.play.core.splitcompat.SplitCompatApplication
import dev.cianjur.ngopi.core.di.databaseModule
import dev.cianjur.ngopi.core.di.networkModule
import dev.cianjur.ngopi.core.di.repositoryModule
import dev.cianjur.ngopi.di.useCaseModule
import dev.cianjur.ngopi.di.viewModelModule
import dev.cianjur.ngopi.util.TimberReleaseTree
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

@Suppress("unused") // as used in manifest file
class NgopiApplication : SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(TimberReleaseTree())
        }

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@NgopiApplication)
            modules(databaseModule, networkModule, repositoryModule, useCaseModule, viewModelModule)
        }
    }
}
