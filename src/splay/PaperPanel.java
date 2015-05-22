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
	private int depth;
	public Shadow()
	{
		depth=6;
	}
	public void setDepth(int depth)
	{
		this.depth=depth;
	}
	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D)g;
		GradientPaint gp=new GradientPaint(0, 0, new Color(0,0,0,77), 0,depth,new Color(0,0,0,0));
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, super.getWidth(), depth);
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
<<<<<<< HEAD
=======
	public PaperPanel(int width,int height,int depth){
		setVisible(true);
		setSize(width, height+depth);
		setLayout(null);
		Shadow shadow=new Shadow();
		shadow.setVisible(true);
		shadow.setDepth(depth);
		shadow.setSize(width,depth);
		shadow.setLocation(0,height);
		add(shadow);
	}
>>>>>>> origin/master
}

class IconPanel extends JPanel{
	private Image image;
	int width,height;
	Color bColor;
	public IconPanel(String location,int width,int height)
	{
		this.width=width;
		this.height=height;
		setSize(height,width);
		setBackground(new Color(0,0,0,0));
		try{
			image = ImageIO.read(new File(location));
		}
		catch(IOException ex){}
		repaint();
	}
	public IconPanel(String location,int width,int height,Color bColor)
	{
		this.width=width;
		this.height=height;
		this.bColor=bColor;
		setSize(height,width);
		setBackground(bColor);
		try{
			image = ImageIO.read(new File(location));
		}
		catch(IOException ex){}
		repaint();
	}
	public void paint(Graphics G)
	{
		super.paintComponents(G);
<<<<<<< HEAD
		setBackground(new Color(0,0,0,0));
=======
		
>>>>>>> origin/master
		G.drawImage(image,0,0,this.getWidth(),this.getHeight(), this);
	}
	@Override
	public void paintComponent(Graphics G)
	{
		super.paintComponent(G);
<<<<<<< HEAD
		
	}
=======
		setBackground(new Color(0,0,0,0));
		
	}
	
>>>>>>> origin/master
	
}