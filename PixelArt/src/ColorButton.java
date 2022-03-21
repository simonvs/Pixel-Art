import java.awt.Color;
import javax.swing.*;

/**
 * Esta clase es un botón que será extendida por ambos tipos de botones: los pixeles
 * y los botones para seleccionar el color.
 * @author Simón Vergara
 */

public class ColorButton extends JButton{

	private static final long serialVersionUID = 1L;
	protected Color shownColor;
	
	public Color getShownColor() {
		return shownColor;
	}
}