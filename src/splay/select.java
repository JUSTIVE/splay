package splay;

import java.awt.Color;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class select extends JPanel{
	public int width = 600;
	public int height = 800;
	public select(int x,int y,String text,Color color)
	{
		panelinitialize(x, y);
		
		JLabel label = new JLabel(text);
		label.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,28));
		label.setForeground(color);
		label.setSize(300,50);
		label.setLocation(100,60);
		
		add(label);
	}
	public void panelinitialize(int x,int y)
	{
		setBackground(Color.white);
		setSize(width,170);
		setLocation(x,y);
		setLayout(null);
	}

}
