package bus.ui;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import bus.db.BusDbConnect;

public class BusMainFrm extends JFrame{
	
	//메인프레임 생성자
	BusMainFrm()
	{	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//메인 화면을 박스로 구성합니다. 
		Box mainbx = Box.createVerticalBox();
		
		//로그인, 마이페이지 같은 글씨가 써질 박스입니다. 
		Box menubox = Box.createHorizontalBox();
		//나중에 지울 코드입니다. 테두리를 보기 위해 추가한 것. 
		menubox.setBorder(new LineBorder(new Color(49, 194, 16)));
		
		//예약 패널과, 예약정보 패널이 위치할 상위 박스입니다. 
		Box upbox = Box.createHorizontalBox();
		//나중에 지울 코드입니다. 테두리를 보기 위해 추가한 것.
		upbox.setBorder(new LineBorder(new Color(49, 194, 16)));
		
		//도착지 정보 패널과 여행기록패널을 보여주기 위한 하단 박스입니다. 
		Box downbox = Box.createHorizontalBox();
		//나중에 지울 코드입니다. 테두리를 보기 위해 추가한 것.
		downbox.setBorder(new LineBorder(new Color(49, 194, 16)));
		
		//실제 menubox 안에 들어가게 될 패널입니다. (MenuPnl.Java)
		MenuPnl mnP = new MenuPnl();
		
		//실제 upbox 안에 들어갈 예약 패널입니다. (RsvPnl.Java)연경이 부분 
		RsvPnl rsP = new RsvPnl();
		//나중에 지울 코드입니다. 테두리를 보기 위해 추가한 것.
		rsP.setBorder(new TitledBorder(new EtchedBorder(), "예약선택(RsvPanl.Java)"));
		
		//실제 upbox 안에 들어갈 예약정보 패널입니다. (RsvInfoPnl.Java)운하형님 부분
		RsvInfoPnl rsInfoP = new RsvInfoPnl();
		//나중에 지울 코드입니다. 테두리를 보기 위해 추가한 것.
		rsInfoP.setBorder(new TitledBorder(new EtchedBorder(), "예약정보(RsvInfoPanl.Java)"));
		
		//실제 downbox 안에 들어갈 도착지정보 패널입니다. (BusThmPnl.Java)운하형님 부분
		BusThmPnl busThmP = new BusThmPnl();
		//나중에 지울 코드입니다. 테두리를 보기 위해 추가한 것.
		busThmP.setBorder(new TitledBorder(new EtchedBorder(), "여정지정보(BusThmPnl.Java)"));
		
		//실제 downbox 안에 들어갈 도착지정보 패널입니다. (DiaryPnl.Java)
		DiaryPnl diaP = new DiaryPnl();
		//나중에 지울 코드입니다. 테두리를 보기 위해 추가한 것.
		diaP.setBorder(new TitledBorder(new EtchedBorder(), "여행기록(DiaryPnl.Java)"));
		
		//메인화면 박스 안에 menubox, upbox, downbox를 추가.
		//createGlue(), createVerticalGlue() 같은 의미
		mainbx.add(menubox);
		mainbx.add(Box.createVerticalGlue());
		mainbx.add(upbox);
		mainbx.add(Box.createVerticalGlue());
		mainbx.add(downbox);
				
		//각 박스들 안에 해당 패널을 넣어줌.
		menubox.add(mnP);
		upbox.add(rsP);
		upbox.add(Box.createHorizontalGlue());
		upbox.add(rsInfoP);
		downbox.add(busThmP);
		downbox.add(Box.createHorizontalGlue());
		downbox.add(diaP);
		
		//프레임 안에 메인 화면 박스를 넣음
		add(mainbx);
		
		//프레임 사이즈를 고정하고 띄움
		this.setSize(1240, 1024);
		this.setVisible(true);
		
		
	}
}
