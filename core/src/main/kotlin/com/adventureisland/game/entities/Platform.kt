package com.adventureisland.game.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * 平台类型
 */
enum class PlatformType {
    NORMAL,     // 普通平台
    LADDER,     // 梯子
    BREAKABLE   // 可破坏平台
}

/**
 * 平台实体
 */
class Platform(x: Float, y: Float, width: Float, height: Float, 
               val type: PlatformType = PlatformType.NORMAL) : Entity(x, y, width, height) {
    
    var isBroken: Boolean = false
    
    override fun update(deltaTime: Float) {
        // 平台是静态的，不需要更新
    }
    
    override fun render(batch: SpriteBatch) {
        if (isBroken) return
        
        texture?.let {
            batch.draw(it, x, y, width, height)
        }
    }
    
    fun breakPlatform() {
        if (type == PlatformType.BREAKABLE) {
            isBroken = true
            isActive = false
        }
    }
}
