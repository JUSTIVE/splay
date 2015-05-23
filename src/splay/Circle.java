package splay;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Circle extends JPanel{
	private Color color;
	private int width;
	private int height;
	private Color bcolor; 
	public Circle(Color color)
	{
		width=87;
		height=87;
		this.color=color;
		bcolor=new Color(0,0,0,0);
		repaint();
	}
	public Circle(Color color,int width,int height)
	{
		this.width=width;
		this.height=height;
		this.color=color;
		bcolor=new Color(0,0,0,0);
		repaint();
	}
	public Circle(Color color,int width,int height,Color bcolor)
	{
		this.width=width;
		this.height=height;
		this.color=color;
		this.setBackground(bcolor);
		repaint();
	}
	public void SetColor(Color c)
	{
		this.color=c;
		//repaint();
	}
	public void paintComponent(Graphics graphics)
	{
		//super.paintComponent(graphics);
		graphics.fillOval(0,0, width, height);
		//Graphics2D ga=(Graphics2D)graphics;
		//ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	    //ga.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		setForeground(color);
		setBackground(bcolor);
		//super.paintComponent(ga);
	}
}
class CirclePanel extends JPanel
{
	public CirclePanel(int width,int height){
		setVisible(true);
		setSize(width,height+3);
		setLayout(null);
		setBackground(new Color(0,0,0,0));
		Circle shadow=new Circle(new Color(0,0,0,77));
		shadow.setVisible(true);
		shadow.setSize(width-3,height);
		shadow.setLocation(0,3);
		super.add(shadow,-1,0);
	}
}
