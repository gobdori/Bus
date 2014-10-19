package bus.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class BusMainFrm extends JFrame
{
	//프레임의 맨 위에 들어갈 패널(레이아웃 고르는 패널이기도함)
	MenuPnl menuPnl = new MenuPnl(this);
	
	//카드레이아웃일 경우 쓸 패널
	CardPanel cardPnl = new CardPanel();
	
	//화면에 출력될 제일 큰 박스
	Box mainbx = Box.createVerticalBox();
	
	//메인프레임 생성자(이 프레임을 만들 때 씀)
	BusMainFrm()
	{
		//창을 닫았을 때 실제로 꺼지게 만드는 옵션. 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//레이아웃을 고르기 위한 패널 추가
		add(menuPnl);
		
		//화면의 크기를 알아와서 중앙에 띄움
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-800)/2, (screenSize.height-90)/2);
		
		//프레임 사이즈를 고정하고 띄움
		this.setSize(800, 90);
		
		//border 안 보이는 메소드 
		//setUndecorated(true);
		
		//이 창을 보이게 하는 메소드. 
		this.setVisible(true);
	}
	
	//카드레이아웃 폼을 생성하는 메소드
	public void CardLayoutFrm(MenuPnl menupnl)
	{
		//메뉴 패널에서 레이아웃 고르는 버튼을 제거. 
		menupnl.removeFristPnl();
		
		//버튼 형식의 메뉴가 들어갈 박스 생성
		Box menubox = Box.createHorizontalBox();

		//메뉴(패널)를 메뉴(박스)안에 넣어줌 
		menubox.add(menupnl);		
		
		//메인 박스에 메뉴 박스를 넣어줌
		mainbx.add(menubox);
		mainbx.add(Box.createVerticalStrut(20));
		//메인 박스에 메뉴 밑에 나올 카드패널을 넣어줌
		mainbx.add(cardPnl);
		//이 프레임에 메인 박스를 넣어줌
		getContentPane().add(mainbx);
		
		//사이즈 지정 및 화면의 중앙에 위치하게 만듬
		this.setSize(800, 620);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-800)/2, (screenSize.height-600)/2);
		this.setVisible(true);	
	}
	
	public void BoxLayoutFrm(MenuPnl menupnl)
	{
		//메뉴 패널에서 레이아웃 고르는 버튼을 제거.
		menupnl.removeFristPnl();
		
		//메인 화면을 박스로 구성합니다.
		mainbx = Box.createVerticalBox();
		
		//로그인, 마이페이지 같은 글씨가 써질 박스입니다. 
		Box menubox = Box.createHorizontalBox();
		
		//예약 패널과, 예약정보 패널이 위치할 상위 박스입니다. 
		Box upbox = Box.createHorizontalBox();
		
		//도착지 정보 패널과 여행기록패널을 보여주기 위한 하단 박스입니다. 
		Box downbox = Box.createHorizontalBox();		
		
		//실제 upbox 안에 들어갈 예약 패널입니다. (RsvPnl.Java)연경이 부분 
		RsvPnl rsP = new RsvPnl();
		//테두리를 보기 위해 추가한 것.
		rsP.setBorder(new TitledBorder(new EtchedBorder(), "예약선택(RsvPanl.Java)"));
		
		//실제 upbox 안에 들어갈 예약정보 패널입니다. (RsvInfoPnl.Java)운하형님 부분
		RsvInfoPnl rsInfoP = new RsvInfoPnl();
		//테두리를 보기 위해 추가한 것.
		rsInfoP.setBorder(new TitledBorder(new EtchedBorder(), "예약정보(RsvInfoPanl.Java)"));
		
		//실제 downbox 안에 들어갈 도착지정보 패널입니다. (BusThmPnl.Java)운하형님 부분
		BusThmPnl busThmP = new BusThmPnl();		
		//테두리를 보기 위해 추가한 것.
		busThmP.setBorder(new TitledBorder(new EtchedBorder(), "여정지정보(BusThmPnl.Java)"));
		
		//실제 downbox 안에 들어갈 도착지정보 패널입니다. (DiaryPnl.Java)
		DiaryPnl diaP = new DiaryPnl();
		//테두리를 보기 위해 추가한 것.
		diaP.setBorder(new TitledBorder(new EtchedBorder(), "여행기록(DiaryPnl.Java)"));
		
		//메인화면 박스 안에 menubox, upbox, downbox를 추가.
		//createGlue(), createVerticalGlue() 같은 의미
		mainbx.add(menubox);
		mainbx.add(Box.createVerticalGlue());
		mainbx.add(upbox);
		mainbx.add(Box.createVerticalGlue());
		mainbx.add(downbox);
				
		//각 박스들 안에 해당 패널을 넣어줌.
		menubox.add(menupnl);
		upbox.add(rsP);
		upbox.add(Box.createHorizontalGlue());
		upbox.add(rsInfoP);
		downbox.add(busThmP);		
		downbox.add(Box.createHorizontalGlue());
		downbox.add(diaP);
		
		//프레임 안에 메인 화면 박스를 넣음
		getContentPane().add(mainbx);
		
		//프레임 사이즈를 고정하고 화면 중앙에 띄움
		this.setSize(1260, 960);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-1260)/2, (screenSize.height-900)/2);
		this.setVisible(true);		
	}
}
