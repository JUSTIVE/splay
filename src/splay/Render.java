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
	private double accuracy[];	//과목당 정확도
	private int achievement[];	//진행상황
	private int choosedSub;	//현재 선책한 과목의 번호 
	private int CurrentNum[]; 
	private String ALLQ[][];
	private String ALLA[][];
	private String ALLC[][][];
	private String archQ[]= {"다음 중에서 컴퓨터의 기본\n구성요소로 볼 수 없는 것은?",
			"컴퓨터의 단어 길이로서 일반적으로\n사용되지 않는 것은?" ,
			"12비트의 주소로 지정할 수 있는\n기억 장소의 수는 모두 몇 개 인가?",
			"다음 중 에서 시스템 버스에\n속하지 않는 것은?" ,
			"키보드의 어느 한 키를 눌렀을 때\nCPU로 전송되는 정보는 ASCII 코드\n7비트에 한 비트가 더해져서 8비트가 된다.\n그 한 비트는 무엇인가?",
			"다음 중에서 슈퍼컴퓨터의\n구성 형태가 아닌 것은?",
			"산술 연산 및 논리 연산을 수행하는\nCPU 구성 요소는 어느 것인가?",
			"다음 중에서 명령어 사이클에 속하는\n부사이클(subcycle)이 아닌 것은?",
			"클록 주파수가 2GHz 인 CPU에서\n인출 사이클에 소요되는 시간은 얼마인가?",
			"4-단계 파이프라인에서 10개의 명령어들을\n실행되는 데는 모두 몇 사이클이\n소요되는가?",
			"4-way 슈퍼스칼라 프로세서에서는\n매 사이클마다 몇 개씩의 명령어들이\n실행될 수 있는가?",
			"기억장치 주소를 나타내는 오퍼랜드 필드\n가 14비트 라면, 이 명령어에 의해 직접\n주소지정될 수 있는 기억장치 용량은\n얼마인가?",
			"간접 주소지정 방식을 사용하는 명령어는\n실행 사이클 동안에 기억장치를 몇 번\n액세스하는가?",
			"2의 보수 '11101000'을 10진수로\n변환하면 어떤 값이 되는가?",
			"두 수를 비교하기 위해서 사용되는\n논리 연산은 무엇인가?",
			"특정 비트들을 마스크(mask) 시키기\n위하여 사용되는 논리 연산은 무엇인가?",
			"A 레지스터에 2의 보수로 표현된 데이터\n'1010101'이 저장되어 있을 때,산술적 우측\n시프트를 두 번 수행한 결과는 다음 중\n어느것인가?",
			"A 레지스터에 '01011011'이 저장되어\n있고 C플래그에 '1'이 저장되어 있을때,\nSHRC 연산을 한 번 수행한 결과는\n다음 중 어느 것인가?",
			"다음 중 부동소수점 덧셈 과정에서\n필요하지 않은 연산은 어느 것인가?",
			"제어 유니트에서 다음에 실행할\n마이크로명령어의 주소를 가지고 있는\n레지스터는 어느 것인가?",
			"제어 기억장치는 다음 중의 어느 기억장치\n소자를 이용하여 구현되는가?",
			"다음 중에서 서브루틴 레지스터(SBR)가\n사용되지 않는 마이크로-연산은\n어느 것인가?",
			"다음 중 CAR에 적재될 수\n없는 값은 어느 것인가?",
			"두 개의 연산 필드가 각각 4비트씩으로\n구성되어 있다. 수평적 마이크로프로그래밍\n방식이 사용되는 경우라면 최대 몇 개의\n제어 신호들이 발생될 수 있는가?",
			"두 개의 연산 필드가 각각 4비트씩으로\n구성되어 있다. 수직적 마이크로프로그래밍\n방식이 사용되는 경우라면 최대 몇 개의\n제어 신호들이 발생될 수 있는가?\n단, 해독기는 두 개만 사용한다고 가정한다.",
			"내부 기억장치와 외부 기억장치를\n구분하는 기준이 되는 것은?",
         		"기억장치 유형중들 중에서 주소를 \n사용하지 않고 저장된 비트 패턴을 \n비교하여 액세스할 위치를\n찾아내는 기억장치는 어느것인가?",
         		"어떤 기억장치의 액세스를 시작하는\n순간부터 다음 액세스를 다시 시작할 수 \n있을 때까지의 시간 간격을 나타내는 것은?",
         		"단어 단위로 주소를 지정하는 시스템에서\n단어의 길이가 32비트라면, 10비트의\n주소로 직접 액세스 할 수 있는 \n기억 장치용량을몇 바이트인가?",
         		"라인의 수가 64개인 캐시를 4-way\n세트-연관 사상 방식으로 구성하였다면,\n주기억장치 블록이 적재될 수 있는\n캐시 라인은 몇 개 인가?",
         		"CPU가 기억장치를 500번 엑세스하는\n동안에 원하는 데이터가 캐시에 있었던\n횟수가 450번이었다면, 캐시 적중률은\n얼마인가?",
         		"탐색 시간이 가장 짧은 디스크는 무엇인가?",
         		"MTTF = 2000시간인 디스크들을 8개\n 접속하여 구성한 디스크 배열의\nMTTF시간은 얼마가 되는가?",
         		"오류 검출을 위하여 해밍 코드를\n이용하는 것은?",
         		"플래시 메모리 중에서 TLC는 하나의 셀을\n이용하여 몇가지 값들을 저장할 수 있는가?",
         		"CD-ROM에서 트랙의 수는 몇 개인가?",
         		"다음 중에서 SSD의 수명을 연장시키기\n위한 기술은 어느 것인가?",
            		"양방향 전송 기능을 가져야 하는 버스는\n어느 것인가?",
         		"펜티엄4 마이크로프로세서의\n주요 버스폭은 36비트이다. 주소지정이\n 가능한 최대 기억장치 용량은?",
         		"버스 중재기가 여러 개\n필요한 중재 방식은?",
         		"기억장치-사상 I/O 방식을 사용하는\n시스템에서 주소가 12비트이면,\nI/O장치를 최대 몇 개까지\n접속할 수 있는가?",
         		"DMA제어기의 한계를 극복하기 위하여\n 사용하는 방식은 어느 것 인가?",
         		"버스 클록의 주파수가 100MHz이고,\n데이터 버스 폭이 64비트라면,\n이 버스의 대역폭은 몇\n[Mbytes/sec]가 되는가?",
            		"가장 작은 단위의 병렬처리는 \n다음 중 어떤 병렬성을 이용하는 것인가?",
         		"여러 개의 데이터들에 대하여 \n같은 연산을 동시에 처리하는 데\n적합한 시스템 조직은 어느 것인가?",
         		"공유-기억장치 컴퓨터시스템에서\n사용되는 상호연결 구조가 아닌 것은?",
         		"9개의 노드들이 링(ring)구조로\n 연결되어 있다. 링크가 양방향이라면\n네트워크 지름은 얼마인가?",
         		"4층 트리 구조로 연결할 수 있는\n노드의 수는 모두 몇 개인가?",
         		"다수의 프로세서들이 독립적으로\n프로그램을 수행하는 시스템 조직은\n어느것인가?",
         		"16개의 프로세서들과 16개의 \n기억장치 모듈들을 오메가 네트워크로\n연결하는데 필요한 2X2 스위치\n소자의 수는 모두 몇 개인가?"
   };
	private String arch_ans[]= {"2","3","4","3","3","2","3","2","2","3",
			"4","2","3","3","1","4","2","3","2","3",
			"3","3","2","3","2","4","3","2","2","2",
			"3","4","2","2","3","1","1","2","4","3",
			"2","1","1","1","2","3","2","2","4","4"
			};
	private String archchose[][]= {{"①CPU","②캐시","③입출력 장치","④기억장치"},
			{"①8비트","②32비트","③24비트","④64비트"},
			{"①12","②1024","③2048","④4096"},
			{"①주소 버스","②데이터 버스","③I/O 버스","④제어 버스"},
			{"①제어 비트","②부호 비트","③패리티 비트","④올림수 비트"},
			{"①클러스터 컴퓨터","②임베디드 컴퓨터","③MPP","④파이프라인 슈퍼컴퓨터"},
			{"①스택","②제어 유니트","③레지스터 세트","④ALU"},
			{"①인출 사이클","②간접 사이클","③제어 사이클","④실행 사이클"},
			{"①0.5ns","②1.5ns","③2ns","④6ns"},
			{"①10","②13","③14","④40"},
			{"①1개","②2개","③4개","④8개"},
			{"①1KByte","②2KByte","③4KByte","④8KByte"},
			{"①한 번","②두 번","③세 번","④액세스하지 않는다."},
			{"①-12","②48","③-24","④232"},
			{"①AND 연산","②OR 연산","③XOR 연산","④NOT연산"},
			{"①AND 연산","②OR 연산","③XOR 연산","④NOT연산"},
			{"①00101011","②01010110","③11010110","④11101011"},
			{"①00101101","②10101101","③10110111","④10110110"},
			{"①지수 조정","②정규화","③지수 덧셈"," ④가수 덧셈"},
			{"①CBR","②CAR","③IR","④PC"},
			{"①RAM","②CAM","③ROM","④DISK"},
			{"①호출(CALL)","②복귀(RET)","③점프(JUMP)", "④조건부 호출(conditional CALL)"},
			{"①SBR의 내용","②사상(mapping)의 결과값","③연산 필드 비트들","④주소 필드(ADF)의 값"},
			{"①4개","②8개","③16개","④32개"},
			{"①8개","②16개","③32개","④64개"},
			{"①기억장치의 용량","②기억장치의 위치","③기억장치의 액세스 속도","④CPU의 직접 액세스 가능 여부"},
			{"①직접 액세스 기억장치","②임의 액세스 기억장치","③연관 액세스 기억장치","④순차적 액세스 기억장치"},
			{"①기억장치 액세스 시간","②기억장치 사이클 시간","③데이터 전송 시간","④데이터 복구 시간"},
			{"①2K 바이트","②4K 바이트","③8K 바이트","④16K 바이트"},
			{"①1개","②4개","③16개","④64개"},
			{"①0.45","②0.5","③0.9","④0.95"},
			{"①단일-헤드디스크","②다중-헤드 디스크","③이동-헤드 디스크","④고정-헤드 디스크"},//"고정-헤드 디스크"},
			{"①200시간","②250시간","③400시간","④1600시간"},//{"250시간"},
			{"①RAID-1","②RAID-2","③RAID-3","④RAID-5"},//{"RAID-2"},
			{"①2","②4","③8","④16"},//{"8"},
			{"①한 개","②200개","③2400개","④2400개 이상"},//{"한 개"},
			{"①마모 평준화","②쓰레기 수집","③주소매핑","④초과 대비 용량"}	,
			{"①주소버스","②데이터 버스","③제어 버스","④인터럽트 버스"},//{"데이터 버스"},
			{"①16MByte","②64MByte","③32GByte","④64GBtye"},//{"64GByte"},
			{"①데이지-체인 방식","②중앙집중식 고정 우선순위 방식","③분산식 고정 우선순위 방식","④소프트웨어 플링 방식"},//{"분산식 고정 우선순위 방식"},
			{"①12개","②2048개","③4096개","④1024개"},//{"2048개"},
			{"①I/O프로세서","②다중 인터럽트","③프로그램을 이용한 I/O","④멀티플렉싱"},//{"I/O 프로세서"},
			{"①800","②64","③640","④80"},
			{"①명령어-단위 병렬성","②태스크-단위 병렬성","③작업-단위 병렬성","④스레드-단위 병렬성"},//{"명령어-단위 병렬성"},
			{"①SISD","②SIMD","③MISD","④MIMD"},//{"SIMD"},
			{"①버스","②다단계 상호연결망","③매시","④크로스바 스위치"},//{"매시"},
			{"①2","②4","③5","④8"},//{"4"},
			{"①6개","②15개","③16개","④31개"},//{"15개"}
			{"①SISD","②SIMD","③MISD","④MIMD"},
			{"①4개","②8개","③16개","④32개"}
		};
	private String DSQ[]={"시간 복잡도 함수 n2+10n+8을\n빅오 표기법으로 나타내면?",
			"시간 복잡도 함수가 7n+10이라면\n이것이 나타내는 것은 무엇인가?",
			"O(n2)의 시간 복잡도를 가지는 알고리즘에서\n입력의 개수가 2배로 되었다면 실행시간은\n어떻게 되는가?",
			"O(n2)의 시간 복잡도를 가지는 알고리즘이\n1개의 입력을 1초에 처리한다.\n이 알고리즘이 10개의 입력을 처리하는\n대략적인 시간은?",
			"순환 호출을 하였을 경우에 활성\n레코드들이 저장되는 위치는 어디인가?",
			"다음 중 활성 레코드에 저장되지\n않는 것은 무엇인가?",
			"다음 중 순환 호출이 불가능한 언어는?",
			"하나의 함수가 호출할 수 있는\n순환 호출의 개수는?",
			"float a[100]으로 선언된 배열의\n시작 주소를 1000번지라고 할 때, 배열의\n10번째 요소의 주소는 몇 번지인가?",
			"int i=10; int *p; *p=8; 의 문장이\n수행되면 i 값은 얼마인가?",
			"int i=10; int *p; (*p)--; 의 문장이\n수행되면 i 값은 얼마인가?",
			"int a[10]; int*p; p=a; *p++=5; 의 문장이\n수행되면 i값은 얼마인가? ",
			"포인터 p에 대한 연산중 p와 같은 것은?",
			"삽입과 삭제 작업이 자주 발생할 때 실행\n시간이 가장 많이 소요되는 자료 구조는?",
			"다음 중 NULL 포인터(NULL pointer)가\n존재하지 않은 구조는 어느 것인가?",
			"리스트의 n번째 요소를 가장 빠르게\n찾을 수 있는 구현 방법은 무엇인가?",
			"단순 연결 리스트의 노드들을 노드 포인터\np로탐색하고자 한다. p가 현재 가리키는\n노드에서 다음 노드로 가려면 어떻게\n하여야 하는가?",
			"단순 연결 리스트의 관련 함수 f가\n헤드 포인터 head를 변경 시켜야 한다면\n함수 매개 변수로 무엇을 받아야 하는가?",
			"스택을 가장 효과적으로 이용하는 기법은?",
			"스택에서 삽입 작업이 발생하면\ntop의 값은?",
			"배열로 구현된 스택에서 top이 3이면\n현재 스택에 저장된 요소들의 개수는?",
			"다음 중 배열로 구현된 스택에서\n공백 상태에 해당하는 조건은?\n또 포화 상태에 해당되는 조건은?",
			"크기가 8인 원형 큐에서 front가 3이고\nrear가 5라고 하면 현재 원형 큐에\n저장된 요소들의 개수는?",
			"큐에 항목들을 삽입하고 삭제하는 연산은\n시간 복잡도가 어떻게 되는가?",
			"크기가 10이고 front가 3,rear가 5인\n원형 큐에서 새로운 항목이 삽입되었을\n경우,배열의 어떤 인덱스에 들어가는가?",
			"공백 상태의 연결된 큐에서 새로운 항목이\n삽입되었다.변화되는 포인터는?",
			"다음에서 트리 구조로 나타내기에\n적합하지 않은 것은?",
			"메모리상에 배열로 저장할 때\n가장 낭비가 큰 트리는?",
			"다음 중 같은 개수의 노드가 저장되는 경우,\n가장 높이가 낮은 트리는?",
			"이진트리에서 높이가 5일 때,\n최대 몇 개의 노드를 가질 수 있는가?",
			"히프 트리에서 노드가 삭제되는\n위치는 어디인가?",
			"히프에서의 마지막 노드는 다음 중\n어떤 조건을 만족하는가?",
			"히프 연산 중에서 하나의 노드가\n삽입되거나 삭제되는 시간은\n무엇에 비례하는가?",
			"최소 히프에서 가장 작은 데이터가\n있는 노드는?",
			"다음 중 안정적인 정렬 방법이\n아닌 것은 무엇인가?",
			"다음 중 삽입 정렬이 가장 효율적으로\n적용될 수 있는 때는?",
			"다음 중 추가 공간을 꼭 필요로 하는\n정렬 기법은?",
			"정렬해야 할 레코드의 크기가 무척 크다면\n다음 중 어떤 정렬 방법을 사용하는 것이\n가장 좋은가?",
			"정점이 3개이고 간선이 3개 있는 무방향\n그래프에서 가능한 신장 트리의 개수는?",
			"정점의 개수를 n, 간선의 개수를 e라고\n할 때, 인접 행렬에서 특정 정점의 차수를\n계산하는 연산의 시간 복잡도는?",
			"정점의 개수를 n, 간선의 개수가 e인\n무방향 그래프를 인접 리스트로\n표현 하였을 경우,인접 리스트상의\n총 노드의 개수는?",
			"다음 중 큐를 사용하는 알고리즘은?",
			"해싱에 대한 설명 중 틀린 것은?",
			"해싱에서 충돌은 언제 발생하는가?",
			"선형 조사법에 대한 설명 중 틀린 것은?",
			"체이닝에서 새로운 탐색 키를 삽입하는\n시간은 무엇에 비례하는가?",
			"이진 탐색에 대한 설명 중 틀린 것은?",
			"이진 탐색 트리에 대한 설명 중 틀린것은? ",
			"AVL트리에 대한 설명 중 맞는 것은?",
			"AVL트리에서 회전은 언제 이루어지는가?"};
	private String DS_ans[]={"3","1","3","4","4","4","3","3","4","4",
			"3","1","1","1","2","1","3","2","4","4",
			"4","1","2","1","1","3","2","1","3","4",
			"1","3","2","2","2","2","4","2","3","2",
			"2","2","1","2","4","3","1","3","4","2"};
	private String DSchose[][]={{"①O(n)","②O(nlog2n)","③O(n2)","④O(n2log2n)"},
			{"①연산의 횟수","②프로그램의 컴파일 시간","③프로그램이 차지하는 메모리의 양","④입력 데이터의 총 개수"},
			{"①변함 없다.","②2배","③4배","④8배"},{"①1초","②10초","③100초","④10000초"},
			{"①순환 호출 함수 내부","②변수","③배열","④스택"},
			{"①매개 변수의 값","②함수 호출이 끝나고 복귀할 주소","③지역 변수","④순환 호출의 순차 번호"},
			{"①C언어","②Pascal 언어","③Basic 언어","④Java 언어"},
			{"①1번","②2번","③스택이 허용되는 한도","④무제한"},
			{"①1000번지","②1010번지","③1020번지","④1040번지"},
			{"①11","②10","③9","④8"},
			{"①11","②10","③9","④8"},
			{"①a[0]","②a[1]","③a[2]","④a[3]"},
			{"①&*p","②*&p","③&&p","④**p"},
			{"①배열로 구현된 리스트","②단순 연결 리스트","③원형 연결 리스트","④이중 연결 리스트"},
			{"①단순 연결리스트","②원형 연결 리스트","③이중 연결 리스트","④헤더 노드를 가지는 단순 연결 리스트"},
			{"①배열","②단순 연결 리스트","③원형 연결 리스트","④이중 연결 리스트"},
			{"①p++;","②p--","③p=p->link;","④p=p->data;"},
			{"①head","②&head","③*head","④head->link;"},
			{"①루핑(looping)","②알고리즘(algorihm)","③반복(iteration)","④순환(recursize)"},
			{"①top == 0","②top == 1","③top = top-1","④top = top+1"},
			{"①1","②2","③3","④4"},
			{"①top==-1","②top == 0","③top == (MAX_STACK_SIZE-1)","④top == MAX_STACK_SIZE"},
			{"①1","②2","③3","④4"},
			{"①O(1)","②O(log2n)","③O(n)","④O(n2)"},
			{"①0","②9","③8","④10"},
			{"①front","②rear","③front 와 rear","④둘 다 변화하지 않는다."},
			{"①가족의 계보","②행렬","③연산 수식","④회사 종업원에 직급상의 상하 관계"},
			{"①경사 트리","②이진 트리","③포화 트리","④완전 트리"},
			{"①경사 트리","②이진 트리","③포화 트리","④완전 트리"},
			{"①26개","②8개","③32개","④31개"},
			{"①루트","②마지막 노드","③가장 최근에 삽입된 노드","④가장 먼저 삽입된 노드"},
			{"①항상 왼쪽 노드이다.","②항상 오른쪽 노드이다.","③항상 맨 마지막 레벨에 있다.","④형제 노드보다 작지 않다."},
			{"①노드의 개수","②트리의 높이","③항상 일정하다","④예측이 불가능하다."},
			{"①마지막 노드","②첫 번째 노드","③중간 노드","④알 수 없다."},
			{"①삽입 정렬","②선택 정렬","③히프 정렬","④셸 정렬"},
			{"①역순으로 정렬되어 있다.","②어느 정도 정렬되어 있다.","③레코드들의 크기가 크다.","④메모리 공간이 여유가 있다."},
			{"①삽입 정렬","②퀵정렬","③히프 정렬","④합병 정렬"},
			{"①삽입 정렬","②선택 정렬","③버블 정렬","④셸 정렬"},
			{"①1개","②2개","③3개","④4개"},
			{"①O(log2n)","②O(n)","③O(n+e)","④O(e)"},
			{"①e개","②2e개","③n개","④2n개"},
			{"①깊이 우선 탐색","②너비 우선 탐색","③Dijkstra의 최단 경로 알고리즘","④Floyd의 최단 경로 알고리즘"},
			{"①충돌이 일어나지 않는다면 시간 복잡도가 O(log2n)이다.","②선형 조사법의 경우, 해시 테이블은 배열로 구성된다.","③해시 함수는 해시 테이블의 인덱스를 생성한다.","④보통 해시 테이블의 크기는 소수(Prime number)이다."},
			{"①탐색 키가 같은 경우","②해시 함수의 값이 같은 경우","③같은 해시 함수를 사용하는 경우","④탐색 키의 길이가 같은 경우"},
			{"①체이닝에 비하여 메모리를 절약할 수 있다.","②충돌이 일어나면 해시 테이블의 비어 있는 곳에 탐색 키를 저장한다.","③다양한 해시 함수를 적용할 수 있다.","④탐색이 실패했을 때나 성공했을 때나 수행 시간은 같다."},
			{"①적재율","②테이블에 있는 전체 항목의 개수","③버켓의 연결 리스트의 항목 개수","④테이블의 크기"},
			{"①배열의 크기가 2의 거듭제곱 값이어야 한다.","②반드시 배열의 요소들은 정렬이 되어 있어야 한다.","③요소들의 삽입이나 삭제는 상당한 시간을 필요로 한다.","④탐색의 시간 복잡도는 O(log2n)이다."},
			{"①비균형 이진 탐색 트리의 경우, 성능이 순차 탐색과 동일할 수도 있다.","②이진 탐색에 비하여 삽입이나 삭제가 쉽다.","③탐색 연산의 성능은 이진 탐색 트리의 높이에 비례한다.","④평균적으로 이진 탐색에 비하여 탐색 속도가 더 빠르다."},
			{"①트리를 탐색하는 과정에서 재구성을 하는 경우도 있다.","②루트 노드에서 단말 노드까지의 경로의 길이는 모두 같다.","③모든 왼쪽 서브 트리의 높이는 오른쪽 서브 트리와 같다.","④이진 탐색 트리의 탐색 연산을 변경 없이 사용할 수 있다."},
			{"①삽입 전","②삽입 후","③탐색 중","④탐색 후"}
			};
	private String ClangQ[]={
              		 "컴퓨터에 특정한 '작업'을 지시하는 것은?",
              		 "소스 파일을 컴파일하면 생기는 파일은?",
              		 "C언어 프로그램의 본체가 되는 함수는?",
               		 "프로그램이 동작할때 처리과정을 뭐라고 \n하는가?",//알고리즘
               		 "알고리즘을 표현하는 방법중 옳지 않은것은?",
             	  	 "문자열을 출력할때 사용하는 함수는?",
              		 "변수를 선언할때 선언할 수 있는 위치로 \n옳은것은?",
              		 "문자와 수치를 출력할 때 이용하는것은?",//변환 사양
              		 "다음중 C언어에 존재하지 않는것은?",//문자,문자열,수치,이미지
              		 "특수할 문자를 나타낼때 사용하는것은?", //이스케이프 스퀀스

              		 "값을 입력받을때 사용하는것은?",
              		 "문자를 저장할때의 변수는?",//char
              		 "0.5를 저장할 수 있는 변수는?",//float
              		 "키보드 수치를 입력받을때 사용하는 \n것은?",
              		 "키보드로 문자를 입력받을때 사용하는\n것은?",
              		 "a++와 같은것은 무엇인가?",
              		 "a *= 10과 같은것은 무엇인가?",
              		 "조건문의 종류가 아닌것은?",
              		 "switch문에서 문을 빠져나갈때 쓰는 \n것은?",
              		 "if문에서 모두 조건의 예외를 처리할때 \n쓰는것은?",//else   

               		 "반복문의 종류가 아닌것은?",
                	 "반복문에서 블록 안의 처리를 종료하는것은?",//break
               		 "반복문이 참일때 반복을 실행하는 반복문은?",//for
               		 "표준적인 처리에 대해서 미리 정의되어있는\n함수는?",//표준 라이브러리 함수
              		 "함수로 값을 전달할 때 사용하는것은?",//인수
               		 "값을 반환할때 사용하는 명령어는?", //return
               		 "함수를 선언한 장소에서 그 함수가 끝날 \n때까지 이용할 수 있는 변수는?",//지역변수
               		 "초기화 값을 지정해서 배열을 초기화 할때 \n사용하는 기호는?",
               		 "배열의 값을 기억시키기 위해서 요소를 \n지정하고 값을 대입할때 \n사용하는것은?",
               		 "포인터가 가르키는 변수의 값을 \n보고싶을때 사용하는것은?",

               		 "int *p = a;printf('%d',p);\n일때 출력되는 값은?",//a의 주소값
               		 "문자열을 복사하는 함수는?",//strcpy;
               		 "문자열을 비교하는 함수는?",//strcmp;
              		 "구조체를 독자적인 형 이름으로 사용하기\n 위해 사용되는것은?",//typedef
              		 "구조체의 멤버를 액세스하기 위해서\n 사용되는 연산자는?",//.
               		 "배열과 구조체의 차이점은 무엇인가?",
              		 "다음중 구조체가 담을수 없는것은?",
               		 "포인터 배열일때 인덱스 값을 찾기위해\n 사용되는것은?",//[]
               		 "2차원 포인터가 가르키는 값은 무엇인가?",//1차원 포인터의 주소값
               		 "파일을 불러올때 사용하는 함수는?",//fopen
               
              		  "스트림의 서식지정 입력을 할 수 있는 \n함수는?",//fscanf
               		 "파일 포지션을 이용할때 사용하는 함수는?",//fseek
               		 "파일포인터의 위치를 처음으로 되돌려주는 함수는?",//rewind
               		 "힙영역으로의 메모리 공간 할당을 하는 함수는?",//malloc
               		 "동적할당을 해제할때 사용하는 함수는?",//free
               		 "동적할당에서 할당을 추가할때 사용하는 함수는?",
               		 "마지막으로 들어온 자료가 가장 먼저 \n나가는 자료구조는?",
               		 "동적할당의 장점으로 옳지 않은것은?",
               		 "정적할당의 장점으로 옳은것은?",
               		 "연결리스트중에서 위치를 사용할때 \n가장 빠른 연결리스트는?"//배열연결리스트
            };
	private String Clangchose[][]={
		      {"①프로그램",	"②힙",	"③동적할당",	"④알고리즘"},
		      {"①오브젝트",	"②파일",	"③메모리",	"④에디터"},
		      {"①printf",	"②sub",	"③main",	"④scanf"},
		      {"①프로그램",	"②알고리즘",	"③컴파일",	"④디버그"},
		      {"①순서도","②자연어","③가상코드",	"④함수"},
		      {"①scanf","②printf","③random","④fclose"},
		      {"①맨끝","②맨처음","③아무데나","④선언 불가"},
		      {"①변환 사양","②작업","③컴파일","④오브젝트 파일"},
		      {"①문자","②문자열","③이미지","④수치"},
		      {"①특수문자","②아스키코드","③이스케이프 스퀀스","④숫자"},
		      
		      {"①scanf","②printf","③random","④fclose"},
		      {"①int","②float","③double","④char"},
		      {"①float","②int","③long","④char"},
		      {"①scanf","②random","③printf","④fopen"},
		      {"①random","②getchar","③calloc","④free"},
		      {"①a+=1","②a == a+1","③a = a+a","④a = a-1"},
		      {"①a = a-10","②a = a*10","③a = a+10","④a = a/10"},
		      {"①if","②switch","③if~else","④for"},
		      {"①continue","②break","③goto","④jump"},
		      {"①else","②etc","③for","④while"},
		      
		      {"①for","②do~while","③continue","④while"},
		      {"①break","②continue","③goto","④else"},
		      {"①for","②while","③if","④switch"},
		      {"①사용자 지정함수","②조건문","③표준 라이브러리 함수","④조건문"},
		      {"①변수","②인수","③정수","④소수"},
		      {"①return","②continue","③break","④goto"},
		      {"①전역변수","②상수","③지역변수","④소수"},
		      {"①{ }","②[ ]","③( )","④:"},
		      {"①탐색","②인덱스","③링크","④구조체"},
		      {"①*","②&","③%","④!"},

		      {"①a의 내용","②p의 주소값","③a의 주소값","④쓰레기 값"},
		      {"①strcpy","②strcmp","③typedef","④malloc"},
		      {"①strcpy","②strcmp","③typedef","④malloc"},
		      {"①calloc","②free","③typedef","④strcmp"},
		      {"①.","②,","③-","④?"},
		      {"①값을 여러개 저장한다","②한가지 자료형만 저장가능하다","③여러개 만들수 있다","④초기화를 안할 수 있다."},
		      {"①정수","②소수","③함수","④문자열"},
		      {"①[]","②{}","③()","④=="},
		      {"①쓰레기값","②1차원 포인터의 주소값","③2차원 주소값의 결과값","④1차원 포인터의 결과값"},
		      {"①fopen","②fclose","③fprintf","④malloc"},

		      {"①fopen","②fscanf","③printf","④scanf"},
		      {"①fseek","②rewind","③malloc","④free"},
		      {"①malloc","②fseek","③rewind","④calloc"},
		      {"①free","②malloc","③fscanf","④printf"},
		      {"①free","②printf","③scanf","④malloc"},
		      {"①malloc","②scanf","③printf","④calloc"},
		      {"①링크드리스트","②배열연결리스트","③스택","④리스트 노드"},
		      {"①공간 사용이 자유롭다","②시간이 적게걸린다","③메모리 사용이 효율적이다","④공간 제약이 없다."},
		      {"①속도가 빠르다","②공간 사용이 자유롭다","③메모리 사용이 효율적이다","④공간 제약이 없다"},
		      {"①링크드리스트","②스택","③배열연결리스트","④없다"}
		};
	private String Clang_ans[] = {
			"1","1","3","2","4","2","2","1","3","3",
			"1","4","1","1","2","1","2","4","2","1",
			"3","1","3","2","1","3","1","2","1","3",
			"1","2","3","1","2","3","1","2","1","2",
			"2","1","3","2","1","4","3","2","1","3"}; 
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
		
		JLabel ach1 =new JLabel("accuracy");
		ach1.setForeground(Color.decode("#616161"));
		ach1.setFont(new Font("맑은 고딕",Font.BOLD,15));
		ach1.setSize(100,30);
		ach1.setLocation(490, 10);
		JLabel ach2 =new JLabel("accuracy");
		ach2.setForeground(Color.decode("#616161"));
		ach2.setFont(new Font("맑은 고딕",Font.BOLD,15));
		ach2.setSize(100,30);
		ach2.setLocation(490, 10);
		JLabel ach3 =new JLabel("accuracy");
		ach3.setForeground(Color.decode("#616161"));
		ach3.setFont(new Font("맑은 고딕",Font.BOLD,15));
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
		menu1ach.setFont(new Font("맑은 고딕",Font.BOLD,28));
		menu1ach.setVisible(true);
		/*	
		JLabel menu1ach=new JLabel(menu1achie.toString()+"%");		
		menu1ach.setFont(new Font("맑은 고딕",Font.BOLD,28));
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
		menu2ach.setFont(new Font("맑은 고딕",Font.BOLD,28));
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
		menu3ach.setFont(new Font("맑은 고딕",Font.BOLD,28));
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
		Qnum.setFont(new Font("맑은 고딕",Font.BOLD,32));
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
		Questions.setFont(new Font("맑은 고딕",Font.BOLD,25));
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
		Q1.setFont(new Font("맑은 고딕",Font.BOLD,20));
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
		Q2.setFont(new Font("맑은 고딕",Font.BOLD,20));
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
		Q3.setFont(new Font("맑은 고딕",Font.BOLD,20));
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
		Q4.setFont(new Font("맑은 고딕",Font.BOLD,20));
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
