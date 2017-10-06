package colors.game.model;

import java.util.Random;

public class Color {
    public static final Color BLUE = new Color(0, 0, 1);
    public static final Color GREEN = new Color(0, 1, 0);
    public static final Color RED = new Color(1, 0, 0);

    public float red;
    public float green;
    public float blue;

    public Color() {
        this(0, 0, 0);
    }

    public Color(Color color) {
        this(color.red, color.green, color.blue);
    }

    public Color(float red, float green, float blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static Color[] colors = new Color[] {RED, GREEN, BLUE};

    private static Random random = new Random();

    public static void setColors(Color... newColors) {
        colors = newColors;
    }

    public static Color getRandom() {
        return new Color(colors[random.nextInt(colors.length)]);
    }

    public float getMax() {
        return Math.max(Math.max(red, green), blue);
    }

    public com.badlogic.gdx.graphics.Color gdx() {
        return new com.badlogic.gdx.graphics.Color(red, green, blue, 1);
    }

    public static Color add(Color color1, Color color2) {
        Color newColor = new Color();
        newColor.red = Math.max(color1.red, color2.red);
        newColor.green = Math.max(color1.green, color2.green);
        newColor.blue = Math.max(color1.blue, color2.blue);
        return newColor;
    }

    public void applyPainter(Painter painter) {
        this.red = Math.max(Math.min(this.red, this.red + painter.power * painter.color.red), this.red + painter.power * painter.color.red);
        this.green = Math.max(Math.min(this.green, this.green + painter.power * painter.color.green), this.green + painter.power * painter.color.green);
        this.blue = Math.max(Math.min(this.blue, this.blue + painter.power * painter.color.blue), this.blue + painter.power * painter.color.blue);
    }
}
