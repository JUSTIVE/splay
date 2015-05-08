package splay;

import java.awt.*;

import javax.swing.*;


public class splay {
	
	public static void main(String []args){
		
		int width=600;
		int height=800;
		
		JFrame frame=new JFrame();
		frame.setSize(width+15, height+39);
		frame.setLocation(15,39);
		frame.setUndecorated(false);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		JPanel paper=new JPanel();
		paper.setVisible(true);
		paper.setSize(width, height);
		paper.setBackground(Color.decode("#e5e5e5"));
		paper.setLayout(null);
		paper.setLocation(0, 0);
		
		JPanel plain=new JPanel();
		plain.setVisible(true);
		plain.setBackground(Color.decode("#ffffff"));
		plain.setSize(570,560);
		plain.setLocation(15, 227);
		
		Circle exit=new Circle();
		exit.setVisible(true);
		exit.setLocation(495,165);
		exit.setSize(87,87);
				
		JPanel notibar=new JPanel();
		notibar.setVisible(true);
		notibar.setBackground(new Color(0,0,0,77));
		notibar.setSize(width, 20);
		notibar.setLocation(0, 0);
		
		JPanel title=new JPanel();
		title.setVisible(true);
		title.setBackground(Color.decode("#3f51b5"));
		title.setLayout(null);
		title.setSize(width, 210);
		title.setLocation(0,0);
		
		Label titlename=new Label("S play");
		titlename.setVisible(true);
		//titlename.setFont(new Font("Adobe °íµñ Std",Font.BOLD,42));
		titlename.setFont(new Font("Adobe °íµñ Std B",Font.BOLD,42));
		titlename.setForeground(Color.decode("#FFFFFF"));
		titlename.setBounds(75, 130, 150, 70);
		
		title.add(notibar);
		title.add(titlename);
		
		paper.add(exit);
		paper.add(title);
		paper.add(plain);
		
		
		frame.add(paper);
		frame.setVisible(true);
	}
}
