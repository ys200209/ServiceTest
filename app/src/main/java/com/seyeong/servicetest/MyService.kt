package com.seyeong.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {


    override fun onBind(intent: Intent): IBinder { // 스타티드 서비스에서는 사용하지 않고 바운드 서비스에서만 사용한다.
        return binder // MyService를 Binder() 형태로 반환해서 binder 변수에 담아두었다.
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action // intent.action 의미
        Log.d("태그", "action = $action")
        return super.onStartCommand(intent, flags, startId)
    }

    companion object { // 테스트로 사용할 명령어 ( 패키지명 + 명령어 )
        val ACTION_START = "com.seyeong.servicetest.START"
        val ACTION_RUN = "com.seyeong.servicetest.RUN"
        val ACTION_STOP = "com.seyeong.servicetest.STOP"
    }

    //////////// 여기까지 스타티드 서비스

    //////////// 여기서부터 바운드 서비스
    inner class MyBinder: Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }
    val binder = MyBinder()

    fun serviceMessage(): String {
        return "Hello Activity! I am Service!"
    }


}