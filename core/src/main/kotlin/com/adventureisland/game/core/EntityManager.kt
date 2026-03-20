package com.adventureisland.game.core

import com.adventureisland.game.entities.Entity
import com.badlogic.gdx.utils.Array

/**
 * 实体管理器
 * 管理所有游戏实体的创建、更新和销毁
 */
class EntityManager {
    
    internal val entities = Array<Entity>()
    private val entitiesToRemove = Array<Entity>()
    private val entitiesToAdd = Array<Entity>()
    
    /**
     * 添加实体
     */
    fun add(entity: Entity) {
        entitiesToAdd.add(entity)
    }
    
    /**
     * 标记实体待删除
     */
    fun remove(entity: Entity) {
        entitiesToRemove.add(entity)
    }
    
    /**
     * 更新所有实体
     * @param deltaTime 时间增量
     */
    fun update(deltaTime: Float) {
        // 先处理待添加的实体
        for (entity in entitiesToAdd) {
            entities.add(entity)
        }
        entitiesToAdd.clear()
        
        // 更新所有实体
        for (entity in entities) {
            if (entity.isActive) {
                entity.update(deltaTime)
            }
        }
        
        // 处理待删除的实体
        for (entity in entitiesToRemove) {
            entities.removeValue(entity, true)
            entity.dispose()
        }
        entitiesToRemove.clear()
    }
    
    /**
     * 渲染所有实体
     */
    fun render(batch: com.badlogic.gdx.graphics.g2d.SpriteBatch) {
        for (entity in entities) {
            if (entity.isVisible) {
                entity.render(batch)
            }
        }
    }
    
    /**
     * 获取所有实体
     */
    fun getAll(): Array<Entity> = entities
    
    /**
     * 获取指定类型的实体
     */
    fun <T : Entity> getOfType(type: Class<T>): com.badlogic.gdx.utils.Array<T> {
        val result = com.badlogic.gdx.utils.Array<T>()
        for (entity in entities) {
            if (type.isInstance(entity)) {
                result.add(type.cast(entity))
            }
        }
        return result
    }
    
    /**
     * 清空所有实体
     */
    fun clear() {
        for (entity in entities) {
            entity.dispose()
        }
        entities.clear()
        entitiesToRemove.clear()
        entitiesToAdd.clear()
    }
}
