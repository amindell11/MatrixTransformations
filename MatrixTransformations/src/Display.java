import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * @author Jacob
 */
public class Display extends BasicGame {
	Letter letter;

	public Display() {
		super("Matrix Sime");
	}

	public static void main(String[] arguments) {
		try {
			AppGameContainer app = new AppGameContainer(new Display());
			app.setDisplayMode(500, 400, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		float[][] N = { { 0, 0 }, { .5f, 0 }, { .5f, 6.42f }, { 6, 0 }, { 6, 8 }, { 5.5f, 8 }, { 5.5f, 1.58f },
				{ 0, 8 } };
		letter = new Letter(N);
		letter.vertices = Letter.reflectY(letter.vertices);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		letter.vertices = Letter.rotate(letter.vertices, .001f * delta);
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		letter.render(g);
	}
}