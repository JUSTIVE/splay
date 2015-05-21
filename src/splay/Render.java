package splay;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.rmi.server.LogStream;
public class Render {
	public JFrame mainframe;
	public JFrame logoframe;
	public int width=600;
	public int height=800;
	public int level;
	public int posX,posY;
	int Px,Py;
	Render(){
		level=0;
		mainframe=new JFrame();
		logoframe=new JFrame();
		posX=16;
		posY=39;
		
	}

	public void draw()
	{
		drawlogos();
		//drawmain();
	}
//inital start screen
	public void drawlogos()
	{
		logoframe.setUndecorated(true);
		logoframe.setResizable(false);
		logoframe.setVisible(true);
		logoframe.getContentPane().setLayout(null);
		logoframe.setSize(width, height);
		logoframe.setLocation(0,0);
		logoframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel allover=new JPanel();
		allover.setLayout(null);
		allover.setBounds(0, 0, width, height);
		allover.addMouseListener(new logotomain());
		allover.addMouseListener(new Moveframe());
		allover.addMouseMotionListener(new Movingframe());
		allover.setBackground(Color.decode("#3f51b5"));
		//mainframe.removeAll();
		
		
		JLabel title=new JLabel("S PLAY");
		title.setVisible(true);
		title.setFont(new Font("HY°­B",Font.PLAIN,42));
		title.setForeground(Color.decode("#FFFFFF"));
		title.setBounds(75, 250, 150, 70);
		allover.add(title);
		logoframe.add(allover);
	}
	public void drawmain()
	{	
		mainframe.setUndecorated(true);
		mainframe.setResizable(false);
		mainframe.setVisible(true);
		mainframe.getContentPane().setLayout(null);
		mainframe.setSize(width, height);
		mainframe.setLocation(Px,Py);
		mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PaperPanel titlecontainer=new PaperPanel(width,210);
		titlecontainer.setLocation(0, 0);
			
		PaperPanel plaincontainer=new PaperPanel(570,560);
		plaincontainer.setLocation(15,227);
				
		JPanel paper=new JPanel();
		paper.setVisible(true);
		paper.setLayout(null);
		paper.setSize(width, height);
		paper.setBackground(Color.decode("#e5e5e5"));
		paper.setLocation(0, 0);
		
		JPanel plain=new JPanel();
		plain.setVisible(true);
		plain.setLayout(null);
		plain.setBackground(Color.decode("#ffffff"));
		plain.setSize(570,560);
		plain.setLocation(0, 0);
		
		CirclePanel exitcontainer=new CirclePanel(87, 87);
		exitcontainer.setLocation(495, 165);
		
		Circle exit=new Circle(Color.decode("#ef5350"));
		exit.setVisible(true);
		exit.setLayout(null);
		exit.addMouseListener(new ClickcolorCircle());
		exit.setLocation(0,0);
		exit.setSize(87,87);
		
		IconPanel exiticon=new IconPanel(".\\images\\exit.png",45,45);
		exiticon.setLocation(22,22);
			
		JPanel notibar=new JPanel();
		notibar.addMouseListener(new Moveframe());
		notibar.addMouseMotionListener(new Movingframe());
		notibar.setVisible(true);
		notibar.setBackground(new Color(0,0,0,77));
		notibar.setSize(width, 20);
		notibar.setLocation(0, 0);
		
		JPanel title=new JPanel();
		title.setVisible(true);
		title.setLayout(null);
		title.setBackground(Color.decode("#3f51b5"));
		title.setSize(width, 210);
		title.setLocation(0,0);
		
		PaperPanel playbuttoncontainer=new PaperPanel(105,105);
		playbuttoncontainer.setLocation(57,173);
		
		JPanel playbutton=new JPanel();
		playbutton.setVisible(true);
		playbutton.setLayout(null);
		playbutton.addMouseListener(new Clickcolor());
		playbutton.setBackground(Color.decode("#5c6bc0"));
		playbutton.setSize(105, 105);
		playbutton.setLocation(0,0);
		
		IconPanel playicon=new IconPanel(".\\images\\play.png",45,45);
		playicon.setLocation(30,30);
		
		PaperPanel creditcontainer=new PaperPanel(105,105);
		creditcontainer.setLocation(233,173);
		
		JPanel creditbutton=new JPanel();
		creditbutton.setVisible(true);
		creditbutton.setLayout(null);
		creditbutton.addMouseListener(new Clickcolor());
		creditbutton.setBackground(Color.decode("#5c6bc0"));
		creditbutton.setSize(105, 105);
		creditbutton.setLocation(0,0);
		
		IconPanel crediticon=new IconPanel(".\\images\\credit.png",45,45);
		crediticon.setLocation(30,30);
		
		PaperPanel aboutcontainer=new PaperPanel(105,105);
		aboutcontainer.setLocation(409,173);
		
		JPanel aboutbutton=new JPanel();
		aboutbutton.setVisible(true);
		aboutbutton.setLayout(null);
		aboutbutton.addMouseListener(new Clickcolor());
		aboutbutton.setBackground(Color.decode("#5c6bc0"));
		aboutbutton.setSize(105, 105);
		aboutbutton.setLocation(0,0);
		
		IconPanel abouticon=new IconPanel(".\\images\\about.png",45,45);
		abouticon.setLocation(30,30);
		
		JLabel titlename=new JLabel("S play");
		titlename.setVisible(true);
		titlename.setFont(new Font("HY°­B",Font.PLAIN,42));
		titlename.setForeground(Color.decode("#FFFFFF"));
		titlename.setBounds(75, 130, 150, 70);
				
		//addline
		title.add(notibar);
		title.add(titlename);
		titlecontainer.add(title);
		
		playbuttoncontainer.add(playbutton);
		creditcontainer.add(creditbutton);
		aboutcontainer.add(aboutbutton);
		
		plain.add(aboutcontainer);
		plain.add(creditcontainer);
		plain.add(playbuttoncontainer);
		
		playbutton.add(playicon);
		creditbutton.add(crediticon);
		aboutbutton.add(abouticon);
		
		plaincontainer.add(plain);
				
		exitcontainer.add(exit,0,0);
		exit.add(exiticon,2,0);
				
		paper.add(exitcontainer);
		paper.add(plaincontainer);
		paper.add(titlecontainer);
		mainframe.add(paper);
	}
	class Clickcolor extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e)
		{
			JPanel play=(JPanel)e.getSource();
			play.setBackground(Color.decode("#3949AB"));
		}
		
		@Override
		public void mouseReleased(MouseEvent e)
		{
			JPanel play=(JPanel)e.getSource();
			play.setBackground(Color.decode("#5c6bc0"));
		}
	}
	class ClickcolorCircle extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e)
		{
			if(Math.sqrt(Math.pow(e.getX()-(44),2)+Math.pow(e.getY()-(44), 2))<43)
			{
				Circle btn=(Circle)e.getSource();
				btn.SetColor(Color.decode("#C62828"));
				btn.repaint();
				//TODO:: clear background glitter
			}
		}
		public void mouseReleased(MouseEvent e)
		{
			if(Math.sqrt(Math.pow(e.getX()-(44),2)+Math.pow(e.getY()-(44), 2))<43)
			{
				Circle btn=(Circle)e.getSource();
				btn.SetColor(Color.decode("#ef5350"));
				btn.setBackground(new Color(0,0,0,0));
				mainframe.dispose();
				System.exit(1);
			}
		}
	}
	class logotomain extends MouseAdapter{
		public void mouseClicked(MouseEvent e)
		{
			drawmain();
			//JPanel tar=(JPanel)e.getSource();
			//for()
			
			logoframe.setVisible(false);
			level++;
			
		}
	}
	class Moveframe extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e)
		{
			Px=e.getX();
			Py=e.getY();
		}	
	}
	class Movingframe extends MouseMotionAdapter{
		public void mouseDragged(MouseEvent evt)
		{
			System.out.println("move!");
			JFrame target=new JFrame();
			switch(level)
			{
			case 0:
				target=logoframe;
				break;
			case 1:
				target=mainframe;
				break;
			}
			
			target.setLocation(target.getLocation().x+evt.getX()-Px,target.getLocation().y+evt.getY()-Py);
			//target.setLocation(posX+evt.getX()-Px,posY+evt.getY()-Py);
			posX=target.getLocation().x;
			posX=target.getLocation().y;
		}
	}
}
