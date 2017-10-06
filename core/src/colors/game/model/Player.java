package colors.game.model;

public class Player {

    public int x, y;
    public Painter painter;

    public Player(int x, int y, Painter painter) {
        this.x = x;
        this.y = y;
        this.painter = painter;
    }

    public String toString() {
        return String.format("x:%d y:%d painter:[%s]", x, y, painter.toString());
    }
}
