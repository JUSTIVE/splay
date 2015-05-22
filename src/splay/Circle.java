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
	public void SetColor(Color c)
	{
		this.color=c;
		//repaint();
	}
	public void paintComponent(Graphics graphics)
	{
		//super.paintComponent(graphics);
		graphics.fillOval(0,0, 87, 87);
		//Graphics2D ga=(Graphics2D)graphics;
		//ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	    //ga.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		setForeground(color);
		setBackground(new Color(0,0,0,0));
		//super.paintComponent(ga);
	}
}
class CirclePanel extends JPanel
{
	public CirclePanel(int width,int height){
		setVisible(true);
		setSize(width,height+2);
		setLayout(null);
		setBackground(new Color(0,0,0,0));
		Circle shadow=new Circle(new Color(0,0,0,77));
		shadow.setVisible(true);
		shadow.setSize(width,height);
		shadow.setLocation(0,2);
		super.add(shadow,-1,0);
	}
}