package com.adventureisland.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.adventureisland.game.screens.GameScreen

/**
 * 游戏主入口类
 * 继承自 LibGDX 的 Game 类，管理游戏状态和屏幕切换
 */
class AdventureIslandGame : Game() {
    
    override fun create() {
        try {
            Gdx.app.log("AdventureIslandGame", "create() called")
            
            // 先设置一个简单的清屏屏幕，测试基本功能
            setScreen(object : Screen {
                override fun show() {
                    Gdx.app.log("TestScreen", "show() called")
                }
                
                override fun render(delta: Float) {
                    Gdx.gl.glClearColor(0.4f, 0.6f, 0.9f, 1f)
                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
                }
                
                override fun resize(w: Int, h: Int) {}
                override fun pause() {}
                override fun resume() {}
                override fun hide() {}
                override fun dispose() {}
            })
            
            Gdx.app.log("AdventureIslandGame", "Test screen set")
        } catch (e: Exception) {
            Gdx.app.error("AdventureIslandGame", "Error in create()", e)
            throw e
        }
    }
    
    override fun render() {
        super.render()
    }
    
    override fun dispose() {
        // 释放资源
        super.dispose()
    }
}
