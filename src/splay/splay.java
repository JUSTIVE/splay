package splay;

import java.awt.*;

import javax.swing.*;

public class splay {
	public static void main(String []args){
		
		int width=600;
		int height=800;
		
		JFrame frame=new JFrame();
		frame.setBounds(300, 300, width, height);
		frame.setUndecorated(false);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		JPanel paper=new JPanel();
		paper.setVisible(true);
		paper.setPreferredSize(new Dimension(width,height));
		paper.setBackground(Color.decode("#e5e5e5"));
		paper.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		
		JPanel plain=new JPanel();
		plain.setVisible(true);
		plain.setPreferredSize(new Dimension(570,560));
		plain.setBackground(Color.decode("#ffffff"));
		plain.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		plain.setLocation(new Point(15,225));
		
		JButton exit=new JButton();
		
		JPanel notibar=new JPanel();
		notibar.setVisible(true);
		notibar.setPreferredSize(new Dimension(width,20));
		notibar.setBackground(new Color(0,0,0,77));
		
		JPanel title=new JPanel();
		title.setPreferredSize(new Dimension(width,210));
		title.setBackground(Color.decode("#3f51b5"));
		title.setVisible(true);
		title.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		
		Label titlename=new Label("S play");
		titlename.setVisible(true);
		titlename.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,35));
		titlename.setForeground(Color.decode("#FFFFFF"));
		
		//titlename.setAlignmentX(135);
		//titlename.setAlignmentY(75);
		title.add(notibar);
		title.add(titlename);
		paper.add(title);
		paper.add(plain);
		frame.add(paper);
		frame.setVisible(true);
	}
}
