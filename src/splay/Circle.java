package splay;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Circle extends JPanel{
	@Override
	public void paintComponent(Graphics graphics)
	{
		/*
		Graphics2D ga=(Graphics2D)graphics;
		ga.setColor(Color.decode("#ef5350"));
		ga.fillOval(495, 165, 87, 87);
		//repaint();
		*/
		/*super.paintComponents(graphics);
		Graphics2D ga=(Graphics2D)graphics;
		Ellipse2D.Double circle = new Ellipse2D.Double(495,165,87,87);
		ga.fill(circle);
		repaint();
		*/
		super.paintComponent(graphics);
		graphics.fillOval(0,0, 87, 87);
		Graphics2D ga=(Graphics2D)graphics;
		//RenderingHints rh = new RenderingHints(
	            
	    ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	    
		//ga.setRenderingHints(Graphics2D.ANTIALIASING,Graphics2D.ANTIALIAS_ON);
		setForeground(Color.decode("#ef5350"));
		setBackground(new Color(00,00,00,00));
	}
}
