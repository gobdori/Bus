package bus.cal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

import bus.db.BusColumn;
import bus.db.BusTimesTableModel;
import bus.db.DbQuery;
import bus.ui.RsvPnl;

public class CalendarInfo extends JDialog {
	
	protected static Connection conn;
	protected PreparedStatement pstmt = null;
	protected ResultSet result;
	
	RsvPnl rsvpn;
	
	JPanel pDate = new JPanel();
	JPanel pWeek = new JPanel();
	JPanel pUp = new JPanel();
	
	JButton btnPrevMon = new JButton("◀");
	JButton btnNextMon = new JButton("▶");
	JLabel lblYearMon = new JLabel();
	
	JButton[] btnArr = new JButton[42];
	String[] Week = {"일","월","화","수","목","금","토"};
	String des ="";
	String arr ="";
	int year;
	int month;
	
	Calendar curMon = Calendar.getInstance();
	
	
	public CalendarInfo(RsvPnl mainFrame, boolean modal) 
	{		
		this.setModal(modal);
		rsvpn = mainFrame;
		
		des = rsvpn.ComboDepartO.getSelectedItem().toString();
		System.out.println("출발지"+des);
		arr = rsvpn.ComboArrivalO.getSelectedItem().toString();
		System.out.println("도착지"+arr);
				
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		pUp.setBackground(Color.gray);
		pUp.setLayout(new FlowLayout(FlowLayout.CENTER));
		pUp.add(btnPrevMon);
		pUp.add(lblYearMon);
		pUp.add(btnNextMon);
		
		
		pWeek.setLayout(new GridLayout(1, 7));
		for (int i = 0; i < Week.length; i++) {
			pWeek.add(lbladdWeek(Week[i], Color.LIGHT_GRAY));
		}

		pDate.setLayout(new GridLayout(6, 7));
		for (int i = 0; i < btnArr.length; i++) {
			btnArr[i] = new JButton("");
			btnArr[i].addActionListener(new BtnArrEventHandler());
			pDate.add(btnArr[i]);
		}
		
		btnPrevMon.addActionListener(new BtnEventHandler());
		btnNextMon.addActionListener(new BtnEventHandler());
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing (WindowEvent we) {
				we.getWindow().setVisible(false);
				we.getWindow().dispose();
				//System.exit(0);
			}
		});
		
		add(pUp, "North");
		add(pWeek, "Center");
		add(pDate, "South");
		
		setBounds(200,200,500,300);
		setDays(curMon);
		setDialogLocation();		
	}
	
	public JLabel lbladdWeek(String text, Color color) {
		  JLabel label = new JLabel();
		  label.setText(text);
		  label.setHorizontalAlignment(SwingConstants.CENTER);
		  label.setBorder(new EtchedBorder());
		  if (color != null) {
		   label.setOpaque(true);
		   label.setBackground(color);
		  }
		  return label;
		 }
	
	@SuppressWarnings("deprecation")
	void setDays(Calendar date) {
		year = date.get(Calendar.YEAR);
		month = date.get(Calendar.MONTH);
		
		lblYearMon.setText(year + "년 " + (month+1) + "월");
		
		Calendar sDay = Calendar.getInstance();
		
		sDay.set(year, month, 1);
		sDay.add(Calendar.DATE, -sDay.get(Calendar.DAY_OF_WEEK) + 1);
		
		for(int i=0; i < btnArr.length; i++, sDay.add(Calendar.DATE, 1)) {
			int day = sDay.get(Calendar.DATE);
			btnArr[i].setLabel(day+"");

		if(sDay.get(Calendar.MONTH)!=month) {
			btnArr[i].setBackground(Color.lightGray);
		} else {
			btnArr[i].setBackground(Color.white);
			}
		}
	}
	
	// 부모 창의 중앙에 화면을 띄우는 메소드
	public void setDialogLocation() {
		
		// 메인 프레임의 위치를 이용해 프레임을 띄우 x, y 위치를 구한다.
		int x = rsvpn.getX() 
				+ rsvpn.getWidth() / 2 - this.getWidth() / 2;
		int y = rsvpn.getY() 
				+ rsvpn.getHeight() / 2 - this.getHeight() / 2;		
		
		// 메인 프레임의 중앙에 가는 일정 선택 프레임을 띄운다.		
		setLocation(x, y);		
	}
	
	class BtnEventHandler implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			
			
			JButton src = (JButton)ae.getSource();			
			
			if(src == btnPrevMon) 
			{
				curMon.add(Calendar.MONTH, -1);
			} 
			else if(src == btnNextMon) 
			{
				curMon.add(Calendar.MONTH, 1);
			}		
			
			setDays(curMon);
			repaint();
		}
	}
	
	
	class BtnArrEventHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent ae) 
		{			
			JButton src = (JButton)ae.getSource();
			System.out.println(src.getText().toString());
			String date = year+"-"+(month+1)+"-"+src.getText().toString();
			
			System.out.println("db"+date);
			DbQuery qry = new DbQuery();
			String desid = qry.getTmnId(des);
			System.out.println("출발지id" +desid);
			String arrid = qry.getTmnId(arr);
			System.out.println("도착지id" +arrid);
			//qry.DbBusInfo(date, des, arr);
			rsvpn.removeSeats();
			rsvpn.vCBus =qry.getBusList(desid, arrid, date);
			
			BusTimesTableModel busoModel;	  
			BusTimesTableModel buspModel;
			
			busoModel = new BusTimesTableModel(qry.getObustime());	   
			buspModel = new BusTimesTableModel(qry.getPbustime());
			rsvpn.busoModel = busoModel;
			rsvpn.tblObusTime.setModel(busoModel);
			rsvpn.buspModel = buspModel;
			rsvpn.tblPbusTime.setModel(buspModel);
			
			rsvpn.tblObusTime.revalidate();
			rsvpn.tblPbusTime.revalidate();			
			setVisible(false);
		}
	}
}