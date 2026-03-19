package com.adventureisland.game.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle

/**
 * 游戏实体基类
 * 所有游戏对象都继承自此类
 */
abstract class Entity(
    var x: Float,
    var y: Float,
    var width: Float,
    var height: Float
) {
    var vx: Float = 0f  // X 轴速度
    var vy: Float = 0f  // Y 轴速度
    
    var isActive: Boolean = true
    var isVisible: Boolean = true
    
    protected var texture: Texture? = null
    
    /**
     * 获取实体的碰撞盒
     */
    val bounds: Rectangle
        get() = Rectangle(x, y, width, height)
    
    /**
     * 更新实体状态
     * @param deltaTime 时间增量 (秒)
     */
    abstract fun update(deltaTime: Float)
    
    /**
     * 渲染实体
     */
    open fun render(batch: SpriteBatch) {
        texture?.let {
            batch.draw(it, x, y, width, height)
        }
    }
    
    /**
     * 应用物理 (重力和速度)
     */
    fun applyPhysics(deltaTime: Float, gravity: Float = 0f) {
        // 应用重力
        if (gravity > 0) {
            vy -= gravity * deltaTime
        }
        
        // 更新位置
        x += vx * deltaTime
        y += vy * deltaTime
    }
    
    /**
     * 设置纹理
     */
    fun setTexture(texturePath: String) {
        texture = Texture(texturePath)
    }
    
    /**
     * 释放资源
     */
    open fun dispose() {
        texture?.dispose()
        texture = null
    }
}
