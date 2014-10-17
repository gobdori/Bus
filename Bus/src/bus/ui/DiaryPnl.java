package bus.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DiaryPnl extends JPanel{
	
	RsvPnl mainFrame;
	
	JPanel Diary = new JPanel();
	
	//JLabel을 JPanel로 넣어준다.  
	//JPanel Year = new JPanel();
	
	JPanel pDate = new JPanel();
	JPanel pWeek = new JPanel();
	JPanel pUp = new JPanel();
	JLabel lblYearMon = new JLabel();
	
	Box box = Box.createVerticalBox();
	
	JButton btnPrevMon = new JButton("◀");
	JButton btnNextMon = new JButton("▶");
	//저장 버튼
	JButton btnDiarySave = new JButton("저장");
	JButton[] btnArr = new JButton[42];
	JButton[] btnWeek = new JButton[7];
	
	Calendar curMon = Calendar.getInstance();
	
	//public DiaryPnl (RsvnStep01 mainFrame, boolean modal) 
	public DiaryPnl (){
		
		box.setPreferredSize(new Dimension(580,380));
		
		this.mainFrame = mainFrame;
		
		pUp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pUp.setBackground(Color.red);
		
		
		pUp.add(btnPrevMon);
		pUp.add(lblYearMon);
		pUp.add(btnNextMon);
		pUp.add(btnDiarySave);
		//저장 버튼
		//pUp.add(btnDiarySave);
		
		
		box.add(pUp);
		box.add(Box.createVerticalGlue());
		
		
		pWeek.setLayout(new GridLayout(1, 7));
		btnWeek[0] = new JButton("일");
		btnWeek[1] = new JButton("월");
		btnWeek[2] = new JButton("화");
		btnWeek[3] = new JButton("수");
		btnWeek[4] = new JButton("목");
		btnWeek[5] = new JButton("금");
		btnWeek[6] = new JButton("토");
		
		pWeek.add(btnWeek[0]);
		pWeek.add(btnWeek[1]);
		pWeek.add(btnWeek[2]);
		pWeek.add(btnWeek[3]);
		pWeek.add(btnWeek[4]);
		pWeek.add(btnWeek[5]);
		pWeek.add(btnWeek[6]);
		box.add(pWeek);
		box.add(Box.createVerticalGlue());
		
		
		
		pDate.setLayout(new GridLayout(6, 7));
		for (int i = 0; i < btnArr.length; i++) {
			btnArr[i] = new JButton("");
			pDate.add(btnArr[i]);
		}
		box.add(pDate);
		
		btnPrevMon.addActionListener(new BtnEventHandler());
		btnNextMon.addActionListener(new BtnEventHandler());
		
		btnDiarySave.addActionListener(new BtnEventHandler());
		
//		addWindowListener(new WindowAdapter() {
//			public void windowClosing (WindowEvent we) {
//				we.getWindow().setVisible(false);
//				we.getWindow().dispose();
//				//System.exit(0);
//			}
//		});
		
		//Diary.add(pUp, "North");
		//Diary.add(pWeek, "Center");
		//Diary.add(pDate, "South");		
		//add(Diary);		

		setDays(curMon);
		//setDialogLocation();
		//setVisible(true);
		
		box.add(Box.createVerticalGlue());
		JTextArea areaMemo = new JTextArea(7,20);
		areaMemo.setBackground(new Color(255,255,255));			
		JScrollPane scrollPane = new JScrollPane(
				areaMemo,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		box.add(scrollPane);
		add(box);
	}
	
	@SuppressWarnings("deprecation")
	void setDays(Calendar date) {
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		
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
//	public void setDialogLocation() {
//		
//		// 메인 프레임의 위치를 이용해 프레임을 띄우 x, y 위치를 구한다.
//		int x = mainFrame.getX() 
//				+ mainFrame.getWidth() / 2 - this.getWidth() / 2;
//		int y = mainFrame.getY() 
//				+ mainFrame.getHeight() / 2 - this.getHeight() / 2;		
//		
//		// 메인 프레임의 중앙에 가는 일정 선택 프레임을 띄운다.		
//		setLocation(x, y);		
//	}
	
	
	
	class BtnEventHandler implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			
			JButton src = (JButton)ae.getSource();
			
			if(src == btnPrevMon) {
				curMon.add(Calendar.MONTH, -1);
			} else if(src == btnNextMon) {
				curMon.add(Calendar.MONTH, 1);
			} 
			setDays(curMon);
			repaint();
			
			JButton src1 = (JButton)ae.getSource();
			
			if(src == btnDiarySave){
				
			}
			
		}
	}
}


		
	
