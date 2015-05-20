package splay;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Circle extends JPanel{
	private Color color;
	public Circle(Color color)
	{
		this.color=color;
	}
	public void paintComponent(Graphics graphics)
	{
		super.paintComponent(graphics);
		graphics.fillOval(0,0, 87, 87);
		Graphics2D ga=(Graphics2D)graphics;
		ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	    ga.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		setForeground(color);
		setBackground(new Color(00,00,00,00));
		super.paintComponent(ga);
	}
}
