package colors.game.model;

public class Painter {

    public float power;
    public Color color;

    public Painter(float power, Color color) {
        this.power = power;
        this.color = color;
    }

    public String toString() {
        return String.format("power:%.1f color:[%s]", power, color.toString());
    }
}
