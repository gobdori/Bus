package bus.ui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bus.cal.CalendarInfo;
import bus.db.BusColumn;
import bus.db.BusTimesTableModel;
import bus.db.DbQuery;
import bus.db.RsvTableModel;

public class RsvPnl extends JPanel {
	
	
	JRadioButton oneway, bothways, packages;
	JPanel mainPanel, radioPanel, areaPanel, onPanel, boPanel, paPanel, timePanel;
	JLabel lbldepart, lblarrival, lblAdult, lblChild, lblDisabled;			
	JScrollPane jtoScroll = new JScrollPane();
	JScrollPane jtpScroll = new JScrollPane();
	JButton btngoCal, btnbackCal, btnRsvn;
	JTabbedPane timeTabb;
	ButtonGroup bg;
	Vector<String> loc;
	Vector<String> pkg;
	Vector<String> seats=new Vector<String>() ;
	Vector<String> adultSeats=new Vector<String>() ;
	Vector<String> childSeats=new Vector<String>() ;
	Vector<String> handySeats=new Vector<String>() ;
	public JComboBox ComboDepartO, ComboArrivalO, ComboDepartB, ComboArrivalB,
				     ComboPackage, ComboAdult, ComboChild, ComboDisabled;
	public static final int ButtonGroup = 0;
	public JTable tblObusTime = new JTable();
	public JTable tblPbusTime = new JTable();	
	public Vector<BusColumn> vCBus;
	public BusTimesTableModel busoModel = new BusTimesTableModel(); 
	public BusTimesTableModel buspModel = new BusTimesTableModel();
	RsvPnl thispnl;
	String username = "";
	String userid =DbQuery.getUserid();
	int cmbAdult = 0;
	int cmbChild = 0;
	int cmbHandy = 0;
	
	ObusSeatPnl oSeatsPnl = new ObusSeatPnl(this);
	PbusSeatPnl pSeatsPnl = new PbusSeatPnl(this);
	RsvInfoPnl rsvInfoPnl = new RsvInfoPnl();
	
	// 인원수 리스트
	String[] adult = {"0", "1", "2", "3", "4", "5"};	// 성인
	String[] child = {"0", "1", "2", "3", "4", "5"};	// 아동
	String[] handicap = {"0", "1", "2", "3", "4", "5"};	// 장애인
	
	
	public RsvPnl() 
	{
		//출발지 와 도착지 값 가져오기
		DbQuery dbq = new DbQuery();
		loc =dbq.getTmnName();
		//패키지값 가져오기
		pkg =dbq.getPkgName();
		//자기 자신을 받는 값을 선언
		thispnl = this;

		//크기 정의
		this.setPreferredSize(new Dimension(620,410));
		this.setMaximumSize(new Dimension(620,410));
		this.setMaximumSize(new Dimension(620,410));		
				
		//	구입방법 라디오버튼 (편도/왕복/패키지) 그룹 생성
		// 라디오버튼 그룹 생성
		bg = new ButtonGroup();
		
		// 라디오버튼 생성
		oneway = new JRadioButton("편도");
		bothways = new JRadioButton("왕복");
		packages = new JRadioButton("패키지");
		
		// '편도'를 Default로 설정
		oneway.setSelected(true);
		
		// 라디오버튼 그룹에 생성한 라디오버튼(편도/왕복/패키지) 추가
		bg.add(oneway);
		bg.add(bothways);
		bg.add(packages);
		
		
		//각 라디오 버튼(편도/왕복/패키지)에 따른 박스 레이아웃 구현
		//편도 (onBox : BoxLayout)		
		Box onBox = Box.createHorizontalBox();
		onBox.setPreferredSize(new Dimension(400,20));		
		onBox.setMaximumSize(new Dimension(400,20));
		onBox.setMinimumSize(new Dimension(400,20));
		// 수평으로 컴포넌트를 배치하는 레이아웃 생성
		
		// 출발 라벨 & 출발도시 콤보박스
		lbldepart = new JLabel("출발");
		ComboDepartO = new JComboBox(loc);	
		
		// 도착 라벨 & 도착도시 콤보박스
		lblarrival = new JLabel("도착");
		ComboArrivalO = new JComboBox(loc);
		
		// 가는날 일정 선택 버튼 > 클릭 시, 달력 창 노출됨 (CalendarGoInfo)
		btngoCal = new JButton("가는날 일정 선택");

		// 생성한 컨트롤들을 Box에 올려주고 수평으로 노출되도록 설정
		onBox.add(oneway);
		onBox.add(Box.createHorizontalStrut(6));
		onBox.add(lbldepart);
		onBox.add(Box.createHorizontalStrut(2));
		onBox.add(ComboDepartO);
		onBox.add(Box.createHorizontalStrut(4));
		onBox.add(lblarrival);
		onBox.add(Box.createHorizontalStrut(2));
		onBox.add(ComboArrivalO);
		onBox.add(Box.createHorizontalStrut(6));
		onBox.add(btngoCal);

		//왕복 (boBox : BoxLayout)
		Box boBox = Box.createHorizontalBox();
		boBox.setPreferredSize(new Dimension(400,20));
		boBox.setMaximumSize(new Dimension(400,20));
		boBox.setMinimumSize(new Dimension(400,20));

		// 출발 라벨 & 출발도시 콤보박스
		lbldepart = new JLabel("출발");
		ComboDepartB = new JComboBox(loc);
		
		// 도착 라벨 & 도착도시 콤보박스
		lblarrival = new JLabel("도착");
		ComboArrivalB = new JComboBox(loc);
		
		// 오는날 일정 선택 버튼 > 클릭 시, 달력 창 노출됨 (CalendarBackInfo)
		btnbackCal = new JButton("오는날 일정 선택");
		
		// 생성한 컨트롤들을 Box에 올려주고 수평으로 노출되도록 설정
		boBox.add(bothways);
		boBox.add(Box.createHorizontalStrut(6));
		boBox.add(lbldepart);
		boBox.add(Box.createHorizontalStrut(2));
		boBox.add(ComboDepartB);
		boBox.add(Box.createHorizontalStrut(4));
		boBox.add(lblarrival);
		boBox.add(Box.createHorizontalStrut(2));
		boBox.add(ComboArrivalB);
		boBox.add(Box.createHorizontalStrut(6));
		boBox.add(btnbackCal);
		
		//	패키지 (paBox : BoxLayout)
		Box paBox = Box.createHorizontalBox();
		paBox.setPreferredSize(new Dimension(400,20));
		paBox.setMaximumSize(new Dimension(400,20));
		paBox.setMinimumSize(new Dimension(400,20));
		
		// 패키지 콤보박스 생성
		ComboPackage = new JComboBox(pkg);
		ComboPackage.setPreferredSize(new Dimension(325,20));
		ComboPackage.setMaximumSize(new Dimension(325,20));
		ComboPackage.setMinimumSize(new Dimension(325,20));

		// 생성한 컨트롤들을 Box에 올려주고 수평으로 노출되도록 설정
		paBox.add(packages);
		paBox.add(Box.createHorizontalStrut(10));
		paBox.add(ComboPackage);

		//	버스 종류(일반/우등/야간)별 시간표 (timeBox : BoxLayout)
		Box timeBox = Box.createHorizontalBox();
		timeBox.setPreferredSize(new Dimension(580,190));
		timeBox.setMaximumSize(new Dimension(580,190));
		timeBox.setMinimumSize(new Dimension(580,190));

		// 탭을 생성하고 만들어진 탭은 위로 노출되도록 설정 
		timeTabb = new JTabbedPane();
		timeTabb.setTabPlacement(JTabbedPane.TOP);

		// 만들어진 각 탭안에 패널을 생성
		JPanel pnlOBusMain = new JPanel();	// '일반'탭 안의 패널
		JPanel pnlPBusMain = new JPanel();	// '우등'탭 안의 패널

		//버스시간표(일반)를 스크롤 형식으로 넣음
		jtoScroll = new JScrollPane(tblObusTime,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jtoScroll.setPreferredSize(new Dimension(100,120));
		jtoScroll.setMaximumSize(new Dimension(100,120));
		jtoScroll.setMinimumSize(new Dimension(100,120));
	  
		//버스시간표(우등)를 스크롤 형식으로 넣음
		JScrollPane jtpScroll = new JScrollPane(tblPbusTime,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jtpScroll.setPreferredSize(new Dimension(100,110));
		jtpScroll.setMaximumSize(new Dimension(100,120));
		jtpScroll.setMinimumSize(new Dimension(100,120));
	  	
		//버스시간표(일반, 우등)에 마우스리스너 연결(선택시 이벤트 발생하게)
		tblObusTime.addMouseListener(new JtableSelectListener());	  
		tblObusTime.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	  	  
		tblPbusTime.addMouseListener(new JtableSelectListener());	  
		tblPbusTime.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
	  
		//버스패널에 좌석과 시간표를 넣음
		pnlOBusMain.add(oSeatsPnl);
		pnlOBusMain.add(new JScrollPane(jtoScroll));  
		pnlPBusMain.add(pSeatsPnl);    
		pnlPBusMain.add(new JScrollPane(jtpScroll));

		//탭 이름과 해당 패널을 추가함
		timeTabb.addTab("일반", pnlOBusMain);
		timeTabb.addTab("우등", pnlPBusMain);
		//탭 클릭시 일어날 리스너 생성
		timeTabb.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//일반버스탭 클릭시
				if(timeTabb.getSelectedIndex()==0)
				{
					//좌석정보가 들어간 배열 초기화
					seats.removeAllElements();
					//어른좌석정보가 들어간 배열 초기화
					adultSeats.removeAllElements();
					//아이좌석정보가 들어간 배열 초기화
					childSeats.removeAllElements();
					//장애인좌석정보가 들어간 배열 초기화
					handySeats.removeAllElements();
					//우등버스시간표 초기화
					tblPbusTime.clearSelection();
					//우등버스좌석 초기화
					pSeatsPnl.setSeat(seats);
				}
				//우등버스탭 클릭시
				else if(timeTabb.getSelectedIndex()==1)
				{
					//좌석정보가 들어간 배열 초기화
					seats.removeAllElements();
					//어른좌석정보가 들어간 배열 초기화
					adultSeats.removeAllElements();
					//아이좌석정보가 들어간 배열 초기화
					childSeats.removeAllElements();
					//장애인좌석정보가 들어간 배열 초기화
					handySeats.removeAllElements();
					//일반버스시간표 초기화
					tblObusTime.clearSelection();		
					//일반버스좌석 초기화
					oSeatsPnl.setSeat(seats);					
				}				
			}
		});
        
		//만들어진 버스시간표 탭을 박스 안에 넣어줌
		timeBox.add(timeTabb);
		
		//인원수 (peBox : BoxLayout)
		Box peBox = Box.createHorizontalBox();
		peBox.setPreferredSize(new Dimension(550, 20));
		peBox.setMinimumSize(new Dimension(550, 20));
		peBox.setMaximumSize(new Dimension(550, 20));
		
		
		// 성인 라벨 & 성인 인원수 콤보박스
		lblAdult = new JLabel("성인");
		ComboAdult = new JComboBox(adult);
		ComboAdult.addActionListener(new ComboItemListener());
		ComboAdult.setEnabled(false);
		ComboAdult.setEditable(false);
		
		// 아동 라벨 & 아동 인원수 콤보박스
		lblChild = new JLabel("7세 미만");
		ComboChild = new JComboBox(child);
		ComboChild.addActionListener(new ComboItemListener());
		ComboChild.setEnabled(false);
		ComboChild.setEditable(false);
		
		// 장애인 라벨 & 장애인 인원수 콤보박스
		lblDisabled = new JLabel("장애인");
		ComboDisabled = new JComboBox(handicap);
		ComboDisabled.addActionListener(new ComboItemListener());
		ComboDisabled.setEnabled(false);
		ComboDisabled.setEditable(false);
		
		//예약하기 버튼 클릭 시, 우측 패널 (RsvInfoPnl.java)로 데이터가 넘어가야 함
		btnRsvn = new JButton("예약하기");
		
		// 생성한 컨트롤들을 Box에 올려주고 수평으로 노출되도록 설정
		peBox.add(lblAdult);
		peBox.add(Box.createHorizontalStrut(10));
		peBox.add(ComboAdult);
		peBox.add(Box.createHorizontalStrut(20));
		peBox.add(lblChild);
		peBox.add(Box.createHorizontalStrut(10));
		peBox.add(ComboChild);
		peBox.add(Box.createHorizontalStrut(20));
		peBox.add(lblDisabled);
		peBox.add(Box.createHorizontalStrut(10));
		peBox.add(ComboDisabled);
		peBox.add(Box.createHorizontalStrut(10));		
		peBox.add(btnRsvn);		
		
		// 생성한 컨트롤들을 Box에 올려주고 수평으로 노출되도록 설정		
		
		/* 구입방법 패널들(onBox(편도), boBox(왕복), paBox(패키지), timeBox(버스 시간표),
		 * 	peBox(인원 수), viewBox(금액확인, 예약버튼)을 수직으로 배치할 메인박스 생성 
		 */
		Box mainBox = Box.createVerticalBox();
		
		// 메인 박스에 구현된 박스들을 세로로 배치
		mainBox.add(onBox);		// 편도
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(boBox);		// 왕복
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(paBox);		// 패키지
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(timeBox);	// 버스 시간표
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(peBox);		// 인원&예약버튼

		// 프레임에 메인박스를 노출
		add(mainBox);
		setSize(585, 375);
		setVisible(true);
		
		
		// 각 컨트롤에 넣을 액션들 구현		
		// 버튼
		ActionListener listener = new ButtonActionListener();
		// 라디오버튼
		ItemListener ra = new RadioItemListener();
		// 콤보박스
							
						
		// 각 버튼에 ActionListener를 연결
		// 가는날 일정 선택 / 오는날 일정 버튼
		btngoCal.addActionListener(listener);
		btnbackCal.addActionListener(listener);
		btnRsvn.addActionListener(listener);
						
		// 편도, 왕복, 패키지 라디오버튼
		oneway.addItemListener(ra);
		bothways.addItemListener(ra);
		packages.addItemListener(ra);		
		
		}
	
	//좌석정보랑 시간을 초기화하는 메소드
	public void removeSeats()
	{
		seats.removeAllElements();
		adultSeats.removeAllElements();
		childSeats.removeAllElements();
		handySeats.removeAllElements();
		
		tblPbusTime.clearSelection();					
		pSeatsPnl.setSeat(seats);
		
		tblObusTime.clearSelection();
		oSeatsPnl.setSeat(seats);		
	}
	
	// 버튼에 대한 리스너
	private class ButtonActionListener implements ActionListener 
	{
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//눌러진 버튼에 대한 정보를 btn에 담는다			
			JButton btn = (JButton) e.getSource();
			
			//달력 객체를 생성해 놓는다. 
			CalendarInfo sub = new CalendarInfo(thispnl, true);
			
			//가늘날 버튼을 눌렀을 경우
			if (btn == btngoCal) 
			{
				//달력 객체 타이틀을 변경후 보여줌. 
				sub.setTitle("가는날 일정 선택");
				sub.setVisible(true);
			} 
			//오는날 버튼을 눌렀을 경우
			else if (btn == btnbackCal) 
			{	//달력 객체 타이틀을 변경후 보여줌. 			
				sub.setTitle("오는날 일정 선택");
				sub.setVisible(true);
			}
			//예약하기 버튼을 눌렀을 경우. 
			else if (btn == btnRsvn) 
			{	
				//선택된 좌석이 0개 인 경우				
				if(seats.size() == 0)
				{	//좌석을 선택하라는 알림창이 뜸
					JOptionPane.showMessageDialog(null,"좌석을 선택해주세요.");
				}				
				//일반 버스 
				else if(timeTabb.getSelectedIndex()==0)
				{	//시간표가 선택되지 않았을 경우
					if(tblObusTime.getSelectedRow() ==-1)
					{
						JOptionPane.showMessageDialog(null,"시간을 선택해주세요.");
						return;
					}
					//좌석이 선택되지 않았을 경우
					else if(seats.size()==0)
					{
						JOptionPane.showMessageDialog(null,"좌석을 선택해주세요.");
						return;
					}
					
					//좌석이랑 시간이 선택 된 경우//
					//일반버스시간표(테이블)에서 선택된 행(index)값을 얻은 후
					//테이블에 적용된 테이블모델의 값(data)을 얻어서					
					BusColumn buscol = busoModel.getRowData(tblObusTime.getSelectedRow());
					//그 값들 중 버스ID랑, 가격을 알아 온다. 
					String busid = buscol.getBUSTIMEID();//버스ID
					int busprice = buscol.getBUSPRICE();//일반가격
					int chprice = (int)(busprice*0.7);//소인가격
					int hdprice = (int)(busprice*0.5);//장애인가격
					
					//성인좌석을 예약
					for(int i=0 ; adultSeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, adultSeats.get(i),busprice, "ADULT" );
					}
					//소인좌석을 예약
					for(int i=0 ; childSeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, childSeats.get(i),chprice, "CHILD" );						
					}
					//장애인좌석을 예약
					for(int i=0 ; handySeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, handySeats.get(i),hdprice, "HANDY" );						
					}
				}
				//우등버스가 선택된 경우-일반인 경우의 주석을 참고. 
				else if( timeTabb.getSelectedIndex()==1)
				{
					if(tblPbusTime.getSelectedRow() ==-1)
					{
						JOptionPane.showMessageDialog(null,"시간을 선택해주세요.");
						return;
					}
					else if(seats.size()==0)
					{
						JOptionPane.showMessageDialog(null,"좌석을 선택해주세요.");
						return;
					}
					BusColumn buscol = busoModel.getRowData(tblPbusTime.getSelectedRow());					
					String busid = buscol.getBUSTIMEID();	
					int busprice = buscol.getBUSPRICE();
					int chprice = (int)(busprice*0.7);
					int hdprice = (int)(busprice*0.5);
					
					for(int i=0 ; adultSeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, adultSeats.get(i),busprice, "ADULT" );
					}
					
					for(int i=0 ; childSeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, childSeats.get(i),chprice, "CHILD" );						
					}
					
					for(int i=0 ; handySeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, handySeats.get(i),hdprice, "HANDY" );						
					}	
				}
							
				//예약정보 패널에 업데이트를 하기위한 구문
				//예약리스트에 넣을 정보를 받아온다
				rsvInfoPnl.rsvList =rsvInfoPnl.dao.getRsvList(userid);
				//받아온 예약리스트를 테이블모델에 적용
				rsvInfoPnl.rsvTblModel = new RsvTableModel(rsvInfoPnl.rsvList);
				//그 모델을 테이블에 적용
				rsvInfoPnl.rsvTable.setModel(rsvInfoPnl.rsvTblModel);
				//좌석과 시간표 초기화				
				removeSeats();				
			}		
		}
	}
	
	
	// 라디오버튼에 대한 아이템 리스너
	private class RadioItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e){

			
		}
	}
	
	private class JtableSelectListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			JTable tbl = (JTable) e.getSource();
			
			//일반버스시간표 클릭시
			if(tbl ==tblObusTime)
			{
				//좌석초기화
				oSeatsPnl.setSeat(new Vector<String>());
				
				//시간표테이블에서 선택된 행(index)의 값을 얻음 
				int rowIndex = tblObusTime.getSelectedRow();	
				//테이블 모델에서 index의 데이터를 얻음
				BusColumn buscol = busoModel.getRowData(rowIndex);
				//그 데이터중에서 버스ID를 얻음
				String busid = buscol.getBUSTIMEID();
				//그 버스ID에 예약된 좌석을 얻음
				DbQuery db = new DbQuery();
				Vector<String> Seats = db.GetUsedSeat(busid);			
				System.out.println(Seats);
				//예약된 좌석들을 일반버스좌석에 표시해줌
				oSeatsPnl.setSeat(Seats);
				//지울 것!!버스아이디를 알고 싶어서 넣음
				JOptionPane.showMessageDialog(null,rowIndex+"-"+buscol.getBUSTIMEID());			
			}
			//우등버스시간표 클릭시 - 일반 쪽 참고
			else if(tbl ==tblPbusTime)
			{
				pSeatsPnl.setSeat(new Vector<String>());
				System.out.println("우등");
				int rowIndex = tblPbusTime.getSelectedRow();					
				BusColumn buscol = buspModel.getRowData(rowIndex);
				String busid = buscol.getBUSTIMEID();
				DbQuery db = new DbQuery();
				Vector<String> Seats = db.GetUsedSeat(busid);
				System.out.println(Seats);
				pSeatsPnl.setSeat(Seats);
				JOptionPane.showMessageDialog(null,rowIndex+"-"+buscol.getBUSTIMEID());							
			}
		}
	}
	
	// 콤보박스에 대한 아이템 리스너(콤보박스 비활성화로 쓰지 않음)	
	private class ComboItemListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)  
		{
			JComboBox com = (JComboBox)e.getSource();
			
			//콤보박스에서 성인, 소인, 장애인의 선택된 수를 얻음
			int adult = Integer.parseInt(ComboAdult.getSelectedItem().toString());
			int child = Integer.parseInt(ComboChild.getSelectedItem().toString());
			int handy = Integer.parseInt(ComboDisabled.getSelectedItem().toString());
			
			if(adult+child+handy >5)//총 인원이 5명이 넘었을 경우
			{
				JOptionPane.showMessageDialog(null,"예약은 5명까지 밖에 안됨요. ");
				
				//콤보박스의 값들을 그 전값으로 되돌림
				if(com == ComboAdult)									
					ComboAdult.setSelectedItem(String.valueOf(cmbAdult));			
				else if(com == ComboChild)				
					ComboChild.setSelectedItem(String.valueOf(cmbChild));									
				else if(com == ComboDisabled)
					ComboDisabled.setSelectedItem(String.valueOf(cmbHandy));
			}
			else
			{
				//선택 성공된 콤보박스 값을 저장해 
				//실패 시 사용할 선택하기 전값으로 활용 
				cmbAdult = Integer.parseInt(ComboAdult.getSelectedItem().toString());
				cmbChild = Integer.parseInt(ComboChild.getSelectedItem().toString());
				cmbHandy = Integer.parseInt(ComboDisabled.getSelectedItem().toString());
			}
			
		}
	}	
	
}