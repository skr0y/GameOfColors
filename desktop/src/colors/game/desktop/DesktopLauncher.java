package colors.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import colors.game.ColorsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Colors";
		cfg.resizable = false;
		cfg.width = 800;
		cfg.height = 600;
		new LwjglApplication(new ColorsGame(), cfg);
	}
}
