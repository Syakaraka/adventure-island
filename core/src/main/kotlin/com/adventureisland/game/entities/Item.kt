package com.adventureisland.game.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * 道具类型
 */
enum class ItemType {
    HEART,          // 心形 (生命 +1)
    SPEED_BOOST,    // 速度鞋
    WEAPON_UPGRADE, // 武器升级
    COIN,           // 金币
    FRUIT           // 水果 (分数)
}

/**
 * 道具实体
 */
class Item(x: Float, y: Float, val itemType: ItemType) : Entity(x, y, 24f, 24f) {
    
    private var bobTimer: Float = 0f
    private val bobSpeed: Float = 5f
    private val bobHeight: Float = 5f
    private val originalY: Float = y
    
    override fun update(deltaTime: Float) {
        // 上下浮动动画
        bobTimer += deltaTime
        y = originalY + kotlin.math.sin(bobTimer * bobSpeed) * bobHeight
    }
    
    /**
     * 玩家收集道具
     * @return 道具效果描述
     */
    fun collect(): String {
        isActive = false
        return when (itemType) {
            ItemType.HEART -> "life"
            ItemType.SPEED_BOOST -> "speed"
            ItemType.WEAPON_UPGRADE -> "weapon"
            ItemType.COIN -> "coin"
            ItemType.FRUIT -> "score"
        }
    }
    
    override fun render(batch: SpriteBatch) {
        texture?.let {
            batch.draw(it, x, y, width, height)
        }
    }
}
