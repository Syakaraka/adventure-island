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
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")
        
        try {
            val config = AndroidApplicationConfiguration().apply {
                // 横屏游戏 - 使用沉浸式模式隐藏状态栏和导航栏
                useImmersiveMode = true
                
                // 传感器 (可选)
                useAccelerometer = false
                useCompass = false
                useGyroscope = false
            }
            
            Log.d(TAG, "Initializing game...")
            // 初始化游戏
            initialize(AdventureIslandGame(), config)
            Log.d(TAG, "Game initialized successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing game", e)
            throw e
        }
    }
}
