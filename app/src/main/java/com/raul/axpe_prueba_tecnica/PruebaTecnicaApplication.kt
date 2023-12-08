package com.raul.axpe_prueba_tecnica

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class PruebaTecnicaApplication : Application(){
    override fun attachBaseContext(base: Context?) {

        super.attachBaseContext(base)
    }
}
