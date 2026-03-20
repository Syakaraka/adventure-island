package com.adventureisland.game

import android.os.Bundle
import android.util.Log
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

/**
 * 安卓主 Activity
 * 游戏入口点
 */
class MainActivity : AndroidApplication() {
    
    companion object {
        private const val TAG = "MainActivity"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        // 最早期的日志，使用 System.out 确保能被记录
        println("[$TAG] ====== APP STARTING ======")
        println("[$TAG] onCreate called")
        println("[$TAG] Package: ${packageName}")
        Log.d(TAG, "===== APP STARTING =====")
        Log.d(TAG, "onCreate called, package: ${packageName}")
        
        super.onCreate(savedInstanceState)
        
        Log.d(TAG, "super.onCreate completed")
        
        try {
            val config = AndroidApplicationConfiguration().apply {
                // 横屏游戏 - 使用沉浸式模式隐藏状态栏和导航栏
                useImmersiveMode = true
                
                // 关闭可能引起问题的功能
                useAccelerometer = false
                useCompass = false
                useGyroscope = false
                
                // 音频配置
                useAudio = false
                
                // 深度缓冲
                r = 5
                g = 6
                b = 5
                a = 8
                depth = 16
                stencil = 8
            }
            
            Log.d(TAG, "Config created, initializing game...")
            println("[$TAG] Initializing game...")
            
            // 初始化游戏
            initialize(AdventureIslandGame(), config)
            
            Log.d(TAG, "Game initialized successfully")
            println("[$TAG] Game initialized")
        } catch (e: Exception) {
            Log.e(TAG, "FATAL ERROR initializing game", e)
            println("[$TAG] FATAL ERROR: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
    
    override fun onDestroy() {
        Log.d(TAG, "onDestroy called")
        super.onDestroy()
    }
    
    override fun onPause() {
        Log.d(TAG, "onPause called")
        super.onPause()
    }
    
    override fun onResume() {
        Log.d(TAG, "onResume called")
        super.onResume()
    }
}
