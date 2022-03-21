import java.awt.Color;
import java.util.ArrayList;

/**
 * Esta clase corresponde al mecanismo que controla el panel de pixeles, contiene el color actual
 * con el que se está pintando y un arraylist con los pixeles a manipular.
 * @author Simón Vergara
 *
 */
public class PixelArtEngine {
	private Color actualColor;
	private ArrayList<Pixel> pixels;
	 
	public PixelArtEngine(ArrayList<Pixel> pixels) {
		super();
		this.actualColor = Color.BLACK;
		this.pixels = pixels;
	}
	
	public void paintAll(Color c) {
		for(int i = 0;i<400;i++) {
			pixels.get(i).setShownColor(c);
		}
	}
	
	public void eraseAll() {
		for(int i = 0;i<400;i++) {
			pixels.get(i).setShownColor(Color.white);
		}
	}
	
	public Color getActualColor() {
		return actualColor;
	}
	
	public void setActualColor(Color c) {
		this.actualColor = c;
	}
}