package com.adventureisland.game.screens

import com.adventureisland.game.AdventureIslandGame
import com.adventureisland.game.utils.Constants
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport

/**
 * 游戏结束界面
 */
class GameOverScreen(val game: AdventureIslandGame, val score: Int) : Screen {
    
    private val camera: OrthographicCamera
    private val batch: SpriteBatch
    private val font: BitmapFont
    
    private var restartTimer: Float = 0f
    private val restartDelay: Float = 3f
    
    init {
        camera = OrthographicCamera()
        camera.setToOrtho(false, Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT)
        batch = SpriteBatch()
        font = BitmapFont()
    }
    
    override fun render(deltaTime: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        
        restartTimer += deltaTime
        
        camera.update()
        batch.projectionMatrix = camera.combined
        batch.begin()
        
        // 绘制游戏结束文字
        font.data.setScale(2f)
        font.draw(batch, "GAME OVER", Constants.WORLD_WIDTH / 2 - 100f, Constants.WORLD_HEIGHT / 2 + 50f)
        
        font.data.setScale(1f)
        font.draw(batch, "Score: $score", Constants.WORLD_WIDTH / 2 - 50f, Constants.WORLD_HEIGHT / 2 - 20f)
        
        if (restartTimer >= restartDelay) {
            font.draw(batch, "Restarting...", Constants.WORLD_WIDTH / 2 - 70, Constants.WORLD_HEIGHT / 2 - 80)
            
            // 自动重启
            if (restartTimer >= restartDelay + 1f) {
                game.setScreen(GameScreen(game))
            }
        }
        
        batch.end()
    }
    
    override fun resize(width: Int, height: Int) {}
    override fun pause() {}
    override fun resume() {}
    override fun hide() {}
    override fun show() {
        restartTimer = 0f
    }
    override fun dispose() {
        batch.dispose()
        font.dispose()
    }
}
