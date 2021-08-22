package com.seyeong.servicetest

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.seyeong.servicetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun serviceStart(view: View) { // 스타티드 서비스
        val intent = Intent(this, MyService::class.java) // MyService는 코틀린임에도 불구하고 class.java로 입력한다
        // Intent intent = new Intent(this, MyService.class)
        intent.action = MyService.ACTION_START
        startService(intent)
    }

    fun serviceStop(view: View) { // 스타티드 서비스
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }

    // 여기서부턴 바운드 서비스
    var myService: MyService? = null // 현재 서비스가 연결되어 있는지를 확인하는 변수
    var isService = false // 현재 서비스가 연결되어 있는지를 확인하는 변수
    val connection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyService.MyBinder
            myService = binder.getService()
            isService = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isService = false
        }
    }

    fun serviceBind(view: View) { // 바인딩 서비스 연결을 시도하는 메서드
        val intent = Intent(this, MyService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
        Log.d("태그", "(serviceBind) isService = $isService")
    }

    fun serviceUnbind(view: View) {
        Log.d("태그", "(serviceUnbind) isService = $isService")
        if (isService) { // 만약 현재 서비스가 바인드(실행) 중이라면.
            unbindService(connection) // 서비스를 언바인드 하도록.
            isService = false // 연결 해제되었다는 의미
            Log.d("태그", "(serviceUnbind) isService = $isService")
        }
    }

    override fun onDestroy() {
        Log.d("태그", "서비스가 종료되었습니다.")
        super.onDestroy()
    }

}


