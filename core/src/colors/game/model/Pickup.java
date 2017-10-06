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

    public String toString() {
        return String.format("x:%d y:%d painter:[%s]", x, y, painter.toString());
    }
}
