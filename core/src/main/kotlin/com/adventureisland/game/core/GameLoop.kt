package com.adventureisland.game.core

import com.badlogic.gdx.utils.TimeUtils

/**
 * 游戏主循环管理
 * 负责更新游戏逻辑和渲染
 */
class GameLoop {
    
    private var lastUpdateTime: Long = 0
    private var accumulator: Float = 0f
    private val fixedTimeStep: Float = 1f / 60f // 60 FPS 固定更新
    
    /**
     * 更新游戏状态
     * @param deltaTime 距离上一帧的时间 (秒)
     */
    fun update(deltaTime: Float, updateAction: () -> Unit) {
        accumulator += deltaTime
        
        // 固定时间步长更新，确保物理模拟稳定
        while (accumulator >= fixedTimeStep) {
            updateAction()
            accumulator -= fixedTimeStep
        }
    }
    
    /**
     * 重置循环计时器
     */
    fun reset() {
        lastUpdateTime = TimeUtils.millis()
        accumulator = 0f
    }
}
