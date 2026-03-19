package com.adventureisland.game

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

/**
 * 安卓主 Activity
 * 游戏入口点
 */
class MainActivity : AndroidApplication() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val config = AndroidApplicationConfiguration().apply {
            // 横屏游戏
            useImmersiveMode = true
            hideStatusBar = true
            hideNavBar = true
            
            // 音频配置
            useAudio = true
            
            // 传感器 (可选)
            useAccelerometer = false
            useCompass = false
            useGyroscope = false
        }
        
        // 初始化游戏
        initialize(AdventureIslandGame(), config)
    }
}
