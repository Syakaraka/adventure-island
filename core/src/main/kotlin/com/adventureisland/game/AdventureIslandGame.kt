package com.adventureisland.game

import com.badlogic.gdx.Game
import com.adventureisland.game.screens.GameScreen

/**
 * 游戏主入口类
 * 继承自 LibGDX 的 Game 类，管理游戏状态和屏幕切换
 */
class AdventureIslandGame : Game() {
    
    override fun create() {
        // 启动游戏主界面
        setScreen(GameScreen(this))
    }
    
    override fun render() {
        super.render()
    }
    
    override fun dispose() {
        // 释放资源
        super.dispose()
    }
}
