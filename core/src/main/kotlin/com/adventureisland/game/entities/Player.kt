package com.adventureisland.game.entities

import com.adventureisland.game.utils.Constants
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle

/**
 * 玩家角色
 * 支持移动、跳跃、攻击
 */
class Player(x: Float, y: Float) : Entity(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT) {
    
    var isOnGround: Boolean = false
    var isClimbing: Boolean = false
    var facingRight: Boolean = true
    var isAttacking: Boolean = false
    
    var lives: Int = Constants.MAX_LIVES
    var score: Int = 0
    var weaponLevel: Int = 1  // 1=石头，2=锤子
    
    private var attackCooldown: Float = 0f
    private val attackCooldownTime: Float = 0.3f
    
    override fun update(deltaTime: Float) {
        // 处理攻击冷却
        if (attackCooldown > 0) {
            attackCooldown -= deltaTime
        }
        
        // 应用重力 (不在爬梯时)
        if (!isClimbing && !isOnGround) {
            vy -= Constants.GRAVITY * deltaTime
        }
        
        // 更新位置
        x += vx * deltaTime
        y += vy * deltaTime
        
        // 边界检查
        if (x < 0) x = 0f
        if (x > Constants.WORLD_WIDTH - width) x = Constants.WORLD_WIDTH - width
        
        isAttacking = false
    }
    
    /**
     * 向左移动
     */
    fun moveLeft() {
        if (!isClimbing) {
            vx = -Constants.PLAYER_SPEED
            facingRight = false
        }
    }
    
    /**
     * 向右移动
     */
    fun moveRight() {
        if (!isClimbing) {
            vx = Constants.PLAYER_SPEED
            facingRight = true
        }
    }
    
    /**
     * 停止移动
     */
    fun stopMoving() {
        if (!isClimbing) {
            vx = 0f
        }
    }
    
    /**
     * 跳跃
     */
    fun jump() {
        if ((isOnGround || isClimbing) && attackCooldown <= 0) {
            vy = Constants.PLAYER_JUMP_FORCE
            isOnGround = false
        }
    }
    
    /**
     * 攻击 (投掷石头)
     * @return 如果成功攻击返回 true，否则返回 false (冷却中)
     */
    fun attack(): Boolean {
        if (attackCooldown <= 0) {
            isAttacking = true
            attackCooldown = attackCooldownTime
            return true
        }
        return false
    }
    
    /**
     * 受到伤害
     */
    fun takeDamage() {
        lives--
        if (lives <= 0) {
            isActive = false
        }
    }
    
    override fun render(batch: SpriteBatch) {
        // 简单渲染 (实际项目中会使用动画)
        texture?.let {
            if (facingRight) {
                batch.draw(it, x, y, width, height)
            } else {
                // 翻转绘制
                batch.draw(it, x + width, y, -width, height)
            }
        }
    }
    
    override fun dispose() {
        super.dispose()
    }
}
