package colors.game.screens;

import colors.game.ColorsGame;
import colors.game.model.Color;
import colors.game.model.GameMap;
import colors.game.model.Pickup;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen implements Screen {

    private final ColorsGame game;
    private OrthographicCamera camera;

    private GameMap map;

    public int playerRadius = 20;

    public GameScreen(final ColorsGame game) {
        this.game = game;

        Color.setColors(Color.RED, Color.GREEN, Color.BLUE);

        map = new GameMap(game);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenWidth, game.screenHeight);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.shapeRenderer.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //camera.update();

        game.shapeRenderer.begin();
        drawMap();
        drawPickups();
        drawPlayer();
        game.shapeRenderer.end();

        update(delta);
    }

    private void drawPickups() {
        for (Pickup pickup : map.pickups) {
            game.shapeRenderer.setColor(pickup.painter.color.gdx());
            int x0 = pickup.x * map.tileSize;
            int y0 = pickup.y * map.tileSize;
            game.shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
            if (pickup.painter.power > 0) {
                game.shapeRenderer.triangle(x0 + 10, y0 + 10, x0 + 25, y0 + 40, x0 + 40, y0 + 10);
            } else {
                game.shapeRenderer.rect(x0 + 10, y0 + 20, 30, 10);
            }
            game.shapeRenderer.set(ShapeRenderer.ShapeType.Line);
            game.shapeRenderer.setColor(com.badlogic.gdx.graphics.Color.BLACK);
            if (pickup.painter.power > 0) {
                game.shapeRenderer.triangle(x0 + 10, y0 + 10, x0 + 25, y0 + 40, x0 + 40, y0 + 10);
            } else {
                game.shapeRenderer.rect(x0 + 10, y0 + 20, 30, 10);
            }
        }
    }

    private void drawMap() {
        game.shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        for (int i = 0; i < map.width; i++) {
            for (int j = 0; j < map.height; j++) {
                game.shapeRenderer.setColor(map.tiles[i][j].red, map.tiles[i][j].green, map.tiles[i][j].blue, 1);
                game.shapeRenderer.rect(i * map.tileSize, j * map.tileSize, map.tileSize, map.tileSize);
            }
        }
    }

    private void drawPlayer() {
        game.shapeRenderer.setColor(map.player.painter.color.gdx());
        game.shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.circle(map.player.x * map.tileSize + map.tileSize / 2, map.player.y * map.tileSize + map.tileSize / 2, playerRadius);
        game.shapeRenderer.set(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(map.tiles[(int)map.player.x][(int)map.player.y].getMax() > 0.5 ? com.badlogic.gdx.graphics.Color.BLACK : com.badlogic.gdx.graphics.Color.WHITE);
        game.shapeRenderer.circle(map.player.x * map.tileSize + map.tileSize / 2, map.player.y * map.tileSize + map.tileSize / 2, playerRadius);
    }

    private void update(float delta) {
        boolean isMoved = false;
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (map.player.x > 0) map.player.x--;
            isMoved = true;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (map.player.x < map.width - 1) map.player.x++;
            isMoved = true;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (map.player.y < map.height - 1) map.player.y++;
            isMoved = true;
        }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (map.player.y > 0) map.player.y--;
            isMoved = true;
        }

        if (isMoved) {
            map.tiles[(int)map.player.x][(int)map.player.y].applyPainter(map.player.painter);

            Pickup toRemove = null;
            for (Pickup pickup : map.pickups) {
                if (map.player.x == pickup.x && map.player.y == pickup.y) {
                    toRemove = pickup;
                    break;
                }
            }
            if (toRemove != null) {
                map.player.painter = toRemove.painter;
                map.pickups.remove(toRemove);
                map.spawnPickups(1);
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
