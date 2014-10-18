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
	JPanel p1 = new JPanel();
	JPanel p2 = new JPanel();
	JTabbedPane timeTabb;
	ButtonGroup bg;
	Vector<String> loc;
	Vector<String> seats=new Vector<String>() ;
	Vector<String> adultSeats=new Vector<String>() ;
	Vector<String> childSeats=new Vector<String>() ;
	Vector<String> handySeats=new Vector<String>() ;
	public JComboBox ComboDepartO, ComboArrivalO, ComboDepartB, ComboArrivalB,
				     ComboPackage, ComboAdult, ComboChild, ComboDisabled;
	public static final int ButtonGroup = 0;
	public JTable jto = new JTable();
	public JTable jtp = new JTable();	
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
	// 패키지
	String[] packageArea = {"[전주 한옥마을] 우리나라 전통이 살아 숨쉬는 곳",
			"[경주 보문관광단지] 경주의 사랑방으로 불리는 종합관광휴양지",
			"[부산 남포동 국제시장] 다양한 상품들이 공급되는 종합재래시장",
			"[포항 덕동문화마을] 300년 세월이 깃든 전통마을과 아름다운 숲",
			"[양양 낙산사] 화마를 이겨낸 해수관음의 성지",
			"[정선 그림바위마을] 세 가지 시선을 따라 걷는 마을길",
			"[합천 문화재여행] 국보 문화재와 함께하는 여행",
			"[안동 하회마을] 전통이 살아 숨쉬는 민속마을"
			};
	
	// 인원수 리스트
	String[] adult = {"0", "1", "2", "3", "4", "5"};	// 성인
	String[] child = {"0", "1", "2", "3", "4", "5"};	// 아동
	String[] handicap = {"0", "1", "2", "3", "4", "5"};	// 장애인
	
	
	public RsvPnl() 
	{
		//출발지 와 도착지 값 가져오기
		DbQuery dbq = new DbQuery();
		loc =dbq.getTmnName();
		thispnl = this;

		this.setPreferredSize(new Dimension(620,410));
		//this.setMaximumSize(new Dimension(620,410));
		//this.setMaximumSize(new Dimension(620,410));		
				
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
		ComboDepartO = new JComboBox( loc);	
		
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
		ComboPackage = new JComboBox(packageArea);
		ComboPackage.setPreferredSize(new Dimension(325,20));
		ComboPackage.setMaximumSize(new Dimension(325,20));
		ComboPackage.setMinimumSize(new Dimension(325,20));

		// 생성한 컨트롤들을 Box에 올려주고 수평으로 노출되도록 설정
		paBox.add(packages);
		paBox.add(Box.createHorizontalStrut(10));
		paBox.add(ComboPackage);

		//	버스 종류(일반/우등/야간)별 시간표 (timeBox : BoxLayout)
		Box timeBox = Box.createHorizontalBox();
		timeBox.setPreferredSize(new Dimension(600,190));
		timeBox.setMaximumSize(new Dimension(600,190));
		timeBox.setMinimumSize(new Dimension(600,190));

		// 탭을 생성하고 만들어진 탭은 위로 노출되도록 설정 
		timeTabb = new JTabbedPane();
		timeTabb.setTabPlacement(JTabbedPane.TOP);

		// 만들어진 각 탭안에 패널을 생성
		JPanel p1 = new JPanel();	// '일반'탭 안의 패널
		JPanel p2 = new JPanel();	// '우등'탭 안의 패널
		JPanel p3 = new JPanel();	// '야간'탭 안의 패널
		JPanel p4 = new JPanel();
		
		JLabel txt1 = new JLabel("JTable 일반 시간표");
		JLabel txt2 = new JLabel("JTable 우등 시간표");
		JLabel txt3 = new JLabel("JTable 야간 시간표");
	    
		txt1.setPreferredSize(new Dimension(140,90));
		txt2.setPreferredSize(new Dimension(140,90));
		txt3.setPreferredSize(new Dimension(140,90));
	  
		jtoScroll = new JScrollPane(jto,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jtoScroll.setPreferredSize(new Dimension(100,120));
		jtoScroll.setMaximumSize(new Dimension(100,120));
		jtoScroll.setMinimumSize(new Dimension(100,120));
	  
		JScrollPane jtpScroll = new JScrollPane(jtp,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jtpScroll.setPreferredSize(new Dimension(100,110));
		jtpScroll.setMaximumSize(new Dimension(100,120));
		jtpScroll.setMinimumSize(new Dimension(100,120));
	  	  
		jto.addMouseListener(new JtableSelectListener());
	  
		jto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	  	  
		jtp.addMouseListener(new JtableSelectListener());
	  
		jtp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
	  
	  
		p1.add(oSeatsPnl);
		p1.add(new JScrollPane(jtoScroll));  
		p2.add(pSeatsPnl);    
		p2.add(new JScrollPane(jtpScroll));

		//탭 이름을 추가함
		timeTabb.addTab("일반", p1);
		timeTabb.addTab("우등", p2);
		timeTabb.addChangeListener( new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(timeTabb.getSelectedIndex()==0)
				{
					jtp.clearSelection();
					p2.removeAll();
					p2.add(pSeatsPnl = new PbusSeatPnl(thispnl));
					p2.add(new JScrollPane(jtpScroll));
					
				}
				else if(timeTabb.getSelectedIndex()==1)
				{
					jto.clearSelection();
					p1.removeAll();
					p1.add(oSeatsPnl = new ObusSeatPnl(thispnl));
					p1.add(new JScrollPane(jtoScroll));
				}
				seats.removeAllElements();       	        	
			}
		});
        
		//만들어진 버스시간표 탭을 박스 안에 넣어줌
		timeBox.add(timeTabb);
		
		//인원수 (peBox : BoxLayout)
		Box peBox = Box.createHorizontalBox();
		
		// 성인 라벨 & 성인 인원수 콤보박스
		lblAdult = new JLabel("성인");
		ComboAdult = new JComboBox(adult);
		ComboAdult.addActionListener(new ComboItemListener());
		
		// 아동 라벨 & 아동 인원수 콤보박스
		lblChild = new JLabel("7세 미만");
		ComboChild = new JComboBox(child);
		ComboChild.addActionListener(new ComboItemListener());
		
		// 장애인 라벨 & 장애인 인원수 콤보박스
		lblDisabled = new JLabel("장애인");
		ComboDisabled = new JComboBox(handicap);
		ComboDisabled.addActionListener(new ComboItemListener());
		
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
	
	// 버튼에 대한 리스너
	private class ButtonActionListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			CalendarInfo sub = new CalendarInfo(RsvPnl.this, true);
			if (btn == btngoCal) 
			{				
				sub.setTitle("가는날 일정 선택");
				sub.setVisible(true);
			} 
			else if (btn == btnbackCal) 
			{				
				sub.setTitle("오는날 일정 선택");
				sub.setVisible(true);
			}
			else if (btn == btnRsvn) 
			{	
				// RsvnfoPnl로 데이터가 넘어가야 함				
				if(seats.size() == 0)
				{
					JOptionPane.showMessageDialog(null,"좌석을 선택해주세요.");
				}				
				else if(timeTabb.getSelectedIndex()==0)
				{
					if(jto.getSelectedRow() ==-1)
					{
						JOptionPane.showMessageDialog(null,"시간을 선택해주세요.");
						return;
					}
					else if(seats.size()==0)
					{
						JOptionPane.showMessageDialog(null,"좌석을 선택해주세요.");
						return;
					}
					BusColumn buscol = busoModel.getRowData(jto.getSelectedRow());
					String busid = buscol.getBUSTIMEID();	
					int busprice = buscol.getBUSPRICE();
					
					for(int i=0 ; adultSeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, seats.get(i),busprice, "ADULT" );
					}
					
					for(int i=0 ; childSeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, seats.get(i),busprice, "CHILD" );
					}
					
					for(int i=0 ; handySeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, seats.get(i),busprice, "CHILD" );
					}
				}									
				else if( timeTabb.getSelectedIndex()==1)
				{
					if(jtp.getSelectedRow() ==-1)
					{
						JOptionPane.showMessageDialog(null,"시간을 선택해주세요.");
						return;
					}
					else if(seats.size()==0)
					{
						JOptionPane.showMessageDialog(null,"좌석을 선택해주세요.");
						return;
					}
					BusColumn buscol = busoModel.getRowData(jtp.getSelectedRow());					
					String busid = buscol.getBUSTIMEID();	
					int busprice = buscol.getBUSPRICE();
					
					for(int i=0 ; adultSeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, seats.get(i),busprice, "ADULT" );
					}
					
					for(int i=0 ; childSeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, seats.get(i),busprice, "CHILD" );
					}
					
					for(int i=0 ; handySeats.size()>i;i++)
					{
						DbQuery db = new DbQuery();
						db.istRsv(userid, busid, seats.get(i),busprice, "CHILD" );
					}				
				}
							
				rsvInfoPnl.rsvList =rsvInfoPnl.dao.getRsvList(userid);
				System.out.println("asdf"+userid);	
				rsvInfoPnl.rsvTblModel = new RsvTableModel(rsvInfoPnl.rsvList);
				rsvInfoPnl.rsvTable.setModel(rsvInfoPnl.rsvTblModel);	
				pSeatsPnl.setSeat(new Vector<String>());
				oSeatsPnl.setSeat(new Vector<String>());
				jto.clearSelection();
				jtp.clearSelection();
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
				
			if(tbl ==jto)
			{				
				//if(e.getButton() == 1 && e.getClickCount() == 2) 
				//{
					pSeatsPnl.setSeat(new Vector<String>());
					System.out.println("일반");
					int rowIndex = jto.getSelectedRow();					
					BusColumn buscol = busoModel.getRowData(rowIndex);
					String busid = buscol.getBUSTIMEID();
					DbQuery db = new DbQuery();
					Vector<String> Seats = db.GetUsedSeat(busid);
					System.out.println(Seats);
					oSeatsPnl.setSeat(Seats);				
					JOptionPane.showMessageDialog(null,rowIndex+"-"+buscol.getBUSTIMEID());				
				//}				
			}
			else if(tbl ==jtp)
			{
				//if(e.getButton() == 1 && e.getClickCount() == 2) 
				//{
					pSeatsPnl.setSeat(new Vector<String>());
					System.out.println("우등");
					int rowIndex = jtp.getSelectedRow();					
					BusColumn buscol = buspModel.getRowData(rowIndex);
					String busid = buscol.getBUSTIMEID();
					DbQuery db = new DbQuery();
					Vector<String> Seats = db.GetUsedSeat(busid);
					System.out.println(Seats);
					pSeatsPnl.setSeat(Seats);
					JOptionPane.showMessageDialog(null,rowIndex+"-"+buscol.getBUSTIMEID());				
				//}				
			}
		}
	}
	
	// 콤보박스에 대한 아이템 리스너
	
	private class ComboItemListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)  
		{
			JComboBox com = (JComboBox)e.getSource();
			
			int adult = Integer.parseInt(ComboAdult.getSelectedItem().toString());
			int child = Integer.parseInt(ComboChild.getSelectedItem().toString());
			int handy = Integer.parseInt(ComboDisabled.getSelectedItem().toString());
			
			if(adult+child+handy >5)
			{
				JOptionPane.showMessageDialog(null,"예약은 5명까지 밖에 안됨요. ");
				
				if(com == ComboAdult)									
					ComboAdult.setSelectedItem(String.valueOf(cmbAdult));			
				else if(com == ComboChild)				
					ComboChild.setSelectedItem(String.valueOf(cmbChild));									
				else if(com == ComboDisabled)
					ComboDisabled.setSelectedItem(String.valueOf(cmbHandy));
			}
			else
			{
				cmbAdult = Integer.parseInt(ComboAdult.getSelectedItem().toString());
				cmbChild = Integer.parseInt(ComboChild.getSelectedItem().toString());
				cmbHandy = Integer.parseInt(ComboDisabled.getSelectedItem().toString());
			}
			
		}
		

	}	
	
	}