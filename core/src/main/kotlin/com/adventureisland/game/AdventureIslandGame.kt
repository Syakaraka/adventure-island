package com.adventureisland.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20

/**
 * 游戏主入口类
 * 继承自 LibGDX 的 Game 类，管理游戏状态和屏幕切换
 */
class AdventureIslandGame : Game() {
    
    init {
        println("[AdventureIslandGame] Class loaded")
    }
    
    override fun create() {
        println("[AdventureIslandGame] ====== create() START ======")
        
        try {
            println("[AdventureIslandGame] Setting up test screen...")
            
            // 先设置一个简单的清屏屏幕，测试基本功能
            setScreen(object : Screen {
                init {
                    println("[TestScreen] Screen object created")
                }
                
                override fun show() {
                    println("[TestScreen] show() called")
                }
                
                override fun render(delta: Float) {
                    Gdx.gl.glClearColor(0.4f, 0.6f, 0.9f, 1f)
                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
                }
                
                override fun resize(w: Int, h: Int) {
                    println("[TestScreen] resize($w, $h)")
                }
                override fun pause() {
                    println("[TestScreen] pause()")
                }
                override fun resume() {
                    println("[TestScreen] resume()")
                }
                override fun hide() {
                    println("[TestScreen] hide()")
                }
                override fun dispose() {
                    println("[TestScreen] dispose()")
                }
            })
            
            println("[AdventureIslandGame] Test screen set successfully")
            println("[AdventureIslandGame] ====== create() END ======")
        } catch (e: Exception) {
            println("[AdventureIslandGame] FATAL ERROR in create(): ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
    
    override fun render() {
        try {
            super.render()
        } catch (e: Exception) {
            println("[AdventureIslandGame] ERROR in render(): ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
    
    override fun dispose() {
        println("[AdventureIslandGame] dispose() called")
        super.dispose()
    }
}
