package splay;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

final class Shadow extends JPanel{ 
	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D)g;
		GradientPaint gp=new GradientPaint(0, 0, new Color(0,0,0,77), 0,6,new Color(0,0,0,0));
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, super.getWidth(), 6);
	}
}

public class PaperPanel extends JPanel{
	public PaperPanel(int width,int height){
		setVisible(true);
		setSize(width, height+6);
		setLayout(null);
		Shadow shadow=new Shadow();
		shadow.setVisible(true);
		shadow.setSize(width,6);
		shadow.setLocation(0,height);
		add(shadow);
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

class IconPanel extends JPanel{
	private Image image;
	int width,height;
	public IconPanel(String location,int width,int height)
	{
		this.width=width;
		this.height=height;
		setSize(height,width);
		try{
			image = ImageIO.read(new File(location));
		}
		catch(IOException ex){}
		repaint();
	}
	public void paint(Graphics G)
	{
		super.paintComponents(G);
		setBackground(new Color(0,0,0,0));
		G.drawImage(image,0,0,this.getWidth(),this.getHeight(), this);
	}
	
	@Override
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
		
	}
	
}