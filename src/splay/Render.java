package splay;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
public class Render {
	//������ ��Ƽ��Ƽ���� �������� �����Ѵ�
	public JFrame logoframe;	//�� ó�� �ΰ� ������ �κ�
	public JFrame mainframe;	//���� �޴��� �ִ� ������
	public JFrame playmenu;		//���� ȭ���� �ִ� ������
	public JFrame scoreview;	//���ھ �� �� �ִ� ������
	public JFrame aboutview;	
	public JFrame chapterview; 	//
	private int width=600;		//��ü �������� ��
	private int height=800;		//��ü �������� ����
	private int level;			//���� �������� �������� ��ȣ
	private int posX,posY;		//ȭ���� �����϶� ���Ǵ� ��ǥ��
	private int score[][];		//������ ������ �迭
	private double accuracy[];	//����� ��Ȯ��
	private int achievement[];	//�����Ȳ
	private int choosedSub;	//���� ��å�� ������ ��ȣ 
	private int CurrentNum[]; 
	private String ALLQ[][];
	private String ALLA[][];
	private String ALLC[][][];
	private String archQ[]= {"���� �߿��� ��ǻ���� �⺻\n������ҷ� �� �� ���� ����?",
			"��ǻ���� �ܾ� ���̷μ� �Ϲ�������\n������ �ʴ� ����?" ,
			"12��Ʈ�� �ּҷ� ������ �� �ִ�\n��� ����� ���� ��� �� �� �ΰ�?",
			"���� �� ���� �ý��� ������\n������ �ʴ� ����?" ,
			"Ű������ ��� �� Ű�� ������ ��\nCPU�� ���۵Ǵ� ������ ASCII �ڵ�\n7��Ʈ�� �� ��Ʈ�� �������� 8��Ʈ�� �ȴ�.\n�� �� ��Ʈ�� �����ΰ�?",
			"���� �߿��� ������ǻ����\n���� ���°� �ƴ� ����?",
			"��� ���� �� �� ������ �����ϴ�\nCPU ���� ��Ҵ� ��� ���ΰ�?",
			"���� �߿��� ��ɾ� ����Ŭ�� ���ϴ�\n�λ���Ŭ(subcycle)�� �ƴ� ����?",
			"Ŭ�� ���ļ��� 2GHz �� CPU����\n���� ����Ŭ�� �ҿ�Ǵ� �ð��� ���ΰ�?",
			"4-�ܰ� ���������ο��� 10���� ��ɾ����\n����Ǵ� ���� ��� �� ����Ŭ��\n�ҿ�Ǵ°�?",
			"4-way ���۽�Į�� ���μ���������\n�� ����Ŭ���� �� ������ ��ɾ����\n����� �� �ִ°�?",
			"�����ġ �ּҸ� ��Ÿ���� ���۷��� �ʵ�\n�� 14��Ʈ ���, �� ��ɾ ���� ����\n�ּ������� �� �ִ� �����ġ �뷮��\n���ΰ�?",
			"���� �ּ����� ����� ����ϴ� ��ɾ��\n���� ����Ŭ ���ȿ� �����ġ�� �� ��\n�׼����ϴ°�?",
			"2�� ���� '11101000'�� 10������\n��ȯ�ϸ� � ���� �Ǵ°�?",
			"�� ���� ���ϱ� ���ؼ� ���Ǵ�\n�� ������ �����ΰ�?",
			"Ư�� ��Ʈ���� ����ũ(mask) ��Ű��\n���Ͽ� ���Ǵ� �� ������ �����ΰ�?",
			"A �������Ϳ� 2�� ������ ǥ���� ������\n'1010101'�� ����Ǿ� ���� ��,����� ����\n����Ʈ�� �� �� ������ ����� ���� ��\n������ΰ�?",
			"A �������Ϳ� '01011011'�� ����Ǿ�\n�ְ� C�÷��׿� '1'�� ����Ǿ� ������,\nSHRC ������ �� �� ������ �����\n���� �� ��� ���ΰ�?",
			"���� �� �ε��Ҽ��� ���� ��������\n�ʿ����� ���� ������ ��� ���ΰ�?",
			"���� ����Ʈ���� ������ ������\n����ũ�θ�ɾ��� �ּҸ� ������ �ִ�\n�������ʹ� ��� ���ΰ�?",
			"���� �����ġ�� ���� ���� ��� �����ġ\n���ڸ� �̿��Ͽ� �����Ǵ°�?",
			"���� �߿��� �����ƾ ��������(SBR)��\n������ �ʴ� ����ũ��-������\n��� ���ΰ�?",
			"���� �� CAR�� ����� ��\n���� ���� ��� ���ΰ�?",
			"�� ���� ���� �ʵ尡 ���� 4��Ʈ������\n�����Ǿ� �ִ�. ������ ����ũ�����α׷���\n����� ���Ǵ� ����� �ִ� �� ����\n���� ��ȣ���� �߻��� �� �ִ°�?",
			"�� ���� ���� �ʵ尡 ���� 4��Ʈ������\n�����Ǿ� �ִ�. ������ ����ũ�����α׷���\n����� ���Ǵ� ����� �ִ� �� ����\n���� ��ȣ���� �߻��� �� �ִ°�?\n��, �ص���� �� ���� ����Ѵٰ� �����Ѵ�.",
			"���� �����ġ�� �ܺ� �����ġ��\n�����ϴ� ������ �Ǵ� ����?",
         		"�����ġ �����ߵ� �߿��� �ּҸ� \n������� �ʰ� ����� ��Ʈ ������ \n���Ͽ� �׼����� ��ġ��\nã�Ƴ��� �����ġ�� ������ΰ�?",
         		"� �����ġ�� �׼����� �����ϴ�\n�������� ���� �׼����� �ٽ� ������ �� \n���� �������� �ð� ������ ��Ÿ���� ����?",
         		"�ܾ� ������ �ּҸ� �����ϴ� �ý��ۿ���\n�ܾ��� ���̰� 32��Ʈ���, 10��Ʈ��\n�ּҷ� ���� �׼��� �� �� �ִ� \n��� ��ġ�뷮���� ����Ʈ�ΰ�?",
         		"������ ���� 64���� ĳ�ø� 4-way\n��Ʈ-���� ��� ������� �����Ͽ��ٸ�,\n�ֱ����ġ ����� ����� �� �ִ�\nĳ�� ������ �� �� �ΰ�?",
         		"CPU�� �����ġ�� 500�� �������ϴ�\n���ȿ� ���ϴ� �����Ͱ� ĳ�ÿ� �־���\nȽ���� 450���̾��ٸ�, ĳ�� ���߷���\n���ΰ�?",
         		"Ž�� �ð��� ���� ª�� ��ũ�� �����ΰ�?",
         		"MTTF = 2000�ð��� ��ũ���� 8��\n �����Ͽ� ������ ��ũ �迭��\nMTTF�ð��� �󸶰� �Ǵ°�?",
         		"���� ������ ���Ͽ� �ع� �ڵ带\n�̿��ϴ� ����?",
         		"�÷��� �޸� �߿��� TLC�� �ϳ��� ����\n�̿��Ͽ� ��� ������ ������ �� �ִ°�?",
         		"CD-ROM���� Ʈ���� ���� �� ���ΰ�?",
         		"���� �߿��� SSD�� ������ �����Ű��\n���� ����� ��� ���ΰ�?",
            		"����� ���� ����� ������ �ϴ� ������\n��� ���ΰ�?",
         		"��Ƽ��4 ����ũ�����μ�����\n�ֿ� �������� 36��Ʈ�̴�. �ּ�������\n ������ �ִ� �����ġ �뷮��?",
         		"���� ����Ⱑ ���� ��\n�ʿ��� ���� �����?",
         		"�����ġ-��� I/O ����� ����ϴ�\n�ý��ۿ��� �ּҰ� 12��Ʈ�̸�,\nI/O��ġ�� �ִ� �� ������\n������ �� �ִ°�?",
         		"DMA������� �Ѱ踦 �غ��ϱ� ���Ͽ�\n ����ϴ� ����� ��� �� �ΰ�?",
         		"���� Ŭ���� ���ļ��� 100MHz�̰�,\n������ ���� ���� 64��Ʈ���,\n�� ������ �뿪���� ��\n[Mbytes/sec]�� �Ǵ°�?",
            		"���� ���� ������ ����ó���� \n���� �� � ���ļ��� �̿��ϴ� ���ΰ�?",
         		"���� ���� �����͵鿡 ���Ͽ� \n���� ������ ���ÿ� ó���ϴ� ��\n������ �ý��� ������ ��� ���ΰ�?",
         		"����-�����ġ ��ǻ�ͽý��ۿ���\n���Ǵ� ��ȣ���� ������ �ƴ� ����?",
         		"9���� ������ ��(ring)������\n ����Ǿ� �ִ�. ��ũ�� ������̶��\n��Ʈ��ũ ������ ���ΰ�?",
         		"4�� Ʈ�� ������ ������ �� �ִ�\n����� ���� ��� �� ���ΰ�?",
         		"�ټ��� ���μ������� ����������\n���α׷��� �����ϴ� �ý��� ������\n������ΰ�?",
         		"16���� ���μ������ 16���� \n�����ġ ������ ���ް� ��Ʈ��ũ��\n�����ϴµ� �ʿ��� 2X2 ����ġ\n������ ���� ��� �� ���ΰ�?"
   };
	private String arch_ans[]= {"2","3","4","3","3","2","3","2","2","3",
			"4","2","3","3","1","4","2","3","2","3",
			"3","3","2","3","2","4","3","2","2","2",
			"3","4","2","2","3","1","1","2","4","3",
			"2","1","1","1","2","3","2","2","4","4"
			};
	private String archchose[][]= {{"��CPU","��ĳ��","������� ��ġ","������ġ"},
			{"��8��Ʈ","��32��Ʈ","��24��Ʈ","��64��Ʈ"},
			{"��12","��1024","��2048","��4096"},
			{"���ּ� ����","�赥���� ����","��I/O ����","������ ����"},
			{"������ ��Ʈ","���ȣ ��Ʈ","���и�Ƽ ��Ʈ","��ø��� ��Ʈ"},
			{"��Ŭ������ ��ǻ��","���Ӻ���� ��ǻ��","��MPP","������������ ������ǻ��"},
			{"�罺��","������ ����Ʈ","�鷹������ ��Ʈ","��ALU"},
			{"������ ����Ŭ","�谣�� ����Ŭ","������ ����Ŭ","����� ����Ŭ"},
			{"��0.5ns","��1.5ns","��2ns","��6ns"},
			{"��10","��13","��14","��40"},
			{"��1��","��2��","��4��","��8��"},
			{"��1KByte","��2KByte","��4KByte","��8KByte"},
			{"���� ��","��� ��","�鼼 ��","��׼������� �ʴ´�."},
			{"��-12","��48","��-24","��232"},
			{"��AND ����","��OR ����","��XOR ����","��NOT����"},
			{"��AND ����","��OR ����","��XOR ����","��NOT����"},
			{"��00101011","��01010110","��11010110","��11101011"},
			{"��00101101","��10101101","��10110111","��10110110"},
			{"������ ����","������ȭ","������ ����"," �갡�� ����"},
			{"��CBR","��CAR","��IR","��PC"},
			{"��RAM","��CAM","��ROM","��DISK"},
			{"��ȣ��(CALL)","�躹��(RET)","������(JUMP)", "�����Ǻ� ȣ��(conditional CALL)"},
			{"��SBR�� ����","����(mapping)�� �����","�鿬�� �ʵ� ��Ʈ��","���ּ� �ʵ�(ADF)�� ��"},
			{"��4��","��8��","��16��","��32��"},
			{"��8��","��16��","��32��","��64��"},
			{"������ġ�� �뷮","������ġ�� ��ġ","������ġ�� �׼��� �ӵ�","��CPU�� ���� �׼��� ���� ����"},
			{"������ �׼��� �����ġ","������ �׼��� �����ġ","�鿬�� �׼��� �����ġ","������� �׼��� �����ġ"},
			{"������ġ �׼��� �ð�","������ġ ����Ŭ �ð�","�鵥���� ���� �ð�","�굥���� ���� �ð�"},
			{"��2K ����Ʈ","��4K ����Ʈ","��8K ����Ʈ","��16K ����Ʈ"},
			{"��1��","��4��","��16��","��64��"},
			{"��0.45","��0.5","��0.9","��0.95"},
			{"�����-����ũ","�����-��� ��ũ","���̵�-��� ��ũ","�����-��� ��ũ"},//"����-��� ��ũ"},
			{"��200�ð�","��250�ð�","��400�ð�","��1600�ð�"},//{"250�ð�"},
			{"��RAID-1","��RAID-2","��RAID-3","��RAID-5"},//{"RAID-2"},
			{"��2","��4","��8","��16"},//{"8"},
			{"���� ��","��200��","��2400��","��2400�� �̻�"},//{"�� ��"},
			{"�縶�� ����ȭ","�農���� ����","���ּҸ���","���ʰ� ��� �뷮"}	,
			{"���ּҹ���","�赥���� ����","������ ����","�����ͷ�Ʈ ����"},//{"������ ����"},
			{"��16MByte","��64MByte","��32GByte","��64GBtye"},//{"64GByte"},
			{"�絥����-ü�� ���","���߾����߽� ���� �켱���� ���","��л�� ���� �켱���� ���","�����Ʈ���� �ø� ���"},//{"�л�� ���� �켱���� ���"},
			{"��12��","��2048��","��4096��","��1024��"},//{"2048��"},
			{"��I/O���μ���","����� ���ͷ�Ʈ","�����α׷��� �̿��� I/O","���Ƽ�÷���"},//{"I/O ���μ���"},
			{"��800","��64","��640","��80"},
			{"���ɾ�-���� ���ļ�","���½�ũ-���� ���ļ�","���۾�-���� ���ļ�","�꽺����-���� ���ļ�"},//{"��ɾ�-���� ���ļ�"},
			{"��SISD","��SIMD","��MISD","��MIMD"},//{"SIMD"},
			{"�����","��ٴܰ� ��ȣ�����","��Ž�","��ũ�ν��� ����ġ"},//{"�Ž�"},
			{"��2","��4","��5","��8"},//{"4"},
			{"��6��","��15��","��16��","��31��"},//{"15��"}
			{"��SISD","��SIMD","��MISD","��MIMD"},
			{"��4��","��8��","��16��","��32��"}
		};
	private String DSQ[]={"�ð� ���⵵ �Լ� n2+10n+8��\n��� ǥ������� ��Ÿ����?",
			"�ð� ���⵵ �Լ��� 7n+10�̶��\n�̰��� ��Ÿ���� ���� �����ΰ�?",
			"O(n2)�� �ð� ���⵵�� ������ �˰��򿡼�\n�Է��� ������ 2��� �Ǿ��ٸ� ����ð���\n��� �Ǵ°�?",
			"O(n2)�� �ð� ���⵵�� ������ �˰�����\n1���� �Է��� 1�ʿ� ó���Ѵ�.\n�� �˰����� 10���� �Է��� ó���ϴ�\n�뷫���� �ð���?",
			"��ȯ ȣ���� �Ͽ��� ��쿡 Ȱ��\n���ڵ���� ����Ǵ� ��ġ�� ����ΰ�?",
			"���� �� Ȱ�� ���ڵ忡 �������\n�ʴ� ���� �����ΰ�?",
			"���� �� ��ȯ ȣ���� �Ұ����� ����?",
			"�ϳ��� �Լ��� ȣ���� �� �ִ�\n��ȯ ȣ���� ������?",
			"float a[100]���� ����� �迭��\n���� �ּҸ� 1000������� �� ��, �迭��\n10��° ����� �ּҴ� �� �����ΰ�?",
			"int i=10; int *p; *p=8; �� ������\n����Ǹ� i ���� ���ΰ�?",
			"int i=10; int *p; (*p)--; �� ������\n����Ǹ� i ���� ���ΰ�?",
			"int a[10]; int*p; p=a; *p++=5; �� ������\n����Ǹ� i���� ���ΰ�? ",
			"������ p�� ���� ������ p�� ���� ����?",
			"���԰� ���� �۾��� ���� �߻��� �� ����\n�ð��� ���� ���� �ҿ�Ǵ� �ڷ� ������?",
			"���� �� NULL ������(NULL pointer)��\n�������� ���� ������ ��� ���ΰ�?",
			"����Ʈ�� n��° ��Ҹ� ���� ������\nã�� �� �ִ� ���� ����� �����ΰ�?",
			"�ܼ� ���� ����Ʈ�� ������ ��� ������\np��Ž���ϰ��� �Ѵ�. p�� ���� ����Ű��\n��忡�� ���� ���� ������ ���\n�Ͽ��� �ϴ°�?",
			"�ܼ� ���� ����Ʈ�� ���� �Լ� f��\n��� ������ head�� ���� ���Ѿ� �Ѵٸ�\n�Լ� �Ű� ������ ������ �޾ƾ� �ϴ°�?",
			"������ ���� ȿ�������� �̿��ϴ� �����?",
			"���ÿ��� ���� �۾��� �߻��ϸ�\ntop�� ����?",
			"�迭�� ������ ���ÿ��� top�� 3�̸�\n���� ���ÿ� ����� ��ҵ��� ������?",
			"���� �� �迭�� ������ ���ÿ���\n���� ���¿� �ش��ϴ� ������?\n�� ��ȭ ���¿� �ش�Ǵ� ������?",
			"ũ�Ⱑ 8�� ���� ť���� front�� 3�̰�\nrear�� 5��� �ϸ� ���� ���� ť��\n����� ��ҵ��� ������?",
			"ť�� �׸���� �����ϰ� �����ϴ� ������\n�ð� ���⵵�� ��� �Ǵ°�?",
			"ũ�Ⱑ 10�̰� front�� 3,rear�� 5��\n���� ť���� ���ο� �׸��� ���ԵǾ���\n���,�迭�� � �ε����� ���°�?",
			"���� ������ ����� ť���� ���ο� �׸���\n���ԵǾ���.��ȭ�Ǵ� �����ʹ�?",
			"�������� Ʈ�� ������ ��Ÿ���⿡\n�������� ���� ����?",
			"�޸𸮻� �迭�� ������ ��\n���� ���� ū Ʈ����?",
			"���� �� ���� ������ ��尡 ����Ǵ� ���,\n���� ���̰� ���� Ʈ����?",
			"����Ʈ������ ���̰� 5�� ��,\n�ִ� �� ���� ��带 ���� �� �ִ°�?",
			"���� Ʈ������ ��尡 �����Ǵ�\n��ġ�� ����ΰ�?",
			"���������� ������ ���� ���� ��\n� ������ �����ϴ°�?",
			"���� ���� �߿��� �ϳ��� ��尡\n���Եǰų� �����Ǵ� �ð���\n������ ����ϴ°�?",
			"�ּ� �������� ���� ���� �����Ͱ�\n�ִ� ����?",
			"���� �� �������� ���� �����\n�ƴ� ���� �����ΰ�?",
			"���� �� ���� ������ ���� ȿ��������\n����� �� �ִ� ����?",
			"���� �� �߰� ������ �� �ʿ�� �ϴ�\n���� �����?",
			"�����ؾ� �� ���ڵ��� ũ�Ⱑ ��ô ũ�ٸ�\n���� �� � ���� ����� ����ϴ� ����\n���� ������?",
			"������ 3���̰� ������ 3�� �ִ� ������\n�׷������� ������ ���� Ʈ���� ������?",
			"������ ������ n, ������ ������ e���\n�� ��, ���� ��Ŀ��� Ư�� ������ ������\n����ϴ� ������ �ð� ���⵵��?",
			"������ ������ n, ������ ������ e��\n������ �׷����� ���� ����Ʈ��\nǥ�� �Ͽ��� ���,���� ����Ʈ����\n�� ����� ������?",
			"���� �� ť�� ����ϴ� �˰�����?",
			"�ؽ̿� ���� ���� �� Ʋ�� ����?",
			"�ؽ̿��� �浹�� ���� �߻��ϴ°�?",
			"���� ������� ���� ���� �� Ʋ�� ����?",
			"ü�̴׿��� ���ο� Ž�� Ű�� �����ϴ�\n�ð��� ������ ����ϴ°�?",
			"���� Ž���� ���� ���� �� Ʋ�� ����?",
			"���� Ž�� Ʈ���� ���� ���� �� Ʋ������? ",
			"AVLƮ���� ���� ���� �� �´� ����?",
			"AVLƮ������ ȸ���� ���� �̷�����°�?"};
	private String DS_ans[]={"3","1","3","4","4","4","3","3","4","4",
			"3","1","1","1","2","1","3","2","4","4",
			"4","1","2","1","1","3","2","1","3","4",
			"1","3","2","2","2","2","4","2","3","2",
			"2","2","1","2","4","3","1","3","4","2"};
	private String DSchose[][]={{"��O(n)","��O(nlog2n)","��O(n2)","��O(n2log2n)"},
			{"�翬���� Ƚ��","�����α׷��� ������ �ð�","�����α׷��� �����ϴ� �޸��� ��","���Է� �������� �� ����"},
			{"�纯�� ����.","��2��","��4��","��8��"},{"��1��","��10��","��100��","��10000��"},
			{"���ȯ ȣ�� �Լ� ����","�躯��","��迭","�꽺��"},
			{"��Ű� ������ ��","���Լ� ȣ���� ������ ������ �ּ�","������ ����","���ȯ ȣ���� ���� ��ȣ"},
			{"��C���","��Pascal ���","��Basic ���","��Java ���"},
			{"��1��","��2��","�齺���� ���Ǵ� �ѵ�","�깫����"},
			{"��1000����","��1010����","��1020����","��1040����"},
			{"��11","��10","��9","��8"},
			{"��11","��10","��9","��8"},
			{"��a[0]","��a[1]","��a[2]","��a[3]"},
			{"��&*p","��*&p","��&&p","��**p"},
			{"��迭�� ������ ����Ʈ","��ܼ� ���� ����Ʈ","����� ���� ����Ʈ","������ ���� ����Ʈ"},
			{"��ܼ� ���Ḯ��Ʈ","����� ���� ����Ʈ","������ ���� ����Ʈ","����� ��带 ������ �ܼ� ���� ����Ʈ"},
			{"��迭","��ܼ� ���� ����Ʈ","����� ���� ����Ʈ","������ ���� ����Ʈ"},
			{"��p++;","��p--","��p=p->link;","��p=p->data;"},
			{"��head","��&head","��*head","��head->link;"},
			{"�����(looping)","��˰���(algorihm)","��ݺ�(iteration)","���ȯ(recursize)"},
			{"��top == 0","��top == 1","��top = top-1","��top = top+1"},
			{"��1","��2","��3","��4"},
			{"��top==-1","��top == 0","��top == (MAX_STACK_SIZE-1)","��top == MAX_STACK_SIZE"},
			{"��1","��2","��3","��4"},
			{"��O(1)","��O(log2n)","��O(n)","��O(n2)"},
			{"��0","��9","��8","��10"},
			{"��front","��rear","��front �� rear","��� �� ��ȭ���� �ʴ´�."},
			{"�簡���� �躸","�����","�鿬�� ����","��ȸ�� �������� ���޻��� ���� ����"},
			{"���� Ʈ��","������ Ʈ��","����ȭ Ʈ��","����� Ʈ��"},
			{"���� Ʈ��","������ Ʈ��","����ȭ Ʈ��","����� Ʈ��"},
			{"��26��","��8��","��32��","��31��"},
			{"���Ʈ","�踶���� ���","�鰡�� �ֱٿ� ���Ե� ���","�갡�� ���� ���Ե� ���"},
			{"���׻� ���� ����̴�.","���׻� ������ ����̴�.","���׻� �� ������ ������ �ִ�.","������ ��庸�� ���� �ʴ�."},
			{"������ ����","��Ʈ���� ����","���׻� �����ϴ�","�꿹���� �Ұ����ϴ�."},
			{"�縶���� ���","��ù ��° ���","���߰� ���","��� �� ����."},
			{"����� ����","�輱�� ����","������ ����","��� ����"},
			{"�翪������ ���ĵǾ� �ִ�.","���� ���� ���ĵǾ� �ִ�.","�鷹�ڵ���� ũ�Ⱑ ũ��.","��޸� ������ ������ �ִ�."},
			{"����� ����","��������","������ ����","���պ� ����"},
			{"����� ����","�輱�� ����","����� ����","��� ����"},
			{"��1��","��2��","��3��","��4��"},
			{"��O(log2n)","��O(n)","��O(n+e)","��O(e)"},
			{"��e��","��2e��","��n��","��2n��"},
			{"����� �켱 Ž��","��ʺ� �켱 Ž��","��Dijkstra�� �ִ� ��� �˰���","��Floyd�� �ִ� ��� �˰���"},
			{"���浹�� �Ͼ�� �ʴ´ٸ� �ð� ���⵵�� O(log2n)�̴�.","�輱�� ������� ���, �ؽ� ���̺��� �迭�� �����ȴ�.","���ؽ� �Լ��� �ؽ� ���̺��� �ε����� �����Ѵ�.","�꺸�� �ؽ� ���̺��� ũ��� �Ҽ�(Prime number)�̴�."},
			{"��Ž�� Ű�� ���� ���","���ؽ� �Լ��� ���� ���� ���","�鰰�� �ؽ� �Լ��� ����ϴ� ���","��Ž�� Ű�� ���̰� ���� ���"},
			{"��ü�̴׿� ���Ͽ� �޸𸮸� ������ �� �ִ�.","���浹�� �Ͼ�� �ؽ� ���̺��� ��� �ִ� ���� Ž�� Ű�� �����Ѵ�.","��پ��� �ؽ� �Լ��� ������ �� �ִ�.","��Ž���� �������� ���� �������� ���� ���� �ð��� ����."},
			{"��������","�����̺� �ִ� ��ü �׸��� ����","������� ���� ����Ʈ�� �׸� ����","�����̺��� ũ��"},
			{"��迭�� ũ�Ⱑ 2�� �ŵ����� ���̾�� �Ѵ�.","��ݵ�� �迭�� ��ҵ��� ������ �Ǿ� �־�� �Ѵ�.","���ҵ��� �����̳� ������ ����� �ð��� �ʿ�� �Ѵ�.","��Ž���� �ð� ���⵵�� O(log2n)�̴�."},
			{"������ ���� Ž�� Ʈ���� ���, ������ ���� Ž���� ������ ���� �ִ�.","������ Ž���� ���Ͽ� �����̳� ������ ����.","��Ž�� ������ ������ ���� Ž�� Ʈ���� ���̿� ����Ѵ�.","����������� ���� Ž���� ���Ͽ� Ž�� �ӵ��� �� ������."},
			{"��Ʈ���� Ž���ϴ� �������� �籸���� �ϴ� ��쵵 �ִ�.","���Ʈ ��忡�� �ܸ� �������� ����� ���̴� ��� ����.","���� ���� ���� Ʈ���� ���̴� ������ ���� Ʈ���� ����.","������ Ž�� Ʈ���� Ž�� ������ ���� ���� ����� �� �ִ�."},
			{"����� ��","����� ��","��Ž�� ��","��Ž�� ��"}
			};
	private String ClangQ[]={
              		 "��ǻ�Ϳ� Ư���� '�۾�'�� �����ϴ� ����?",
              		 "�ҽ� ������ �������ϸ� ����� ������?",
              		 "C��� ���α׷��� ��ü�� �Ǵ� �Լ���?",
               		 "���α׷��� �����Ҷ� ó�������� ����� \n�ϴ°�?",//�˰���
               		 "�˰����� ǥ���ϴ� ����� ���� ��������?",
             	  	 "���ڿ��� ����Ҷ� ����ϴ� �Լ���?",
              		 "������ �����Ҷ� ������ �� �ִ� ��ġ�� \n��������?",
              		 "���ڿ� ��ġ�� ����� �� �̿��ϴ°���?",//��ȯ ���
              		 "������ C�� �������� �ʴ°���?",//����,���ڿ�,��ġ,�̹���
              		 "Ư���� ���ڸ� ��Ÿ���� ����ϴ°���?", //�̽������� ������

              		 "���� �Է¹����� ����ϴ°���?",
              		 "���ڸ� �����Ҷ��� ������?",//char
              		 "0.5�� ������ �� �ִ� ������?",//float
              		 "Ű���� ��ġ�� �Է¹����� ����ϴ� \n����?",
              		 "Ű����� ���ڸ� �Է¹����� ����ϴ�\n����?",
              		 "a++�� �������� �����ΰ�?",
              		 "a *= 10�� �������� �����ΰ�?",
              		 "���ǹ��� ������ �ƴѰ���?",
              		 "switch������ ���� ���������� ���� \n����?",
              		 "if������ ��� ������ ���ܸ� ó���Ҷ� \n���°���?",//else   

               		 "�ݺ����� ������ �ƴѰ���?",
                	 "�ݺ������� ��� ���� ó���� �����ϴ°���?",//break
               		 "�ݺ����� ���϶� �ݺ��� �����ϴ� �ݺ�����?",//for
               		 "ǥ������ ó���� ���ؼ� �̸� ���ǵǾ��ִ�\n�Լ���?",//ǥ�� ���̺귯�� �Լ�
              		 "�Լ��� ���� ������ �� ����ϴ°���?",//�μ�
               		 "���� ��ȯ�Ҷ� ����ϴ� ��ɾ��?", //return
               		 "�Լ��� ������ ��ҿ��� �� �Լ��� ���� \n������ �̿��� �� �ִ� ������?",//��������
               		 "�ʱ�ȭ ���� �����ؼ� �迭�� �ʱ�ȭ �Ҷ� \n����ϴ� ��ȣ��?",
               		 "�迭�� ���� ����Ű�� ���ؼ� ��Ҹ� \n�����ϰ� ���� �����Ҷ� \n����ϴ°���?",
               		 "�����Ͱ� ����Ű�� ������ ���� \n��������� ����ϴ°���?",

               		 "int *p = a;printf('%d',p);\n�϶� ��µǴ� ����?",//a�� �ּҰ�
               		 "���ڿ��� �����ϴ� �Լ���?",//strcpy;
               		 "���ڿ��� ���ϴ� �Լ���?",//strcmp;
              		 "����ü�� �������� �� �̸����� ����ϱ�\n ���� ���Ǵ°���?",//typedef
              		 "����ü�� ����� �׼����ϱ� ���ؼ�\n ���Ǵ� �����ڴ�?",//.
               		 "�迭�� ����ü�� �������� �����ΰ�?",
              		 "������ ����ü�� ������ ���°���?",
               		 "������ �迭�϶� �ε��� ���� ã������\n ���Ǵ°���?",//[]
               		 "2���� �����Ͱ� ����Ű�� ���� �����ΰ�?",//1���� �������� �ּҰ�
               		 "������ �ҷ��ö� ����ϴ� �Լ���?",//fopen
               
              		  "��Ʈ���� �������� �Է��� �� �� �ִ� \n�Լ���?",//fscanf
               		 "���� �������� �̿��Ҷ� ����ϴ� �Լ���?",//fseek
               		 "������������ ��ġ�� ó������ �ǵ����ִ� �Լ���?",//rewind
               		 "������������ �޸� ���� �Ҵ��� �ϴ� �Լ���?",//malloc
               		 "�����Ҵ��� �����Ҷ� ����ϴ� �Լ���?",//free
               		 "�����Ҵ翡�� �Ҵ��� �߰��Ҷ� ����ϴ� �Լ���?",
               		 "���������� ���� �ڷᰡ ���� ���� \n������ �ڷᱸ����?",
               		 "�����Ҵ��� �������� ���� ��������?",
               		 "�����Ҵ��� �������� ��������?",
               		 "���Ḯ��Ʈ�߿��� ��ġ�� ����Ҷ� \n���� ���� ���Ḯ��Ʈ��?"//�迭���Ḯ��Ʈ
            };
	private String Clangchose[][]={
		      {"�����α׷�",	"����",	"�鵿���Ҵ�",	"��˰���"},
		      {"�������Ʈ",	"������",	"��޸�",	"�꿡����"},
		      {"��printf",	"��sub",	"��main",	"��scanf"},
		      {"�����α׷�",	"��˰���",	"��������",	"������"},
		      {"�������","���ڿ���","�鰡���ڵ�",	"���Լ�"},
		      {"��scanf","��printf","��random","��fclose"},
		      {"��ǳ�","���ó��","��ƹ�����","�꼱�� �Ұ�"},
		      {"�纯ȯ ���","���۾�","��������","�������Ʈ ����"},
		      {"�繮��","�蹮�ڿ�","���̹���","���ġ"},
		      {"��Ư������","��ƽ�Ű�ڵ�","���̽������� ������","�����"},
		      
		      {"��scanf","��printf","��random","��fclose"},
		      {"��int","��float","��double","��char"},
		      {"��float","��int","��long","��char"},
		      {"��scanf","��random","��printf","��fopen"},
		      {"��random","��getchar","��calloc","��free"},
		      {"��a+=1","��a == a+1","��a = a+a","��a = a-1"},
		      {"��a = a-10","��a = a*10","��a = a+10","��a = a/10"},
		      {"��if","��switch","��if~else","��for"},
		      {"��continue","��break","��goto","��jump"},
		      {"��else","��etc","��for","��while"},
		      
		      {"��for","��do~while","��continue","��while"},
		      {"��break","��continue","��goto","��else"},
		      {"��for","��while","��if","��switch"},
		      {"������ �����Լ�","�����ǹ�","��ǥ�� ���̺귯�� �Լ�","�����ǹ�"},
		      {"�纯��","���μ�","������","��Ҽ�"},
		      {"��return","��continue","��break","��goto"},
		      {"����������","����","����������","��Ҽ�"},
		      {"��{ }","��[ ]","��( )","��:"},
		      {"��Ž��","���ε���","�鸵ũ","�걸��ü"},
		      {"��*","��&","��%","��!"},

		      {"��a�� ����","��p�� �ּҰ�","��a�� �ּҰ�","�꾲���� ��"},
		      {"��strcpy","��strcmp","��typedef","��malloc"},
		      {"��strcpy","��strcmp","��typedef","��malloc"},
		      {"��calloc","��free","��typedef","��strcmp"},
		      {"��.","��,","��-","��?"},
		      {"�簪�� ������ �����Ѵ�","���Ѱ��� �ڷ����� ���尡���ϴ�","�鿩���� ����� �ִ�","���ʱ�ȭ�� ���� �� �ִ�."},
		      {"������","��Ҽ�","���Լ�","�깮�ڿ�"},
		      {"��[]","��{}","��()","��=="},
		      {"�羲���Ⱚ","��1���� �������� �ּҰ�","��2���� �ּҰ��� �����","��1���� �������� �����"},
		      {"��fopen","��fclose","��fprintf","��malloc"},

		      {"��fopen","��fscanf","��printf","��scanf"},
		      {"��fseek","��rewind","��malloc","��free"},
		      {"��malloc","��fseek","��rewind","��calloc"},
		      {"��free","��malloc","��fscanf","��printf"},
		      {"��free","��printf","��scanf","��malloc"},
		      {"��malloc","��scanf","��printf","��calloc"},
		      {"�縵ũ�帮��Ʈ","��迭���Ḯ��Ʈ","�齺��","�긮��Ʈ ���"},
		      {"����� ����� �����Ӵ�","��ð��� ���԰ɸ���","��޸� ����� ȿ�����̴�","����� ������ ����."},
		      {"��ӵ��� ������","����� ����� �����Ӵ�","��޸� ����� ȿ�����̴�","����� ������ ����"},
		      {"�縵ũ�帮��Ʈ","�轺��","��迭���Ḯ��Ʈ","�����"}
		};
	private String Clang_ans[] = {
			"1","1","3","2","4","2","2","1","3","3",
			"1","4","1","1","2","1","2","4","2","1",
			"3","1","3","2","1","3","1","2","1","3",
			"1","2","3","1","2","3","1","2","1","2",
			"2","1","3","2","1","4","3","2","1","3"}; 
	private String Subject []= {"��ǻ�� ��Ű����","�ڷ� ����","C ���"};//������ ������ �����ϴ� ���ڿ� �迭
	int Px,Py;
	Render()// ȭ�鿡 �����͸� ����ϴ� render Ŭ������ ������
	{
		level=0;
		mainframe=new JFrame();
		logoframe=new JFrame();
		playmenu=new JFrame();
		scoreview=new JFrame();
		aboutview=new JFrame();
		chapterview=new JFrame();
		posX=500;
		posY=150;
		//���� �迭 �Ҵ� �� �ʱ�ȭ
		score=new int[3][100];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<100;j++)
				score[i][j]=0;
		}
		achievement=new int[3];
		for(int i=0;i<3;i++)
			achievement[i]=0;
		accuracy=new double[3];
		for(int i=0;i<3;i++)
			accuracy[i]=0;
		CurrentNum=new int[3];
		for(int i=0;i<3;i++)
			CurrentNum[i]=0;
		choosedSub=1;
		ALLQ=new String[3][];
		ALLQ[0]=archQ;
		ALLQ[1]=DSQ;
		ALLQ[2]=ClangQ;
		ALLA=new String[3][];
		ALLA[0]=arch_ans;
		ALLA[1]=DS_ans;
		ALLA[2]=Clang_ans;
		ALLC=new String[3][50][4];
		ALLC[0]=archchose;
		ALLC[1]=DSchose;
		ALLC[2]=Clangchose;
		
		
	}

	public void draw()
	{
		//���� ó���� logoframe�� ����Ѵ�.
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
		title.setFont(new Font("���� ���",Font.BOLD,42));
		title.setForeground(Color.decode("#FFFFFF"));
		title.setBounds(75, 250, 150, 70);
		
		JLabel corp=new JLabel("��õ����б�");
		corp.setVisible(true);
		corp.setFont(new Font("���� ���",Font.PLAIN,13));
		corp.setForeground(Color.decode("#FFFFFF"));
		corp.setBounds(75, 290, 300, 70);
		
		JLabel major=new JLabel("��ǻ�ͼ���Ʈ������а�");
		major.setVisible(true);
		major.setFont(new Font("���� ���",Font.PLAIN,13));
		major.setForeground(Color.decode("#FFFFFF"));
		major.setBounds(75, 315, 300, 70);
		
		JLabel dev=new JLabel("��λ� �̱�� ��α�");
		dev.setVisible(true);
		dev.setFont(new Font("���� ���",Font.BOLD,15));
		dev.setForeground(Color.decode("#FFFFFF"));
		dev.setBounds(75, 215, 300, 70);
		
		JLabel java=new JLabel("JAVA ���α׷���");
		java.setVisible(true);
		java.setFont(new Font("���� ���",Font.BOLD,18));
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
		titlelabel.setFont(new Font("���� ���",Font.BOLD,32));
		
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
		
		JLabel ach1 =new JLabel("accuracy");
		ach1.setForeground(Color.decode("#616161"));
		ach1.setFont(new Font("���� ���",Font.BOLD,15));
		ach1.setSize(100,30);
		ach1.setLocation(490, 10);
		JLabel ach2 =new JLabel("accuracy");
		ach2.setForeground(Color.decode("#616161"));
		ach2.setFont(new Font("���� ���",Font.BOLD,15));
		ach2.setSize(100,30);
		ach2.setLocation(490, 10);
		JLabel ach3 =new JLabel("accuracy");
		ach3.setForeground(Color.decode("#616161"));
		ach3.setFont(new Font("���� ���",Font.BOLD,15));
		ach3.setSize(100,30);
		ach3.setLocation(490, 10);
		
		piechart sub1=new piechart((int)((accuracy[0]/CurrentNum[0])*100),Color.decode("#CDDC39"));
		sub1.setVisible(true);
		sub1.setBounds(480,40,80,80);
		piechart sub2=new piechart((int)((accuracy[1]/CurrentNum[1])*100),Color.decode("#CDDC39"));
		sub2.setVisible(true);
		sub2.setBounds(480,40,80,80);
		piechart sub3=new piechart((int)((accuracy[2]/CurrentNum[2])*100),Color.decode("#CDDC39"));
		sub2.setVisible(true);
		sub2.setBounds(480,40,80,80);
		
		
		//TODO:: scoreadding
		menu1.add(ach1);
		menu2.add(ach2);
		menu3.add(ach3);
		
		title.add(exiticon);
		title.add(backicon);
		title.add(titlelabel);
		title.add(notibar);
		
		menu1.add(sub1);
		menu1.add(sub2);
		menu1.add(sub3);
		
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
		titlename.setFont(new Font("���� ���",Font.BOLD,32));
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
		aboutlabel.setFont(new Font("���� ���",Font.PLAIN,32));
		aboutlabel.setLocation(100,30);
		
		
		String x = "<html>�� ���α׷��� ������� �н��ɷ��� �����ִ�<br>�н� ���α׷� �Դϴ�.</html>";
		JLabel aboutinsert = new JLabel(x);
		aboutinsert.setSize(800,100);
		aboutinsert.setFont(new Font("���� ���",Font.PLAIN,25));
		aboutinsert.setLocation(30,80);
		
		PaperPanel credit = new PaperPanel(600,250);
		credit.setBackground(Color.decode("#fafafa"));
		credit.setLocation(0,350);
		credit.setLayout(null);
		
		IconPanel crediticon = new IconPanel(".\\images\\credit.png",45,45);
		crediticon.setLocation(12,12);
		
				
		String credits[] = {"Credit", "<html>20144575 ��λ�<br>20144592 �̱��<br>20144564 ��α�"};
				
		JPanel creditpanel = new JPanel();
		creditpanel.setBackground(Color.decode("#00BCD2"));
		creditpanel.setSize(70,70);
		creditpanel.setLocation(20,20);
		creditpanel.setLayout(null);
		
		JLabel creaditlabel = new JLabel(credits[0]);
		creaditlabel.setSize(500,50);
		creaditlabel.setFont(new Font("���� ���",Font.PLAIN,32));
		creaditlabel.setLocation(100,30);
		credit.add(creaditlabel);
		
		JLabel creaditinsert = new JLabel(credits[1]);
		creaditinsert.setSize(800,100);
		creaditinsert.setFont(new Font("���� ���",Font.PLAIN,25));
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
	@SuppressWarnings("serial")
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
		titlelabel.setFont(new Font("���� ���",Font.BOLD,32));
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
		ach1.setFont(new Font("���� ���",Font.BOLD,15));
		ach1.setSize(100,15);
		ach1.setLocation(470, 50);
		JLabel ach2 =new JLabel("achievement");
		ach2.setForeground(Color.decode("#616161"));
		ach2.setFont(new Font("���� ���",Font.BOLD,15));
		ach2.setSize(100,15);
		ach2.setLocation(470, 50);
		JLabel ach3 =new JLabel("achievement");
		ach3.setForeground(Color.decode("#616161"));
		ach3.setFont(new Font("���� ���",Font.BOLD,15));
		ach3.setSize(100,15);
		ach3.setLocation(470, 50);
		
		
		JLabel menu1ach;
		menu1ach = new JLabel()
		{
			@Override
			 public void paint(Graphics g)
            {
				Integer menu1achie=new Integer(CurrentNum[0]*2);
				Graphics2D ga=(Graphics2D)g;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				g.drawString(menu1achie.toString()+"%",0,35);
            }
		};
		menu1ach.setSize(80,50);
		menu1ach.setForeground(Color.decode("#CDDC39"));
		menu1ach.setLocation(500,60);
		menu1ach.setFont(new Font("���� ���",Font.BOLD,28));
		menu1ach.setVisible(true);
		/*	
		JLabel menu1ach=new JLabel(menu1achie.toString()+"%");		
		menu1ach.setFont(new Font("���� ���",Font.BOLD,28));
		menu1ach.setForeground(Color.decode("#CDDC39"));
		menu1ach.setBounds(500,60,50,50);
		*/
		JLabel menu2ach;
		menu2ach = new JLabel()
		{
			@Override
			 public void paint(Graphics g)
            {
				Integer menu2achie=new Integer(CurrentNum[1]*2);
				Graphics2D ga=(Graphics2D)g;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				g.drawString(menu2achie.toString()+"%",0,35);
            }
		};
		menu2ach.setSize(80,50);
		menu2ach.setForeground(Color.decode("#CDDC39"));
		menu2ach.setLocation(500,60);
		menu2ach.setFont(new Font("���� ���",Font.BOLD,28));
		menu2ach.setVisible(true);
		
		JLabel menu3ach;
		menu3ach = new JLabel()
		{
			@Override
			 public void paint(Graphics g)
            {
				Integer menu3achie=new Integer(CurrentNum[2]*2);
				Graphics2D ga=(Graphics2D)g;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				g.drawString(menu3achie.toString()+"%",0,35);
            }
		};
		menu3ach.setSize(80,50);
		menu3ach.setForeground(Color.decode("#CDDC39"));
		menu3ach.setLocation(500,60);
		menu3ach.setFont(new Font("���� ���",Font.BOLD,28));
		menu3ach.setVisible(true);
			
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
	@SuppressWarnings("serial")
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
				Graphics2D ga=(Graphics2D)g;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				setText(Subject[choosedSub]);
				g.drawString(Subject[choosedSub], 0,40);
            }
		};
		titlelabel.setSize(500,50);
		titlelabel.setForeground(Color.decode("#FFFFFF"));
		titlelabel.setLocation(100,32);
		titlelabel.setFont(new Font("���� ���",Font.BOLD,32));
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
		
		PaperPanel QuestionPanel=new PaperPanel(width,350);
		QuestionPanel.setVisible(true);
		QuestionPanel.setLayout(null);
		QuestionPanel.setBackground(Color.decode("#FFFFFF"));
		QuestionPanel.setLocation(0, 100);
		
		JLabel Qnum;
		Qnum=new JLabel()
		{
			@Override
			public void paint(Graphics g)
	        {
				Integer temp= CurrentNum[choosedSub]+1;
				Graphics2D ga=(Graphics2D)g;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				
				setText(temp.toString());
				g.drawString((temp.toString()),20,32);	
	        }
			
		};
		Qnum.setSize(100,100);
		Qnum.setFont(new Font("���� ���",Font.BOLD,32));
		Qnum.setForeground(Color.decode("#00BCD2"));
		Qnum.setLocation(20,20);
		Qnum.setVisible(true);
		
		JLabel Questions;
		Questions=new JLabel(){
			
			private void drawaString(Graphics g, String text, int x, int y) {
				for (String line : text.split("\n"))
		            g.drawString(line, x, y += g.getFontMetrics().getHeight());
		    };
			
			
			@Override
			public void paint(Graphics g)
			{
				
				Graphics2D ga=(Graphics2D)g;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				setText(ALLQ[choosedSub][CurrentNum[choosedSub]]);
				drawaString(g,ALLQ[choosedSub][CurrentNum[choosedSub]],20,32);			}
			
		};
		Questions.setSize(500,400);
		Questions.setFont(new Font("���� ���",Font.BOLD,25));
		Questions.setForeground(Color.decode("#424242"));
		Questions.setLocation(20,20);
		Questions.setVisible(true);
		//////////////////////////////////////////////////////////////////////
		PaperPanel exp1=new PaperPanel(width,75,2);
		exp1.addMouseListener(new Correct());
		exp1.setName("1");
		exp1.setVisible(true);
		exp1.setLayout(null);
		exp1.setBackground(Color.decode("#FFFFFF"));
		exp1.setLocation(0,456);
		exp1.setVisible(true);
		exp1.addMouseListener(new Clickcolorgray());
		
		JLabel Q1;
		Q1=new JLabel(){
			private void drawaString(Graphics g, String text, int x, int y) {
				for (String line : text.split("<br>"))
		            g.drawString(line, x, y += g.getFontMetrics().getHeight());
		    };
			@Override
			public void paint(Graphics g)
			{
				Graphics2D ga=(Graphics2D)g;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				setText(ALLQ[choosedSub][CurrentNum[choosedSub]]);
				drawaString(g,ALLC[choosedSub][CurrentNum[choosedSub]][0],20,32);
			}
		};
		Q1.setSize(500,400);
		Q1.setFont(new Font("���� ���",Font.BOLD,20));
		Q1.setForeground(Color.decode("#424242"));
		Q1.setLocation(0,0);
		Q1.setVisible(true);
		
		
		PaperPanel exp2=new PaperPanel(width,75,2);
		exp2.addMouseListener(new Correct());
		exp2.setName("2");
		exp2.setVisible(true);
		exp2.setLayout(null);
		exp2.setBackground(Color.decode("#FFFFFF"));
		exp2.setLocation(0,533);
		exp2.setVisible(true);
		exp2.addMouseListener(new Clickcolorgray());
		
		JLabel Q2;
		Q2=new JLabel(){
			private void drawaString(Graphics g, String text, int x, int y) {
				for (String line : text.split("<br>"))
		            g.drawString(line, x, y += g.getFontMetrics().getHeight());
		    };
			@Override
			public void paint(Graphics g)
			{
				Graphics2D ga=(Graphics2D)g;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				setText(ALLQ[choosedSub][CurrentNum[choosedSub]]);
				drawaString(g,ALLC[choosedSub][CurrentNum[choosedSub]][1],20,32);
			}
		};
		Q2.setSize(500,400);
		Q2.setFont(new Font("���� ���",Font.BOLD,20));
		Q2.setForeground(Color.decode("#424242"));
		Q2.setLocation(0,0);
		Q2.setVisible(true);
		
		PaperPanel exp3=new PaperPanel(width,75,2);
		exp3.addMouseListener(new Correct());
		exp3.setName("3");
		exp3.setVisible(true);
		exp3.setLayout(null);
		exp3.setBackground(Color.decode("#FFFFFF"));
		exp3.setLocation(0,610);
		exp3.setVisible(true);
		exp3.addMouseListener(new Clickcolorgray());
		
		JLabel Q3;
		Q3=new JLabel(){
			private void drawaString(Graphics g, String text, int x, int y) {
				for (String line : text.split("<br>"))
		            g.drawString(line, x, y += g.getFontMetrics().getHeight());
		    };
			@Override
			public void paint(Graphics g)
			{
				Graphics2D ga=(Graphics2D)g;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				setText(ALLQ[choosedSub][CurrentNum[choosedSub]]);
				drawaString(g,ALLC[choosedSub][CurrentNum[choosedSub]][2],20,32);
			}
		};
		Q3.setSize(500,400);
		Q3.setFont(new Font("���� ���",Font.BOLD,20));
		Q3.setForeground(Color.decode("#424242"));
		Q3.setLocation(0,0);
		Q3.setVisible(true);
		
		PaperPanel exp4=new PaperPanel(width,75,2);
		exp4.addMouseListener(new Correct());
		exp4.setName("4");
		exp4.setVisible(true);
		exp4.setLayout(null);
		exp4.setBackground(Color.decode("#FFFFFF"));
		exp4.setLocation(0,687);
		exp4.setVisible(true);
		exp4.addMouseListener(new Clickcolorgray());
		
		JLabel Q4;
		Q4=new JLabel(){
			private void drawaString(Graphics g, String text, int x, int y) {
				for (String line : text.split("<br>"))
		            g.drawString(line, x, y += g.getFontMetrics().getHeight());
		    };
			@Override
			public void paint(Graphics g)
			{
				Graphics2D ga=(Graphics2D)g;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				setText(ALLQ[choosedSub][CurrentNum[choosedSub]]);
				drawaString(g,ALLC[choosedSub][CurrentNum[choosedSub]][3],20,32);
			}
		};
		Q4.setSize(500,400);
		Q4.setFont(new Font("���� ���",Font.BOLD,20));
		Q4.setForeground(Color.decode("#424242"));
		Q4.setLocation(0,0);
		Q4.setVisible(true);
		
		//System.out.println(temp.toString());
		
		//TODO::adding
		exp1.add(Q1);
		exp2.add(Q2);
		exp3.add(Q3);
		exp4.add(Q4);
		
		QuestionPanel.add(Questions);
		QuestionPanel.add(Qnum);
		paper.add(exp4);
		paper.add(exp3);
		paper.add(exp2);
		paper.add(exp1);
		paper.add(QuestionPanel);
		title.add(notibar);
		title.add(titlelabel);
		title.add(exiticon);
		title.add(backicon);
		paper.add(title);
		
		chapterview.add(paper);
		
	}
	@SuppressWarnings("serial")
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
		
		Circle exit;
		exit=new Circle(Color.decode("#CDDC39"))
		{
			{
				this.setLayout(null);
				this.setVisible(true);
				this.setLocation(0,0);
				this.setSize(87,87);
				this.addMouseListener(new ClickcolorCircleExit());
				this.setOpaque(false);
			}
			public void paintComponent(Graphics graphics)
			{
				
				super.paintComponent(graphics);
				//graphics.setColor(getBackground());
				//setForeground(Color.decode("#CDDC39"));
				super.paintComponent(graphics);
				setBackground(new Color(0,0,0,0));
				
				Graphics2D ga=(Graphics2D)graphics;
				ga.setRenderingHint( RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				graphics.fillOval(0,0, 87, 87);
			  
				
			}
			
		};
		/*
		Circle exit=new Circle(Color.decode("#CDDC39"));
		exit.setVisible(true);
		
		exit.addMouseListener(new ClickcolorCircleExit());
		exit.setLocation(0,0);
		exit.setSize(87,87);
		*/
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
		playlabel.setFont(new Font("���� ���",Font.BOLD,28));
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
		scorelabel.setFont(new Font("���� ���",Font.BOLD,28));
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
		aboutlabel.setFont(new Font("���� ���",Font.BOLD,28));
		aboutlabel.setVisible(true);
		aboutlabel.setSize(120,30);
		aboutlabel.setLocation(418,290);
		
		IconPanel abouticon=new IconPanel(".\\images\\about.png",45,45);
		abouticon.setLocation(30,30);
		
		JLabel titlename=new JLabel("S PLAY");
		titlename.setVisible(true);
		titlename.setFont(new Font("���� ���",Font.BOLD,42));
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
				btn.setOpaque(false);
				btn.SetColor(Color.decode("#AFB42B"));
				btn.setBackground(new Color(0,0,0,0));
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
				//btn.repaint();
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
			if(CurrentNum[choosedSub]>48)
			{
				CurrentNum[choosedSub]=0;
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
	class Correct extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			JPanel source=(JPanel)e.getSource();
			if(source.getName().equals(ALLA[choosedSub][CurrentNum[choosedSub]]))
			{
				source.setBackground(Color.decode("#CDDC39"));
				source.updateUI();
				accuracy[choosedSub]++;
			}
			else
			{
				source.setBackground(Color.decode("#F44336"));
				source.updateUI();
			}
			
			//CurrentNum[choosedSub]++;
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			if(CurrentNum[choosedSub]>48)
			{
				source.setBackground(Color.decode("#FFFFFF"));
				playmenu.setLocation(posX,posY);
				playmenu.setVisible(true);

				chapterview.revalidate();
				level=2;

			}
			else
			{	
				System.out.println(CurrentNum[choosedSub]);
				source.setBackground(Color.decode("#FFFFFF"));
				chapterview.dispose();
				drawchapterview();
			}
			CurrentNum[choosedSub]++;
		}
	}
	
}
