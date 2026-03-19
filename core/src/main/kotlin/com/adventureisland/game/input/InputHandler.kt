package com.adventureisland.game.input

import com.adventureisland.game.entities.Player
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.utils.Array

/**
 * 输入处理器
 * 处理触摸和键盘输入
 */
class InputHandler : InputProcessor {
    
    var player: Player? = null
    
    // 虚拟按键状态
    var leftPressed: Boolean = false
    var rightPressed: Boolean = false
    var jumpPressed: Boolean = false
    var attackPressed: Boolean = false
    
    // 虚拟按键区域 (屏幕坐标)
    var leftButtonRect: com.badlogic.gdx.math.Rectangle? = null
    var rightButtonRect: com.badlogic.gdx.math.Rectangle? = null
    var jumpButtonRect: com.badlogic.gdx.math.Rectangle? = null
    var attackButtonRect: com.badlogic.gdx.math.Rectangle? = null
    
    init {
        Gdx.input.inputProcessor = this
    }
    
    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        // 转换为游戏坐标
        val gameX = screenX.toFloat()
        val gameY = Gdx.graphics.height - screenY.toFloat()
        
        // 检查虚拟按键
        leftButtonRect?.let {
            if (it.contains(gameX, gameY)) {
                leftPressed = true
                return true
            }
        }
        
        rightButtonRect?.let {
            if (it.contains(gameX, gameY)) {
                rightPressed = true
                return true
            }
        }
        
        jumpButtonRect?.let {
            if (it.contains(gameX, gameY)) {
                jumpPressed = true
                return true
            }
        }
        
        attackButtonRect?.let {
            if (it.contains(gameX, gameY)) {
                attackPressed = true
                return true
            }
        }
        
        return false
    }
    
    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val gameX = screenX.toFloat()
        val gameY = Gdx.graphics.height - screenY.toFloat()
        
        leftButtonRect?.let {
            if (it.contains(gameX, gameY)) {
                leftPressed = false
            }
        }
        
        rightButtonRect?.let {
            if (it.contains(gameX, gameY)) {
                rightPressed = false
            }
        }
        
        jumpButtonRect?.let {
            if (it.contains(gameX, gameY)) {
                jumpPressed = false
            }
        }
        
        attackButtonRect?.let {
            if (it.contains(gameX, gameY)) {
                attackPressed = false
            }
        }
        
        return false
    }
    
    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.LEFT, Input.Keys.A -> leftPressed = true
            Input.Keys.RIGHT, Input.Keys.D -> rightPressed = true
            Input.Keys.UP, Input.Keys.W, Input.Keys.SPACE -> jumpPressed = true
            Input.Keys.Z, Input.Keys.K -> attackPressed = true
        }
        return false
    }
    
    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.LEFT, Input.Keys.A -> leftPressed = false
            Input.Keys.RIGHT, Input.Keys.D -> rightPressed = false
            Input.Keys.UP, Input.Keys.W, Input.Keys.SPACE -> jumpPressed = false
            Input.Keys.Z, Input.Keys.K -> attackPressed = false
        }
        return false
    }
    
    /**
     * 更新玩家状态
     */
    fun updatePlayer() {
        player?.let { p ->
            // 移动
            when {
                leftPressed -> p.moveLeft()
                rightPressed -> p.moveRight()
                else -> p.stopMoving()
            }
            
            // 跳跃
            if (jumpPressed) {
                p.jump()
                jumpPressed = false  // 防止连续跳跃
            }
            
            // 攻击
            if (attackPressed) {
                p.attack()
                attackPressed = false  // 防止连续攻击
            }
        }
    }
    
    // 其他 InputProcessor 方法 (未使用)
    override fun keyTyped(character: Char): Boolean = false
    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false
    override fun mouseMoved(screenX: Int, screenY: Int): Boolean = false
    override fun scrolled(amountX: Float, amountY: Float): Boolean = false
    override fun touchCancelled(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false
}
