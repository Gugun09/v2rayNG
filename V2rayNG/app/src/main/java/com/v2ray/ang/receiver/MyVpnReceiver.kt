package com.v2ray.ang.receiver

import android.os.Handler
import android.os.Looper
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.v2ray.ang.handler.V2RayServiceManager

class MyVpnReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i("MyVpnReceiver", "📥 onReceive: action=${intent.action}, token=${intent.getStringExtra("token")}")
        val token = intent.getStringExtra("token")
        if (token != "abc123") {
            Log.w("MyVpnReceiver", "Token salah, akses ditolak.")
            return
        }

        when (intent.action) {
            "com.v2ray.ang.START_VPN" -> {
                Log.i("MyVpnReceiver", "Trigger START_VPN")
                V2RayServiceManager.startVService(context)
            }
            "com.v2ray.ang.STOP_VPN" -> {
                Log.i("MyVpnReceiver", "Trigger STOP_VPN")
                V2RayServiceManager.stopVService(context)
            }
            "com.v2ray.ang.RESTART_VPN" -> {
                Log.i("MyVpnReceiver", "Trigger RESTART_VPN")
                V2RayServiceManager.stopVService(context)
                Handler(Looper.getMainLooper()).postDelayed({
                    V2RayServiceManager.startVService(context)
                }, 2000) // delay 2 detik
            }
            "com.v2ray.ang.PING_VPN" -> {
                Log.i("MyVpnReceiver", "Trigger PING_VPN")
                com.v2ray.ang.util.MessageUtil.sendMsg2Service(context, com.v2ray.ang.AppConfig.MSG_MEASURE_DELAY, "")
            }
        }
    }
}
