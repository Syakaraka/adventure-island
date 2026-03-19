package com.adventureisland.game.utils

/**
 * 游戏全局常量
 */
object Constants {
    // 世界尺寸 (会被动态设置)
    var WORLD_WIDTH = 800f
    var WORLD_HEIGHT = 450f
    
    // 玩家常量
    const val PLAYER_WIDTH = 32f
    const val PLAYER_HEIGHT = 48f
    const val PLAYER_SPEED = 200f
    const val PLAYER_JUMP_FORCE = 400f
    const val PLAYER_GRAVITY = 900f
    
    // 敌人常量
    const val ENEMY_WIDTH = 32f
    const val ENEMY_HEIGHT = 32f
    const val ENEMY_SPEED = 50f
    
    // 投射物 (石头)
    const val PROJECTILE_WIDTH = 16f
    const val PROJECTILE_HEIGHT = 16f
    const val PROJECTILE_SPEED = 400f
    
    // 物理
    const val GRAVITY = 900f
    
    // 游戏状态
    const val MAX_LIVES = 3
}
