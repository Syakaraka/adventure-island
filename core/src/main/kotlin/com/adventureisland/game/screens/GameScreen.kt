package com.adventureisland.game.screens

import com.adventureisland.game.AdventureIslandGame
import com.adventureisland.game.core.Collision
import com.adventureisland.game.core.EntityManager
import com.adventureisland.game.core.GameLoop
import com.adventureisland.game.entities.*
import com.adventureisland.game.input.InputHandler
import com.adventureisland.game.utils.Constants
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport

/**
 * 游戏主界面
 */
class GameScreen(val game: AdventureIslandGame) : Screen {
    
    private val camera: OrthographicCamera
    private val viewport: Viewport
    private var batch: SpriteBatch? = null
    
    private val gameLoop = GameLoop()
    private val entityManager = EntityManager()
    private val inputHandler = InputHandler()
    
    private var player: Player? = null
    private var platforms = mutableListOf<Platform>()
    private var isInitialized = false
    
    init {
        // 设置相机和视口
        camera = OrthographicCamera()
        viewport = FitViewport(800f, 450f, camera)
    }
    
    override fun show() {
        // 在屏幕显示时初始化游戏（确保 GL 上下文已准备好）
        if (!isInitialized) {
            // 创建 SpriteBatch（需要 GL 上下文）
            batch = SpriteBatch()
            initGame()
            isInitialized = true
        }
    }
    
    private fun initGame() {
        // 创建玩家
        player = Player(100f, 200f).apply {
            inputHandler.player = this
        }
        entityManager.add(player!!)
        
        // 创建关卡平台
        createLevel1()
        
        // 创建敌人
        entityManager.add(Enemy(400f, 200f, EnemyType.WALKING))
        entityManager.add(Enemy(600f, 200f, EnemyType.WALKING))
    }
    
    private fun createLevel1() {
        // 地面
        platforms.add(Platform(0f, 0f, Constants.WORLD_WIDTH, 40f, PlatformType.NORMAL))
        
        // 平台
        platforms.add(Platform(200f, 120f, 150f, 20f, PlatformType.NORMAL))
        platforms.add(Platform(450f, 200f, 150f, 20f, PlatformType.NORMAL))
        platforms.add(Platform(100f, 280f, 150f, 20f, PlatformType.NORMAL))
        
        // 梯子
        platforms.add(Platform(250f, 40f, 30f, 80f, PlatformType.LADDER))
        platforms.add(Platform(500f, 120f, 30f, 80f, PlatformType.LADDER))
        
        // 添加到实体管理器
        for (platform in platforms) {
            entityManager.add(platform)
        }
    }
    
    override fun render(deltaTime: Float) {
        // 清除屏幕
        Gdx.gl.glClearColor(0.4f, 0.6f, 0.9f, 1f)  // 天空蓝背景
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        
        // 如果还未初始化或 batch 未创建，直接返回
        if (!isInitialized || batch == null) return
        
        // 更新游戏逻辑
        gameLoop.update(deltaTime) {
            updateGame(deltaTime)
        }
        
        // 渲染
        camera.update()
        batch!!.projectionMatrix = camera.combined
        batch!!.begin()
        entityManager.render(batch!!)
        renderUI(batch!!)
        batch!!.end()
    }
    
    private fun updateGame(deltaTime: Float) {
        // 处理输入
        inputHandler.updatePlayer()
        
        // 更新实体
        entityManager.update(deltaTime)
        
        // 玩家物理和碰撞
        player?.let { p ->
            applyPlayerPhysics(p, deltaTime)
            checkPlayerCollisions(p)
        }
        
        // 检查投射物与敌人碰撞
        checkProjectileCollisions()
        
        // 检查玩家与敌人碰撞
        checkPlayerEnemyCollisions()
        
        // 检查玩家与道具碰撞
        checkPlayerItemCollisions()
        
        // 检查玩家是否掉落
        player?.let { p ->
            if (p.y < -50) {
                p.takeDamage()
                if (p.isActive) {
                    respawnPlayer()
                } else {
                    gameOver()
                }
            }
        }
    }
    
    private fun applyPlayerPhysics(player: Player, deltaTime: Float) {
        // 检查是否在梯子上
        player.isClimbing = false
        for (platform in platforms) {
            if (platform.type == PlatformType.LADDER && 
                Collision.check(player, platform)) {
                player.isClimbing = true
                player.vy = 0f
                break
            }
        }
        
        // 应用重力 (不在爬梯时)
        if (!player.isClimbing) {
            player.applyPhysics(deltaTime, Constants.GRAVITY)
        }
        
        // 地面碰撞
        player.isOnGround = false
        for (platform in platforms) {
            if (platform.type == PlatformType.NORMAL && !platform.isBroken) {
                val direction = Collision.getCollisionDirection(player.bounds, platform.bounds)
                if (direction == "bottom") {
                    player.isOnGround = true
                    player.y = platform.y + platform.height
                    player.vy = 0f
                }
            }
        }
    }
    
    private fun checkPlayerCollisions(player: Player) {
        // 平台碰撞
        for (platform in platforms) {
            if (platform.type == PlatformType.NORMAL && !platform.isBroken) {
                val direction = Collision.getCollisionDirection(player.bounds, platform.bounds)
                when (direction) {
                    "bottom" -> {
                        player.isOnGround = true
                        player.y = platform.y + platform.height
                        player.vy = 0f
                    }
                    "top" -> {
                        player.y = platform.y - player.height
                        player.vy = 0f
                    }
                }
            }
        }
    }
    
    private fun checkProjectileCollisions() {
        val projectiles = entityManager.getOfType(Projectile::class.java)
        val enemies = entityManager.getOfType(Enemy::class.java)
        
        for (projectile in projectiles) {
            for (enemy in enemies) {
                if (enemy.isActive && !enemy.isDead && Collision.check(projectile, enemy)) {
                    enemy.hitByProjectile()
                    projectile.isActive = false
                    break
                }
            }
        }
    }
    
    private fun checkPlayerEnemyCollisions() {
        player?.let { p ->
            val enemies = entityManager.getOfType(Enemy::class.java)
            for (enemy in enemies) {
                if (enemy.isActive && !enemy.isDead && Collision.check(p, enemy)) {
                    if (enemy.isStunned) {
                        // 踢走眩晕的敌人
                        enemy.isActive = false
                        p.score += 100
                    } else if (p.vy < 0 && p.y > enemy.y + enemy.height / 2) {
                        // 踩踏敌人
                        enemy.stomped()
                        p.vy = Constants.PLAYER_JUMP_FORCE / 2
                        p.score += 200
                    } else {
                        // 玩家受伤
                        p.takeDamage()
                        if (!p.isActive) {
                            gameOver()
                        }
                    }
                }
            }
        }
    }
    
    private fun checkPlayerItemCollisions() {
        player?.let { p ->
            val items = entityManager.getOfType(Item::class.java)
            for (item in items) {
                if (item.isActive && Collision.check(p, item)) {
                    val effect = item.collect()
                    when (effect) {
                        "life" -> p.lives++
                        "coin" -> p.score += 50
                        "score" -> p.score += 100
                    }
                }
            }
        }
    }
    
    private fun renderUI(batch: SpriteBatch) {
        player?.let { p ->
            // 简单 UI 渲染 (实际项目中应使用 BitmapFont)
            // 这里只是占位，实际需要使用 LibGDX 的 Scene2D UI
        }
    }
    
    private fun respawnPlayer() {
        player?.let { p ->
            p.x = 100f
            p.y = 200f
            p.vx = 0f
            p.vy = 0f
        }
    }
    
    private fun gameOver() {
        // 切换到游戏结束界面
        game.setScreen(GameOverScreen(game, player?.score ?: 0))
    }
    
    override fun resize(width: Int, height: Int) {
        viewport.update(width, height)
    }
    
    override fun pause() {}
    
    override fun resume() {}
    
    override fun hide() {}
    
    override fun dispose() {
        batch.dispose()
        entityManager.clear()
    }
}
