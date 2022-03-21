import java.awt.Color;

/**
 * La clase Pixel es un botón que se contiene dentro del panel para dibujar,
 * y puede cambiar de color.
 * @author Simón Vergara
 */

public class Pixel extends ColorButton{
	
	private static final long serialVersionUID = 1L;

	public Pixel() {
		this.shownColor = Color.WHITE;
	}
	
	public Pixel(Color c) {
		this.shownColor = c;
		setBackground(c);
	}
	
	public void setShownColor(Color c) {
		this.shownColor = c;
		setBackground(c);
	}
}
