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
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class DiaryPnl extends JPanel{
	
	RsvPnl mainFrame;
	
	JPanel Diary = new JPanel();
	
	//JLabel을 JPanel로 넣어준다.  
	//JPanel Year = new JPanel();
	
	JPanel pDate = new JPanel();
	JPanel pWeek = new JPanel();
	JPanel pUp = new JPanel();
	JPanel pUp2 = new JPanel();
	JPanel pUp3 = new JPanel();
	JPanel pUp4 = new JPanel();
	JPanel pUp5 = new JPanel();	
	JLabel lblYearMon = new JLabel();
	String[] Week = {"일","월","화","수","목","금","토"};
	Box box = Box.createVerticalBox();
	
	JButton btnPrevMon = new JButton("◀");
	JButton btnNextMon = new JButton("▶");
	//저장 버튼
	JButton btnDiarySave = new JButton("저장");
	JButton[] btnArr = new JButton[42];
	JButton[] btnWeek = new JButton[7];
	
	Calendar curMon = Calendar.getInstance();
 
	public DiaryPnl(){
		this.mainFrame = mainFrame;
		
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(620,410));
		this.setMaximumSize(new Dimension(620,410));
		this.setMaximumSize(new Dimension(620,410));
		
		box.setPreferredSize(new Dimension(580,380));
		
		
		pUp.setLayout(new FlowLayout(FlowLayout.RIGHT));		
		pUp2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		pUp.add(btnPrevMon);
		pUp.add(lblYearMon);
		pUp.add(btnNextMon);	
		
		pUp2.add(btnDiarySave);
		
		pUp5.setPreferredSize(new Dimension(180,10));
		pUp4.setPreferredSize(new Dimension(120,10));
		pUp3.add(pUp5);
		pUp3.add(pUp);
		pUp3.add(pUp4);
		pUp3.add(pUp2);
		
		pUp3.setPreferredSize(new Dimension(580, 40));
		pUp3.setMaximumSize(new Dimension(580, 40));
		pUp3.setMinimumSize(new Dimension(580, 40));
		box.add(pUp3);
		box.add(Box.createVerticalGlue());
		
		pWeek.setLayout(new GridLayout(1, 7));
		for (int i = 0; i < Week.length; i++) {
			pWeek.add(lbladdWeek(Week[i], Color.LIGHT_GRAY));
		}		

		pDate.setLayout(new GridLayout(6, 7));
		for (int i = 0; i < btnArr.length; i++) {
			btnArr[i] = new JButton("");
			pDate.add(btnArr[i]);
		}		
		btnPrevMon.addActionListener(new BtnEventHandler());
		btnNextMon.addActionListener(new BtnEventHandler());	
		btnDiarySave.addActionListener(new BtnEventHandler());
		setDays(curMon);	
		
		JTextArea areaMemo = new JTextArea(7,20);
		areaMemo.setBackground(new Color(255,255,255));			
		JScrollPane scrollPane = new JScrollPane(areaMemo,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				
		box.add(pWeek);
		box.add(Box.createVerticalGlue());
		box.add(pDate);
		box.add(Box.createVerticalGlue());
		box.add(scrollPane);
		add(box);	
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


		
	
