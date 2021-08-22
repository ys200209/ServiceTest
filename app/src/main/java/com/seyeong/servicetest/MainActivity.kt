package com.seyeong.servicetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.seyeong.servicetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun serviceStart(view: View) {
        val intent = Intent(this, MyService::class.java) // MyService는 코틀린임에도 불구하고 class.java로 입력한다
        // Intent intent = new Intent(this, MyService.class)
        intent.action = MyService.ACTION_START
        startService(intent)
    }

    fun serviceStop(view: View) {
        val intent = Intent(this, MyService::class.java)
        intent.action = MyService.ACTION_STOP
        stopService(intent)
    }

    override fun onDestroy() {
        Log.d("태그", "서비스가 종료되었습니다.")
        super.onDestroy()
    }

}


