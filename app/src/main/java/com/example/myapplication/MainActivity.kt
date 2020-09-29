package com.example.myapplication

import android.content.BroadcastReceiver
import  android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {

    private var count : Int = 0
    private val t by lazy { makeBroadcastReceiver() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        registerReceiver(t, IntentFilter("subFactorial"))
    }

    override fun onDestroy() {
        unregisterReceiver(t)
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState.run{
            putString("count", count.toString()) }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        super.onRestoreInstanceState(savedInstanceState)

        count = Integer.parseInt(savedInstanceState.getString("count"))
        count = count + 1

        Log.w("YaremkoDR","N is $count")

        val intent = Intent(this, MyService::class.java)
        intent.putExtra("count", count)
        startService(intent)

    }

    private fun makeBroadcastReceiver(): BroadcastReceiver {
        return object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent?) {

                var result = (intent?.getStringExtra("service")).toString().toDouble()
                textView.setText("${result.toInt()}")
                Log.d("YaremkoDR","Result is $result")

            }
        }
    }
    }
