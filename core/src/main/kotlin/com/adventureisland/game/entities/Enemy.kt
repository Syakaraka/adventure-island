package com.adventureisland.game.entities

import com.adventureisland.game.utils.Constants
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * 敌人类型
 */
enum class EnemyType {
    WALKING,    // 地面行走敌人
    FLYING,     // 飞行敌人
    BOSS        // BOSS
}

/**
 * 敌人实体
 * 简单的 AI：来回巡逻
 */
class Enemy(x: Float, y: Float, val type: EnemyType = EnemyType.WALKING) : 
    Entity(x, y, Constants.ENEMY_WIDTH, Constants.ENEMY_HEIGHT) {
    
    var isStunned: Boolean = false
    var isDead: Boolean = false
    
    private var patrolDirection: Int = 1  // 1=右，-1=左
    private var patrolTimer: Float = 0f
    private val patrolInterval: Float = 2f
    
    private var stunTimer: Float = 0f
    private val stunDuration: Float = 5f
    
    override fun update(deltaTime: Float) {
        if (isDead) return
        
        if (isStunned) {
            // 眩晕状态
            stunTimer -= deltaTime
            if (stunTimer <= 0) {
                isStunned = false
            }
            return
        }
        
        when (type) {
            EnemyType.WALKING -> updateWalking(deltaTime)
            EnemyType.FLYING -> updateFlying(deltaTime)
            EnemyType.BOSS -> updateBoss(deltaTime)
        }
    }
    
    private fun updateWalking(deltaTime: Float) {
        patrolTimer += deltaTime
        
        // 简单巡逻 AI
        vx = patrolDirection * Constants.ENEMY_SPEED
        
        // 定期改变方向
        if (patrolTimer >= patrolInterval) {
            patrolDirection = -patrolDirection
            patrolTimer = 0f
        }
        
        // 应用物理
        applyPhysics(deltaTime, Constants.GRAVITY)
        
        // 边界检查
        if (x <= 0) {
            patrolDirection = 1
            x = 0f
        } else if (x >= Constants.WORLD_WIDTH - width) {
            patrolDirection = -1
            x = Constants.WORLD_WIDTH - width
        }
    }
    
    private fun updateFlying(deltaTime: Float) {
        // 飞行敌人不受重力影响
        vx = patrolDirection * Constants.ENEMY_SPEED * 1.5f
        
        patrolTimer += deltaTime
        if (patrolTimer >= patrolInterval) {
            patrolDirection = -patrolDirection
            patrolTimer = 0f
        }
        
        x += vx * deltaTime
    }
    
    private fun updateBoss(deltaTime: Float) {
        // BOSS AI (后续实现)
        vx = 0f
        vy = 0f
    }
    
    /**
     * 被石头击中
     */
    fun hitByProjectile() {
        isStunned = true
        stunTimer = stunDuration
    }
    
    /**
     * 被踩踏
     */
    fun stomped() {
        isDead = true
        isActive = false
    }
    
    override fun render(batch: SpriteBatch) {
        if (isDead) return
        
        texture?.let {
            if (isStunned) {
                // 眩晕时闪烁
                val alpha = (kotlin.math.sin(System.currentTimeMillis() / 100f) + 1) / 2
                batch.color = batch.color.cpy().apply { a = alpha }
            }
            batch.draw(it, x, y, width, height)
            batch.color = batch.color.cpy().apply { a = 1f }
        }
    }
    
    override fun dispose() {
        super.dispose()
    }
}
