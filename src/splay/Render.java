package splay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
public class Render {
	//각각의 액티비티별로 프레임을 구성한다
	public JFrame logoframe;	//맨 처음 로고가 나오는 부분
	public JFrame mainframe;	//메인 메뉴가 있는 프레임
	public JFrame playmenu;		//실행 화면이 있는 프레임
	public JFrame scoreview;	//스코어를 볼 수 있는 프레임
	public JFrame aboutview;	
	public JFrame chapterview; 	//
	private int width=600;		//전체 프레임의 폭
	private int height=800;		//전체 프레임의 높이
	private int level;			//현재 실행중인 프레임의 번호
	private int posX,posY;		//화면이 움직일때 사용되는 좌표값
	private int score[][];		//점수를 저장할 배열
	private int accuracy[];	//과목당 정확도
	private int achievement[];	//진행상황
	private int choosedSub;	//현재 선책한 과목의 번호 
	private int choosedChp; //현재 선택한 과목의 챕터 번호
	private int choosedNum; //현재 선택한 과목의 챕터의 문제 번호
	private JLabel chaptertitle;
	private String arch_ans[]= {"<html>2</html>","<html>3</html>","<html>4</html>","<html>3</html>","<html>3</html>","<html>2</html>","<html>3</html>","<html>2</html>","<html>2</html>","<html>3</html>","<html>4</html>","<html>2</html>","<html>3</html>","<html>3</html>","<html>1</html>","<html>4</html>","<html>2</html>","<html>3</html>","<html>2</html>","<html>3</html>","<html>3</html>","<html>3</html>","<html>2</html>","<html>3</html>"};
	private String archchose[][]= {{"①CPU","②캐시","③입출력 장치","④기억장치"}, {"①8비트","②32비트","③24비트","④64비트"}, {"①12","②1024","③2048","④4096"},{"①주소 버스","②데이터 버스","③I/O 버스","④제어 버스"},{"①제어 비트","②부호 비트","③패리티 비트","④올림수 비트"} ,{"①클러스터 컴퓨터","②임베디드 컴퓨터","③MPP","④파이프라인 슈퍼컴퓨터"} , {"①스택","②제어 유니트","③레지스터 세트","④ALU"},{"①인출 사이클","②간접 사이클","③제어 사이클","④실행 사이클"},{"①0.5ns","②1.5ns","③2ns","④6ns"},{"①10","②13","③14","④40"},{"①1개","②2개","③4개","④8개"},{"①1KByte","②2KByte","③4KByte","④8KByte"},{"①한 번","②두 번","③세 번","④액세스하지 않는다."},{"①-12","②48","③-24","④232"},{"①AND 연산","②OR 연산","③XOR 연산","④NOT연산"},{"①AND 연산","②OR 연산","③XOR 연산","④NOT연산"},{"①00101011","②01010110","③11010110","④11101011"},{"①00101101","②10101101","③10110111","④10110110"},{"①지수 조정","②정규화","③지수 덧셈"," ④가수 덧셈"},{"①CBR","②CAR","③IR","④PC","①RAM","②CAM","③ROM","④DISK"},{"①호출(CALL)","②복귀(RET)","③점프(JUMP)", "④조건부 호출(conditional CALL)"},{"①SBR의 내용","②사상(mapping)의 결과값","③연산 필드 비트들","④주소 필드(ADF)의 값"},{"①4개","②8개","③16개","④32개"},{"①8개","②16개","③32개","④64개"}};
	private String Subject []= {"컴퓨터 아키텍쳐","자료 구조","C 언어"};//각각의 과목을 저장하는 문자열 배열
	int Px,Py;
	Render()// 화면에 데이터를 출력하는 render 클래스의 생성자
	{
		level=0;
		mainframe=new JFrame();
		logoframe=new JFrame();
		playmenu=new JFrame();
		scoreview=new JFrame();
		aboutview=new JFrame();
		chapterview=new JFrame();
		chaptertitle=new JLabel();
		posX=500;
		posY=150;
		//점수 배열 할당 및 초기화
		score=new int[3][100];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<100;j++)
				score[i][j]=0;
		}
		achievement=new int[3];
		for(int i=0;i<3;i++)
			achievement[i]=0;
		accuracy=new int[3];
		for(int i=0;i<3;i++)
			accuracy[i]=0;
		choosedSub=1;
		
	}

	public void draw()
	{
		//가장 처음의 logoframe을 출력한다.
		drawlogos();
		logoframe.repaint();
	}
//Initial start screen
	public void drawlogos()
	{
		logoframe.setUndecorated(true);
		logoframe.setResizable(false);
		logoframe.setVisible(true);
		logoframe.getContentPane().setLayout(null);
		logoframe.setSize(width, height);
		logoframe.setLocation(posX,posY);
		logoframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel allover=new JPanel();
		allover.setLayout(null);
		allover.setBounds(0, 0, width, height);
		allover.addMouseListener(new TransScreen());
		allover.addMouseListener(new Moveframe());
		allover.addMouseMotionListener(new Movingframe());
		allover.setBackground(Color.decode("#00BCD4"));
						
		JLabel title=new JLabel("S PLAY");
		title.setVisible(true);
		title.setFont(new Font("맑은 고딕",Font.BOLD,42));
		title.setForeground(Color.decode("#FFFFFF"));
		title.setBounds(75, 250, 150, 70);
		
		JLabel corp=new JLabel("순천향대학교");
		corp.setVisible(true);
		corp.setFont(new Font("맑은 고딕",Font.PLAIN,13));
		corp.setForeground(Color.decode("#FFFFFF"));
		corp.setBounds(75, 290, 300, 70);
		
		JLabel major=new JLabel("컴퓨터소프트웨어공학과");
		major.setVisible(true);
		major.setFont(new Font("맑은 고딕",Font.PLAIN,13));
		major.setForeground(Color.decode("#FFFFFF"));
		major.setBounds(75, 315, 300, 70);
		
		JLabel dev=new JLabel("김민상 이기백 김민기");
		dev.setVisible(true);
		dev.setFont(new Font("맑은 고딕",Font.BOLD,15));
		dev.setForeground(Color.decode("#FFFFFF"));
		dev.setBounds(75, 215, 300, 70);
		
		JLabel java=new JLabel("JAVA 프로그래밍");
		java.setVisible(true);
		java.setFont(new Font("맑은 고딕",Font.BOLD,18));
		java.setForeground(Color.decode("#FFFFFF"));
		java.setBounds(75, 340, 300, 70);
		
		IconPanel exiticon=new IconPanel(".\\images\\exit.png",30,30);
		exiticon.setLocation(555,15);
		exiticon.addMouseListener(new ExitButton());
		
		allover.add(exiticon);
		allover.add(dev);
		allover.add(major);
		allover.add(corp);
		allover.add(title);
		allover.add(java);
		logoframe.add(allover);
	}
	public void drawscoreview()
	{
		scoreview.setName("scoreviewdeclared");
		scoreview.setUndecorated(true);
		scoreview.setResizable(false);
		scoreview.setVisible(true);
		scoreview.getContentPane().setLayout(null);
		scoreview.setSize(width, height);
		scoreview.setLocation(posX,posY);
		scoreview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel paper=new JPanel();
		paper.setVisible(true);
		paper.setLayout(null);
		paper.setSize(width, height);
		paper.setBackground(Color.decode("#FAFAFA"));
		paper.setLocation(0, 0);
				
		JPanel notibar=new JPanel();
		notibar.addMouseListener(new Moveframe());
		notibar.addMouseMotionListener(new Movingframe());
		notibar.setVisible(true);
		notibar.setBackground(new Color(0,0,0,77));
		notibar.setSize(width, 20);
		notibar.setLocation(0,0);
		
		JPanel title = new JPanel();
		title.setBackground(Color.decode("#00BCD4"));
		title.setSize(width,100);
		title.setLocation(0,0);
		title.setLayout(null);
		
		JLabel titlelabel = new JLabel("SCORE");
		titlelabel.setSize(200,50);
		titlelabel.setForeground(Color.decode("#FFFFFF"));
		titlelabel.setLocation(100,32);
		titlelabel.setFont(new Font("맑은 고딕",Font.BOLD,32));
		
		IconPanel exiticon=new IconPanel(".\\images\\exit.png",45,45,Color.decode("#00BCD4"));
		exiticon.setLocation(520,37);
		exiticon.addMouseListener(new ExitButton());
		
		IconPanel backicon=new IconPanel(".\\images\\back.png",45,45,Color.decode("#00BCD4"));
		backicon.setLocation(20,37);
		backicon.addMouseListener(new BackMenu());
		
		PaperPanel item1=new PaperPanel(width, 170, 3);
		item1.setLocation(0,100);
		PaperPanel item2=new PaperPanel(width, 170, 3);
		item2.setLocation(0,270);
		PaperPanel item3=new PaperPanel(width, 170, 3);
		item3.setLocation(0,440);
		
		select menu1 = new select(0, 500, Subject[0],Color.decode("#00bcd4"));
		menu1.setLocation(0,0);
		select menu2 = new select(0, 500, Subject[1],Color.decode("#00bcd4"));
		menu2.setLocation(0,0);
		select menu3 = new select(0, 500, Subject[2],Color.decode("#00bcd4"));
		menu3.setLocation(0,0);
				
		IconPanel menu1icon=new IconPanel(".\\images\\item1.png",45,45,Color.decode("#AFAFAF"));
		menu1icon.setLocation(30,60);
		IconPanel menu2icon=new IconPanel(".\\images\\item2.png",45,45,Color.decode("#AFAFAF"));
		menu2icon.setLocation(30,60);
		IconPanel menu3icon=new IconPanel(".\\images\\item3.png",45,45,Color.decode("#AFAFAF"));
		menu3icon.setLocation(30,60);
		
		piechart sub1=new piechart(accuracy[0]);
		sub1.setVisible(true);
		sub1.setBounds(500,60,80,80);
		
		
		//TODO:: scoreadding
		title.add(exiticon);
		title.add(backicon);
		title.add(titlelabel);
		title.add(notibar);
		
		menu1.add(sub1);
		menu1.add(menu1icon);
		menu2.add(menu2icon);
		menu3.add(menu3icon);
		
		item1.add(menu1);
		item2.add(menu2);
		item3.add(menu3);
		
		paper.add(item1);
		paper.add(item2);
		paper.add(item3);
		
		paper.add(title);
		scoreview.add(paper);
	}
	public void drawaboutview()
	{
		aboutview.setName("aboutviewdeclared");
		aboutview.setUndecorated(true);
		aboutview.setResizable(false);
		aboutview.setVisible(true);
		aboutview.getContentPane().setLayout(null);
		aboutview.setSize(width, height);
		aboutview.setLocation(posX,posY);
		aboutview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PaperPanel titlecontainer=new PaperPanel(width,210,6);
		titlecontainer.setLocation(0, 0);
		
		JPanel paper=new JPanel();
		paper.setVisible(true);
		paper.setLayout(null);
		paper.setSize(width, height);
		paper.setBackground(Color.decode("#FAFAFA"));
		paper.setLocation(0, 0);
		
		JPanel notibar=new JPanel();
		notibar.addMouseListener(new Moveframe());
		notibar.addMouseMotionListener(new Movingframe());
		notibar.setVisible(true);
		notibar.setBackground(new Color(0,0,0,77));
		notibar.setSize(width, 20);
		notibar.setLocation(0,0);
		
		JPanel title=new JPanel();
		title.setVisible(true);
		title.setLayout(null);
		title.setBackground(Color.decode("#00BCD4"));
		title.setSize(width, 100);
		title.setLocation(0,0);
		
		JLabel titlename=new JLabel("Credit + About");
		titlename.setVisible(true);
		titlename.setFont(new Font("맑은 고딕",Font.BOLD,32));
		titlename.setForeground(Color.decode("#FFFFFF"));
		titlename.setBounds(100, 32, 400, 50);
				
		IconPanel exiticon=new IconPanel(".\\images\\exit.png",45,45,Color.decode("#00BCD4"));
		exiticon.setLocation(520,37);
		exiticon.addMouseListener(new ExitButton());
		
		IconPanel backicon=new IconPanel(".\\images\\back.png",45,45,Color.decode("#00BCD4"));
		backicon.setLocation(20,37);
		backicon.addMouseListener(new BackMenu());
		//title over///////////////////////////////////////////////////////
		
		PaperPanel about = new PaperPanel(600,250);
		about.setBackground(Color.decode("#fafafa"));
		about.setLocation(0,100);
		about.setLayout(null);
		
		JPanel aboutpanel = new JPanel();
		aboutpanel.setBackground(Color.decode("#00BCD2"));
		aboutpanel.setSize(70,70);
		aboutpanel.setLocation(20,20);
		aboutpanel.setLayout(null);
				
		IconPanel abouticon=new IconPanel(".\\images\\about.png",45,45);
		abouticon.setLocation(12,12);
		
		JLabel aboutlabel = new JLabel("About");
		aboutlabel.setSize(500,50);
		aboutlabel.setFont(new Font("맑은 고딕",Font.PLAIN,32));
		aboutlabel.setLocation(100,30);
		
		
		String x = "<html>이 프로그램은 사용자의 학습능력을 도와주는<br>학습 프로그램 입니다.</html>";
		JLabel aboutinsert = new JLabel(x);
		aboutinsert.setSize(800,100);
		aboutinsert.setFont(new Font("맑은 고딕",Font.PLAIN,25));
		aboutinsert.setLocation(30,80);
		
		PaperPanel credit = new PaperPanel(600,250);
		credit.setBackground(Color.decode("#fafafa"));
		credit.setLocation(0,350);
		credit.setLayout(null);
		
		IconPanel crediticon = new IconPanel(".\\images\\credit.png",45,45);
		crediticon.setLocation(12,12);
		
				
		String credits[] = {"Credit", "<html>20144575 김민상<br>20144592 이기백<br>20144564 김민기"};
				
		JPanel creditpanel = new JPanel();
		creditpanel.setBackground(Color.decode("#00BCD2"));
		creditpanel.setSize(70,70);
		creditpanel.setLocation(20,20);
		creditpanel.setLayout(null);
		
		JLabel creaditlabel = new JLabel(credits[0]);
		creaditlabel.setSize(500,50);
		creaditlabel.setFont(new Font("맑은 고딕",Font.PLAIN,32));
		creaditlabel.setLocation(100,30);
		credit.add(creaditlabel);
		
		JLabel creaditinsert = new JLabel(credits[1]);
		creaditinsert.setSize(800,100);
		creaditinsert.setFont(new Font("맑은 고딕",Font.PLAIN,25));
		creaditinsert.setLocation(30,100);
		
		//TODO:: about adding
		
		aboutpanel.add(abouticon);
		about.add(aboutlabel);
		about.add(aboutpanel);
		about.add(aboutinsert);
		
		creditpanel.add(crediticon);
		credit.add(creditpanel);
		credit.add(creaditinsert);
		
		title.add(titlename);
		title.add(notibar);
		title.add(exiticon);
		title.add(backicon);
		titlecontainer.add(title);
		
		
		paper.add(about);
		paper.add(credit);
		paper.add(titlecontainer);
		
		aboutview.add(paper);
		
		
	}
	public void drawplaymenu()
	{
		playmenu.setName("playmenudeclared");
		playmenu.setUndecorated(true);
		playmenu.setResizable(false);
		playmenu.setVisible(true);
		playmenu.getContentPane().setLayout(null);
		playmenu.setSize(width, height);
		playmenu.setLocation(posX,posY);
		playmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PaperPanel titlecontainer=new PaperPanel(width,100,0);
		titlecontainer.setLocation(0, 0);
		
		JPanel paper=new JPanel();
		paper.setVisible(true);
		paper.setLayout(null);
		paper.setSize(width, height);
		paper.setBackground(Color.decode("#FAFAFA"));
		paper.setLocation(0, 0);
		
		JPanel notibar=new JPanel();
		notibar.addMouseListener(new Moveframe());
		notibar.addMouseMotionListener(new Movingframe());
		notibar.setVisible(true);
		notibar.setBackground(new Color(0,0,0,77));
		notibar.setSize(width, 20);
		notibar.setLocation(0,0);
		
		JPanel title = new JPanel();
		title.setBackground(Color.decode("#00BCD4"));
		title.setSize(width,100);
		title.setLocation(0,0);
		title.setLayout(null);
		
		IconPanel exiticon=new IconPanel(".\\images\\exit.png",45,45,Color.decode("#00BCD4"));
		exiticon.setLocation(520,37);
		exiticon.addMouseListener(new ExitButton());
		
		IconPanel backicon=new IconPanel(".\\images\\back.png",45,45,Color.decode("#00BCD4"));
		backicon.setLocation(20,37);
		backicon.addMouseListener(new BackMenu());
				
		JLabel titlelabel = new JLabel("MENU");
		titlelabel.setSize(200,50);
		titlelabel.setForeground(Color.decode("#FFFFFF"));
		titlelabel.setLocation(100,32);
		titlelabel.setFont(new Font("맑은 고딕",Font.BOLD,32));
		//////////////
		PaperPanel item1=new PaperPanel(width, 170, 3);
		item1.setLocation(0,100);
		PaperPanel item2=new PaperPanel(width, 170, 3);
		item2.setLocation(0,273);
		PaperPanel item3=new PaperPanel(width, 170, 3);
		item3.setLocation(0,446);
				
		select menu1 = new select(0, 500, Subject[0],Color.decode("#00bcd4"));
		menu1.setName("SUB1");
		menu1.addMouseListener(new Clickcolorgray());
		menu1.addMouseListener(new subtochp());
		menu1.setLocation(0,0);
		select menu2 = new select(0, 500, Subject[1],Color.decode("#00bcd4"));
		menu2.setName("SUB2");
		menu2.addMouseListener(new Clickcolorgray());
		menu2.addMouseListener(new subtochp());
		menu2.setLocation(0,0);
		select menu3 = new select(0, 500, Subject[2],Color.decode("#00bcd4"));
		menu3.setName("SUB3");
		menu3.addMouseListener(new Clickcolorgray());
		menu3.addMouseListener(new subtochp());
		menu3.setLocation(0,0);
				
		IconPanel menu1icon=new IconPanel(".\\images\\item1.png",45,45,Color.decode("#AFAFAF"));
		menu1icon.setLocation(30,60);
		IconPanel menu2icon=new IconPanel(".\\images\\item2.png",45,45,Color.decode("#AFAFAF"));
		menu2icon.setLocation(30,60);
		IconPanel menu3icon=new IconPanel(".\\images\\item3.png",45,45,Color.decode("#AFAFAF"));
		menu3icon.setLocation(30,60);
		
		JLabel ach1 =new JLabel("achievement");
		ach1.setForeground(Color.decode("#616161"));
		ach1.setFont(new Font("맑은 고딕",Font.BOLD,15));
		ach1.setSize(100,15);
		ach1.setLocation(470, 50);
		JLabel ach2 =new JLabel("achievement");
		ach2.setForeground(Color.decode("#616161"));
		ach2.setFont(new Font("맑은 고딕",Font.BOLD,15));
		ach2.setSize(100,15);
		ach2.setLocation(470, 50);
		JLabel ach3 =new JLabel("achievement");
		ach3.setForeground(Color.decode("#616161"));
		ach3.setFont(new Font("맑은 고딕",Font.BOLD,15));
		ach3.setSize(100,15);
		ach3.setLocation(470, 50);
		
		Integer menu1achie=new Integer(achievement[0]);
		JLabel menu1ach=new JLabel(menu1achie.toString()+"%");		
		menu1ach.setFont(new Font("맑은 고딕",Font.BOLD,28));
		menu1ach.setForeground(Color.decode("#CDDC39"));
		menu1ach.setBounds(500,60,50,50);
		Integer menu2achie=new Integer(achievement[0]);
		JLabel menu2ach=new JLabel(menu2achie.toString()+"%");		
		menu2ach.setFont(new Font("맑은 고딕",Font.BOLD,28));
		menu2ach.setForeground(Color.decode("#CDDC39"));
		menu2ach.setBounds(500,60,50,50);
		Integer menu3achie=new Integer(achievement[0]);
		JLabel menu3ach=new JLabel(menu3achie.toString()+"%");		
		menu3ach.setFont(new Font("맑은 고딕",Font.BOLD,28));
		menu3ach.setForeground(Color.decode("#CDDC39"));
		menu3ach.setBounds(500,60,50,50);
		
		//TODO:: playmenuadding
		title.add(notibar);
		title.add(titlelabel);
		title.add(backicon);
		title.add(exiticon);
		titlecontainer.add(title);
		
		paper.add(titlecontainer);
		
		menu1.add(ach1);
		menu2.add(ach2);
		menu3.add(ach3);
		
		menu1.add(menu1ach);
		menu2.add(menu2ach);
		menu3.add(menu3ach);
		
		menu1.add(menu1icon);
		menu2.add(menu2icon);
		menu3.add(menu3icon);
		
		item1.add(menu1);
		item2.add(menu2);
		item3.add(menu3);
		
		paper.add(item1);
		paper.add(item2);
		paper.add(item3);
		
		playmenu.add(paper);
		
	}
	public void drawchapterview()
	{
		//chapterview.setName("chapterviewdeclared");
		chapterview.setUndecorated(true);
		chapterview.setResizable(false);
		chapterview.setVisible(true);
		chapterview.getContentPane().setLayout(null);
		chapterview.setSize(width, height);
		chapterview.setLocation(posX,posY);
		chapterview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel paper=new JPanel();
		paper.setVisible(true);
		paper.setLayout(null);
		paper.setSize(width, height);
		paper.setBackground(Color.decode("#FAFAFA"));
		paper.setLocation(0, 0);
		
		JPanel notibar=new JPanel();
		notibar.addMouseListener(new Moveframe());
		notibar.addMouseMotionListener(new Movingframe());
		notibar.setVisible(true);
		notibar.setBackground(new Color(0,0,0,77));
		notibar.setSize(width, 20);
		notibar.setLocation(0,0);
		
		JPanel title = new JPanel();
		
		title.setBackground(Color.decode("#00BCD4"));
		title.setSize(width,100);
		title.setLocation(0,0);
		title.setLayout(null);
		////////////////////////////////
		////////////////////////////////
		JLabel titlelabel;
		titlelabel = new JLabel(){
			@Override
			 public void paint(Graphics g)
            {
				setText(Subject[choosedSub]);
				g.drawString(Subject[choosedSub], 0,40);
            }
		};
		titlelabel.setSize(500,50);
		titlelabel.setForeground(Color.decode("#FFFFFF"));
		titlelabel.setLocation(100,32);
		titlelabel.setFont(new Font("맑은 고딕",Font.BOLD,32));
		titlelabel.setVisible(true);
		//TODO: problem occurred
		///////////////////////////////////
		///////////////////////////////////
		IconPanel exiticon=new IconPanel(".\\images\\exit.png",45,45,Color.decode("#00BCD4"));
		exiticon.setLocation(520,37);
		exiticon.addMouseListener(new ExitButton());
		
		IconPanel backicon=new IconPanel(".\\images\\back.png",45,45,Color.decode("#00BCD4"));
		backicon.setLocation(20,37);
		backicon.addMouseListener(new BackMenu());
				
		title.add(notibar);
		
		chapterview.add(titlelabel);
		title.add(exiticon);
		title.add(backicon);
		paper.add(title);
		
		chapterview.add(paper);
		
	}
	public void drawmain()
	{	
		mainframe.setName("mainframedeclared");
		mainframe.setUndecorated(true);
		mainframe.setResizable(false);
		mainframe.setVisible(true);
		mainframe.getContentPane().setLayout(null);
		mainframe.setSize(width, height);
		mainframe.setLocation(posX,posY);
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
		
		Circle exit=new Circle(Color.decode("#CDDC39"));
		exit.setVisible(true);
		exit.setLayout(null);
		exit.addMouseListener(new ClickcolorCircleExit());
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
		title.setBackground(Color.decode("#00BCD4"));
		title.setSize(width, 210);
		title.setLocation(0,0);
		
		PaperPanel playbuttoncontainer=new PaperPanel(105,105);
		playbuttoncontainer.setLocation(57,173);
		
		JPanel playbutton=new JPanel();
		playbutton.setVisible(true);
		playbutton.setLayout(null);
		playbutton.setName("playbutton");
		playbutton.addMouseListener(new Clickcolor());
		playbutton.addMouseListener(new TransScreen());
		playbutton.setBackground(Color.decode("#D4E157"));
		playbutton.setSize(105, 105);
		playbutton.setLocation(0,0);
		
		IconPanel playicon=new IconPanel(".\\images\\play.png",45,45);
		playicon.setLocation(30,30);
		
		JLabel playlabel=new JLabel("PLAY");
		playlabel.setForeground(Color.decode("#424242"));
		playlabel.setFont(new Font("맑은 고딕",Font.BOLD,28));
		playlabel.setVisible(true);
		playlabel.setSize(80,30);
		playlabel.setLocation(75,290);
		
		PaperPanel creditcontainer=new PaperPanel(105,105);
		creditcontainer.setLocation(233,173);
		
		JPanel scorebutton=new JPanel();
		scorebutton.setVisible(true);
		scorebutton.setLayout(null);
		scorebutton.setName("scorebutton");
		scorebutton.addMouseListener(new Clickcolor());
		scorebutton.addMouseListener(new TransScreen());
		scorebutton.setBackground(Color.decode("#D4E157"));
		scorebutton.setSize(105, 105);
		scorebutton.setLocation(0,0);
		
		JLabel scorelabel=new JLabel("SCORE");
		scorelabel.setForeground(Color.decode("#424242"));
		scorelabel.setFont(new Font("맑은 고딕",Font.BOLD,28));
		scorelabel.setVisible(true);
		scorelabel.setSize(120,30);
		scorelabel.setLocation(242,290);
		
		IconPanel crediticon=new IconPanel(".\\images\\credit.png",45,45);
		crediticon.setLocation(30,30);
		
		PaperPanel aboutcontainer=new PaperPanel(105,105);
		aboutcontainer.setLocation(409,173);
		
		JPanel aboutbutton=new JPanel();
		aboutbutton.setVisible(true);
		aboutbutton.setLayout(null);
		aboutbutton.setName("aboutbutton");
		aboutbutton.addMouseListener(new Clickcolor());
		aboutbutton.addMouseListener(new TransScreen());
		aboutbutton.setBackground(Color.decode("#D4E157"));
		aboutbutton.setSize(105, 105);
		aboutbutton.setLocation(0,0);
		
		JLabel aboutlabel=new JLabel("ABOUT");
		aboutlabel.setForeground(Color.decode("#424242"));
		aboutlabel.setFont(new Font("맑은 고딕",Font.BOLD,28));
		aboutlabel.setVisible(true);
		aboutlabel.setSize(120,30);
		aboutlabel.setLocation(418,290);
		
		IconPanel abouticon=new IconPanel(".\\images\\about.png",45,45);
		abouticon.setLocation(30,30);
		
		JLabel titlename=new JLabel("S PLAY");
		titlename.setVisible(true);
		titlename.setFont(new Font("맑은 고딕",Font.BOLD,42));
		titlename.setForeground(Color.decode("#FFFFFF"));
		titlename.setBounds(75, 130, 150, 70);
				
		//TODO:addpin
		title.add(notibar);
		title.add(titlename);
		titlecontainer.add(title);
		
		playbuttoncontainer.add(playbutton);
		creditcontainer.add(scorebutton);
		aboutcontainer.add(aboutbutton);
		
		plain.add(playlabel);
		plain.add(scorelabel);
		plain.add(aboutlabel);
		
		plain.add(aboutcontainer);
		plain.add(creditcontainer);
		plain.add(playbuttoncontainer);
		
		
		
		playbutton.add(playicon);
		scorebutton.add(crediticon);
		aboutbutton.add(abouticon);
		
		plaincontainer.add(plain);
				
		exitcontainer.add(exit,0,0);
		exit.add(exiticon,2,0);
				
		paper.add(exitcontainer);
		paper.add(plaincontainer);
		paper.add(titlecontainer);
		mainframe.add(paper);
	}
	//TODO:: events
	class Clickcolor extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e)
		{
			JPanel play=(JPanel)e.getSource();
			play.setBackground(Color.decode("#AFB42B"));
		}
		
		@Override
		public void mouseReleased(MouseEvent e)
		{
			JPanel play=(JPanel)e.getSource();
			play.setBackground(Color.decode("#D4E157"));
		}
	}
	class Clickcolorgray extends MouseAdapter{
		public void mousePressed(MouseEvent e)
		{
			JPanel play=(JPanel)e.getSource();
			play.setBackground(Color.decode("#F5F5F5"));
		}
		
		@Override
		public void mouseReleased(MouseEvent e)
		{
			JPanel play=(JPanel)e.getSource();
			play.setBackground(Color.decode("#FFFFFF"));
		}
	}
	class ClickcolorCircleExit extends MouseAdapter{
		@Override
		public void mousePressed(MouseEvent e)
		{
			if(Math.sqrt(Math.pow(e.getX()-(44),2)+Math.pow(e.getY()-(44), 2))<43)
			{
				Circle btn=(Circle)e.getSource();
				btn.SetColor(Color.decode("#AFB42B"));
				btn.setBackground(Color.decode("#AFB42B"));
				btn.repaint();
				//TODO:: clear background glitter
			}
		}
		public void mouseReleased(MouseEvent e)
		{
			if(Math.sqrt(Math.pow(e.getX()-(44),2)+Math.pow(e.getY()-(44), 2))<43)
			{
				Circle btn=(Circle)e.getSource();
				btn.SetColor(Color.decode("#CDDC39"));
				btn.setBackground(new Color(0,0,0,0));
				btn.repaint();
				//TODO:add another asking windows
					
				JFrame target=new JFrame();
				switch(level)
				{
				case 0:
					target=logoframe;
					break;
				case 1:
					target=mainframe;
					break;
				case 2:
					target=playmenu;
					break;
				}
				target.dispose();
				System.exit(1);
			}
			else
			{
				Circle btn=(Circle)e.getSource();
				btn.SetColor(Color.decode("#CDDC39"));
				btn.repaint();
			}
		}
		@Override
		public void mouseExited(MouseEvent e)
		{
			Circle btn=(Circle)e.getSource();
			btn.SetColor(Color.decode("#CDDC39"));
			btn.repaint();
		}
		
	}
	class BackMenu extends MouseAdapter{
		public void mouseClicked(MouseEvent e)
		{
			switch(level)
			{
				case 2:
					mainframe.setLocation(posX,posY);
					mainframe.setVisible(true);				
					playmenu.setVisible(false);
					level=1;
					break;
				case 3:
					mainframe.setLocation(posX,posY);
					mainframe.setVisible(true);
					scoreview.setVisible(false);
					level=1;
					break;
				case 4:
					mainframe.setLocation(posX,posY);
					mainframe.setVisible(true);
					aboutview.setVisible(false);
					level=1;
					break;
				case 5:
					playmenu.setLocation(posX,posY);
					playmenu.setVisible(true);
					chapterview.setVisible(false);
					chapterview.dispose();
					
					level=2;
				default:
					break;
			}
		}
	}
	class ExitButton extends MouseAdapter{
		public void mouseReleased(MouseEvent e)
		{
			JFrame target=new JFrame();
			switch(level)
			{
			case 0:
				target=logoframe;
				break;
			case 1:
				target=mainframe;
				break;
			case 2:
				target=playmenu;
				break;
			case 3:
				target=scoreview;
				break;
			case 4:
				target=aboutview;
				break;
			case 5:
				target=chapterview;
			}
			target.dispose();
			System.exit(1);
		}
	}
	class subtochp extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			JPanel target=(JPanel)e.getSource();
			switch (target.getName())
			{
			case "SUB1":
				choosedSub=0;
				break;
			case "SUB2":
				choosedSub=1;
				break;
			case "SUB3":
				choosedSub=2;
				break;
			default:
				break;
			}
			chapterview.setLocation(posX,posY);
			playmenu.setVisible(false);
			level=5;
			if(chapterview.getName()!="chapterviewdeclared")
			{				
				drawchapterview();
				
			}
			else
			{
				//TODO:: update jlabel
				chapterview.validate();
				chapterview.getContentPane().repaint();
				chapterview.setVisible(true);
				
			}
			
				
			
		}
	}
	class TransScreen extends MouseAdapter{
		public void mouseClicked(MouseEvent e)
		{
			switch(level)
			{
			case 0:
				drawmain();
				mainframe.repaint();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				logoframe.setVisible(false);
				level=1;
				break;
			case 1:
				JPanel target=(JPanel)e.getSource();
				if(target.getName()=="playbutton")
				{
					if(playmenu.getName()!="playmenudeclared")
						drawplaymenu();
					else
					{
						playmenu.setLocation(posX,posY);
						playmenu.setVisible(true);
					}
					mainframe.setVisible(false);
					level=2;
				}
				else if(target.getName()=="scorebutton")
				{
					if(scoreview.getName()!="scoreviewdeclared")
						drawscoreview();
					else
					{
						scoreview.setLocation(posX,posY);
						scoreview.setVisible(true);
					}
					mainframe.setVisible(false);
					level=3;
				}
				else if(target.getName()=="aboutbutton")
				{
					if(aboutview.getName()!="aboutviewdeclared")
						drawaboutview();
					else
					{
						aboutview.setLocation(posX,posY);
						aboutview.setVisible(true);
					}
					mainframe.setVisible(false);
					level=4;
				}
				break;
			default:
				break;
					
			}
		}
		public void mouseReleased(MouseEvent e){
			switch(level)
			{
			case 1:
				JPanel target=(JPanel)e.getSource();
				if(target.getName()=="playbutton")
				{
					if(playmenu.getName()!="playmenudeclared")
						drawplaymenu();
					else
					{
						playmenu.setLocation(posX,posY);
						playmenu.setVisible(true);
					}
					mainframe.setVisible(false);
					level=2;
				}
				else if(target.getName()=="scorebutton")
				{
					if(scoreview.getName()!="scoreviewdeclared")
						drawscoreview();
					else
					{
						scoreview.setLocation(posX,posY);
						scoreview.setVisible(true);
					}
					mainframe.setVisible(false);
					level=3;
				}
				else if(target.getName()=="aboutbutton")
				{
					if(aboutview.getName()!="aboutviewdeclared")
						drawaboutview();
					else
					{
						aboutview.setLocation(posX,posY);
						aboutview.setVisible(true);
					}
					mainframe.setVisible(false);
					level=4;
				}
				break;
			default:
				break;
			}
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
			JFrame target=new JFrame();
			switch(level)
			{
			case 0:
				target=logoframe;
				break;
			case 1:
				target=mainframe;
				break;
			case 2:
				target=playmenu;
				break;
			case 3:
				target=scoreview;
				break;
			case 4:
				target=aboutview;
				break;
			case 5:
				target=chapterview;
				break;
			}
			target.setLocation(target.getLocation().x+evt.getX()-Px,target.getLocation().y+evt.getY()-Py);
			posX=target.getLocation().x;
			posY=target.getLocation().y;
		}
	}
	public void updateUI(JLabel input){
		SwingUtilities.updateComponentTreeUI(input);
	}
}
