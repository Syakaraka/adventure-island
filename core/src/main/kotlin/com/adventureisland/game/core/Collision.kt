package com.adventureisland.game.core

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Array

/**
 * 碰撞检测系统
 * 使用 AABB (Axis-Aligned Bounding Box) 碰撞检测
 */
object Collision {
    
    /**
     * 检测两个矩形是否碰撞
     */
    fun check(rect1: Rectangle, rect2: Rectangle): Boolean {
        return rect1.overlaps(rect2)
    }
    
    /**
     * 检测实体与实体数组的碰撞
     * @return 返回第一个碰撞的实体，如果没有则返回 null
     */
    fun <T> check(entity: com.adventureisland.game.entities.Entity, 
                  others: Array<T>): T? where T : com.adventureisland.game.entities.Entity {
        val rect1 = entity.bounds
        
        for (other in others) {
            if (other !== entity && check(rect1, other.bounds)) {
                return other
            }
        }
        
        return null
    }
    
    /**
     * 检测两个实体是否碰撞
     */
    fun check(entity1: com.adventureisland.game.entities.Entity, 
              entity2: com.adventureisland.game.entities.Entity): Boolean {
        return entity1.bounds.overlaps(entity2.bounds)
    }
    
    /**
     * 获取碰撞方向
     * @return "top", "bottom", "left", "right" 或 null (无碰撞)
     */
    fun getCollisionDirection(rect1: Rectangle, rect2: Rectangle): String? {
        if (!rect1.overlaps(rect2)) return null
        
        val overlapLeft = rect2.x - rect1.x - rect1.width
        val overlapRight = rect2.x + rect2.width - rect1.x
        val overlapTop = rect2.y + rect2.height - rect1.y
        val overlapBottom = rect2.y - rect1.y - rect1.height
        
        val minOverlap = minOf(
            kotlin.math.abs(overlapLeft),
            kotlin.math.abs(overlapRight),
            kotlin.math.abs(overlapTop),
            kotlin.math.abs(overlapBottom)
        )
        
        return when (minOverlap) {
            kotlin.math.abs(overlapLeft) -> "left"
            kotlin.math.abs(overlapRight) -> "right"
            kotlin.math.abs(overlapTop) -> "top"
            kotlin.math.abs(overlapBottom) -> "bottom"
            else -> null
        }
    }
}
