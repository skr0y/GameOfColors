package colors.game;

import colors.game.screens.GameScreen;
import colors.game.screens.MainMenuScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class ColorsGame extends Game {
	public final int screenWidth = 800;
	public final int screenHeight = 600;

	public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        shapeRenderer.setAutoShapeType(true);
        font.setColor(Color.WHITE);

        setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
	}
}
