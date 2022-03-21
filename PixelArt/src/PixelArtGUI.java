import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Esta es la interfaz gráfica en la que se va a dibujar, contiene todos los botones, 
 * paneles y un a clase PixelArtEngine para controlar el programa.
 * @author Simón Vergara
 *
 */
public class PixelArtGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private PixelArtEngine engine;
	private JPanel boardPanel;
	private JPanel selectorPanel;
	private ArrayList<Pixel> pixelBoard;
	private JPanel actualColorPanel;
	private JLabel actualColorLabel;
	private JButton paintAllButton;
	private JButton eraseAllButton;
	private JButton saveImageButton;
	
	public PixelArtGUI() {
		
		setBounds(100, 0, 625, 500);
		setTitle("PixelArt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		pixelBoard = new ArrayList<Pixel>();
		for(int i = 0;i<400;i++) {
			Pixel pix = new Pixel(Color.WHITE);
			pix.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if(e.getButton()==1)
						pix.setShownColor(engine.getActualColor());
					if(e.getButton()==3)
						pix.setShownColor(Color.WHITE);
				}
			});
			pixelBoard.add(pix);
		}
		engine = new PixelArtEngine(pixelBoard);
			
		ColorSelector[] selectorBoard = new ColorSelector[12];
		Color[] colores = {Color.BLACK, Color.RED, new Color(255,150,0),
							Color.DARK_GRAY, Color.YELLOW, Color.GREEN,
							Color.GRAY, Color.CYAN, Color.BLUE, 
							Color.LIGHT_GRAY, Color.PINK, Color.MAGENTA};
		for(int i = 0;i<12;i++) {
			selectorBoard[i] = new ColorSelector(colores[i]);
		}
		
		boardPanel = new JPanel();
		boardPanel.setBounds(200, 25, 400, 400);
		boardPanel.setVisible(true);
		boardPanel.setLayout(new GridLayout(20, 20));
		for(int i = 0;i<400;i++)
			boardPanel.add(pixelBoard.get(i));
		add(boardPanel);
		
		actualColorPanel = new JPanel();
		actualColorPanel.setBounds(125, 150, 50, 50);
		actualColorPanel.setBackground(Color.BLACK);
		actualColorPanel.setVisible(true);
		add(actualColorPanel);
		
		actualColorLabel = new JLabel("Color Actual:");
		actualColorLabel.setBounds(25, 160, 100, 25);
		actualColorLabel.setVisible(true);
		add(actualColorLabel);
		
		selectorPanel = new JPanel();
		selectorPanel.setBounds(25, 225, 150, 200);
		selectorPanel.setLayout(new GridLayout(4, 3));
		selectorPanel.setVisible(true);
		for(ColorSelector n: selectorBoard) {
			n.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					engine.setActualColor(n.getShownColor());
					actualColorPanel.setBackground(n.getShownColor());
				}
			});
			selectorPanel.add(n);
		}
		add(selectorPanel);
		
		paintAllButton = new JButton("Pintar Todo");
		paintAllButton.setBounds(25, 25, 150, 25);
		paintAllButton.setVisible(true);
		paintAllButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				engine.paintAll(engine.getActualColor());
			}
		});
		add(paintAllButton);
		
		eraseAllButton = new JButton("Borrar Todo");
		eraseAllButton.setBounds(25, 60, 150, 25);
		eraseAllButton.setVisible(true);
		eraseAllButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				engine.eraseAll();
			}
		});
		add(eraseAllButton);
		
		saveImageButton = new JButton("Guardar Imagen");
		saveImageButton.setBounds(25, 95, 150, 25);
		saveImageButton.setVisible(true);
		saveImageButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveImage();
			}
		});
		add(saveImageButton);
		
		
		setVisible(true);
	}
	
	private void saveImage(){
	    BufferedImage imagebuf=null;
	    try {
	    	imagebuf = new Robot().createScreenCapture(boardPanel.getBounds());
	    } catch (AWTException e1) {
	    	e1.printStackTrace();
	    }
	    Graphics2D graphics2D = imagebuf.createGraphics();
	    boardPanel.paint(graphics2D);
	    try {
	    	String name = JOptionPane.showInputDialog("Ingresa el nombre del archivo a guardar:");
	    	ImageIO.write(imagebuf,"jpeg", new File(name+".jpeg"));
	    	System.out.println("El archivo se guardó en la carpeta PixelArt");
	    } catch (Exception e) {
	    	System.out.println("Error al guardar la imagen");
	    }
	}
	public static void main(String[] args) {
		new PixelArtGUI();
	}
}