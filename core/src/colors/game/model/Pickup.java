package colors.game.model;

public class Pickup {

    public int x;
    public int y;
    public Painter painter;

    public Pickup(int x, int y, Painter painter) {
        this.x = x;
        this.y = y;
        this.painter = painter;
    }
}
