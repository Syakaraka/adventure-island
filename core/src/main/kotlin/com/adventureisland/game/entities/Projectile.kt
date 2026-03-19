package com.adventureisland.game.entities

import com.adventureisland.game.utils.Constants
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * 投射物 (石头/锤子)
 */
class Projectile(x: Float, y: Float, facingRight: Boolean) : 
    Entity(x, y, Constants.PROJECTILE_WIDTH, Constants.PROJECTILE_HEIGHT) {
    
    init {
        vx = (if (facingRight) 1 else -1) * Constants.PROJECTILE_SPEED
    }
    
    override fun update(deltaTime: Float) {
        // 水平移动
        x += vx * deltaTime
        
        // 超出屏幕后销毁
        if (x < -width || x > Constants.WORLD_WIDTH + width) {
            isActive = false
        }
    }
    
    override fun render(batch: SpriteBatch) {
        texture?.let {
            batch.draw(it, x, y, width, height)
        }
    }
}
