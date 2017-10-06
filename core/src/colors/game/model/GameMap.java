package colors.game.model;

import colors.game.ColorsGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMap {

    private final ColorsGame game;

    public Color[][] tiles;
    public Player player;
    public List<Pickup> pickups;

    public int tileSize = 50;
    public int width;
    public int height;

    private Random random;

    public GameMap(final ColorsGame game) {
        this.game = game;

        width = game.screenWidth / tileSize;
        height = game.screenHeight / tileSize;

        pickups = new ArrayList<Pickup>();

        player = new Player(0, 0, new Painter(1, Color.RED));
        setTiles();
        spawnPickups(2);
    }

    public void spawnPickups(int count) {
        Random random = new Random();
        int x, y;

        for (int i = 0; i < count; i++) {
            do {
                x = random.nextInt(width);
                y = random.nextInt(height);
            } while (Math.abs(player.x - x) + Math.abs(player.y - y) < 4);
            pickups.add(new Pickup(x, y, new Painter(random.nextInt(2) * 2 - 1, Color.getRandom())));
        }
    }

    public void setTiles() {
        tiles = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = Color.getRandom();
            }
        }
    }
}
