import java.awt.Color;

/**
 * Este objeto corresponde a un botón para seleccionar el color con el que se va a pintar, 
 * no puede cambiar de color.
 * @author Simón Vergara
 */

public class ColorSelector extends ColorButton{
	
	private static final long serialVersionUID = 1L;

	public ColorSelector(Color c) {
		this.shownColor = c;
		setBackground(c);
	}
}