package com.adventureisland.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.adventureisland.game.screens.GameScreen

/**
 * 游戏主入口类
 * 继承自 LibGDX 的 Game 类，管理游戏状态和屏幕切换
 */
class AdventureIslandGame : Game() {
    
    override fun create() {
        try {
            Gdx.app.log("AdventureIslandGame", "create() called")
            // 启动游戏主界面
            setScreen(GameScreen(this))
            Gdx.app.log("AdventureIslandGame", "GameScreen set")
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
