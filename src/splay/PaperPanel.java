package splay;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

class Shadow extends JPanel{
	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		Graphics2D g2d=(Graphics2D)g;
		GradientPaint gp=new GradientPaint(0, 0, new Color(0,0,0,0), 0, 6, Color.BLACK);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, 600, 6);
	}
}

public class PaperPanel extends JPanel{
	public PaperPanel(int width,int height){
		setVisible(true);
		setSize(width, height+3);
		JPanel shadow=new JPanel();
		
	}
}
