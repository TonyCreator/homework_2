package com.example.myapplication

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import java.lang.Math.*
import kotlin.math.pow

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        var count = intent.getIntExtra("count",0)

        val intent = Intent("subFactorial")
        intent.putExtra("service", subFactorial(count).toString());
        sendBroadcast(intent)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun subFactorial(argument: Int) : Double {
        var result: Double = 1.0
        var temp: Double = -1.0
        for(index in 1..argument) {
            result +=  temp.pow(index)/factorial(index)
        }
        result *=factorial(argument)
        return result
    }
    fun factorial(argument: Int) : Int {
        var result : Int = 1
        for (index in 1..argument) {
            result *= index
        }
        return result
    }
}
