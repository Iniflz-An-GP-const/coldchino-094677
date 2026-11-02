package nazema.coldchino.info

import android.app.Application
import nazema.coldchino.info.di.dataModule
import nazema.coldchino.info.di.dispatcherModule
import nazema.coldchino.info.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class NMDNOApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModules = dispatcherModule + dataModule + viewModule

        startKoin {
            androidLogger()
            androidContext(this@NMDNOApplication)
            modules(appModules)
        }
    }
}