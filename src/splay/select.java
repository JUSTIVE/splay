package splay;

import java.awt.Color;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class select extends JPanel{
	public int width = 600;
	public int height = 800;
	public select(int x,int y,String text)
	{
		setBackground(Color.white);
		setSize(width,170);
		setLocation(x,y);
		setLayout(null);
		JLabel label = new JLabel(text);
		label.setFont(new Font("¸¼Àº °íµñ",Font.PLAIN,30));
		label.setSize(300,50);
		label.setLocation(85,60);
		add(label);
	}
	public select(int x,int y,String text,Color color)
	{
		setBackground(Color.white);
		setSize(width,170);
		setLocation(x,y);
		setLayout(null);
		JLabel label = new JLabel(text);
		label.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,28));
		label.setForeground(color);
		label.setSize(300,50);
		label.setLocation(85,60);
		add(label);
	}

}
