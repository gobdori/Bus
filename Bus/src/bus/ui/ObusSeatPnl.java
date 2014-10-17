package bus.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class ObusSeatPnl extends JPanel
{
	RsvPnl sd;
	
	ObusSeatPnl(RsvPnl rs)
	{
		sd = rs;
		//버튼 액선리스너 생성
		SelectActionListener select = new SelectActionListener();
		
		//우등버스 좌석이 들어갈 메인 박스
		Box seatMain = Box.createVerticalBox();
		
		//첫째줄이 들어간 박스
		Box oneLineSeat= Box.createHorizontalBox();
		
		//첫재줄에 들어갈 버튼(좌석)을 만들고 리스너 연결
		//기본 색은 lightGray로 지정했음. 
		JButton A1 = new JButton("A1");
		A1.addActionListener(select);
		A1.setBackground(Color.lightGray);
		JButton B1 = new JButton("B1");
		B1.addActionListener(select);
		B1.setBackground(Color.lightGray);
		JButton C1 = new JButton("C1");
		C1.addActionListener(select);
		C1.setBackground(Color.lightGray);
		JButton D1 = new JButton("D1");
		D1.addActionListener(select);
		D1.setBackground(Color.lightGray);
		JButton E1 = new JButton("E1");
		E1.addActionListener(select);
		E1.setBackground(Color.lightGray);
		JButton F1 = new JButton("F1");
		F1.addActionListener(select);
		F1.setBackground(Color.lightGray);
		JButton G1 = new JButton("G1");
		G1.addActionListener(select);
		G1.setBackground(Color.lightGray);
		JButton H1 = new JButton("H1");
		H1.addActionListener(select);
		H1.setBackground(Color.lightGray);
		JButton I1 = new JButton("I1");
		I1.addActionListener(select);
		I1.setBackground(Color.lightGray);
		
		//첫번째 박스에 버튼을 넣음.
		//박스형에 콤포넌트(버튼이나 textfield 같은)를 넣을 경우엔
		//중간 중간에 해당 박스에 맞는 glue를 넣어준다. 
		//strut는 고정값을 넣어주는 형식
		oneLineSeat.add(A1);
		oneLineSeat.add(Box.createHorizontalGlue());
		oneLineSeat.add(B1);
		oneLineSeat.add(Box.createHorizontalGlue());
		oneLineSeat.add(C1);
		oneLineSeat.add(Box.createHorizontalGlue());
		oneLineSeat.add(D1);
		oneLineSeat.add(Box.createHorizontalGlue());
		oneLineSeat.add(E1);
		oneLineSeat.add(Box.createHorizontalGlue());
		oneLineSeat.add(F1);
		oneLineSeat.add(Box.createHorizontalGlue());
		oneLineSeat.add(G1);
		oneLineSeat.add(Box.createHorizontalGlue());
		oneLineSeat.add(H1);
		oneLineSeat.add(Box.createHorizontalGlue());
		oneLineSeat.add(I1);
		
		//첫 째줄과 동일. . 
		Box twoLineSeat= Box.createHorizontalBox();
		twoLineSeat.setBackground(Color.black);
		JButton A2 = new JButton("A2");
		A2.addActionListener(select);
		A2.setBackground(Color.lightGray);
		JButton B2 = new JButton("B2");
		B2.addActionListener(select);
		B2.setBackground(Color.lightGray);
		JButton C2 = new JButton("C2");
		C2.addActionListener(select);
		C2.setBackground(Color.lightGray);
		JButton D2 = new JButton("D2");
		D2.addActionListener(select);
		D2.setBackground(Color.lightGray);
		JButton E2= new JButton("E2");
		E2.addActionListener(select);
		E2.setBackground(Color.lightGray);
		JButton F2 = new JButton("F2");
		F2.addActionListener(select);
		F2.setBackground(Color.lightGray);
		JButton G2 = new JButton("G2");
		G2.addActionListener(select);
		G2.setBackground(Color.lightGray);
		JButton H2 = new JButton("H2");
		H2.addActionListener(select);
		H2.setBackground(Color.lightGray);
		JButton I2 = new JButton("I2");
		I2.addActionListener(select);
		I2.setBackground(Color.lightGray);
		twoLineSeat.add(A2);
		twoLineSeat.add(Box.createHorizontalGlue());
		twoLineSeat.add(B2);
		twoLineSeat.add(Box.createHorizontalGlue());
		twoLineSeat.add(C2);
		twoLineSeat.add(Box.createHorizontalGlue());
		twoLineSeat.add(D2);
		twoLineSeat.add(Box.createHorizontalGlue());
		twoLineSeat.add(E2);
		twoLineSeat.add(Box.createHorizontalGlue());		
		twoLineSeat.add(F2);
		twoLineSeat.add(Box.createHorizontalGlue());
		twoLineSeat.add(G2);
		twoLineSeat.add(Box.createHorizontalGlue());
		twoLineSeat.add(H2);
		twoLineSeat.add(Box.createHorizontalGlue());
		twoLineSeat.add(I2);
		
		//첫번째 박스와 동일
		Box threeLineSeat= Box.createHorizontalBox();
		threeLineSeat.setBackground(Color.green);
		JButton A3 = new JButton("A3");
		A3.addActionListener(select);
		A3.setBackground(Color.lightGray);
		JButton B3 = new JButton("B3");
		B3.addActionListener(select);
		B3.setBackground(Color.lightGray);
		JButton C3 = new JButton("C3");
		C3.addActionListener(select);
		C3.setBackground(Color.lightGray);
		JButton D3 = new JButton("D3");
		D3.addActionListener(select);
		D3.setBackground(Color.lightGray);
		JButton E3= new JButton("E3");
		E3.addActionListener(select);
		E3.setBackground(Color.lightGray);		
		JButton F3 = new JButton("F3");
		F3.addActionListener(select);
		F3.setBackground(Color.lightGray);
		JButton G3 = new JButton("G3");
		G3.addActionListener(select);
		G3.setBackground(Color.lightGray);
		JButton H3 = new JButton("H3");
		H3.addActionListener(select);
		H3.setBackground(Color.lightGray);
		JButton I3 = new JButton("I3");
		I3.addActionListener(select);
		I3.setBackground(Color.lightGray);
		threeLineSeat.add(A3);
		threeLineSeat.add(Box.createHorizontalGlue());
		threeLineSeat.add(B3);
		threeLineSeat.add(Box.createHorizontalGlue());
		threeLineSeat.add(C3);
		threeLineSeat.add(Box.createHorizontalGlue());
		threeLineSeat.add(D3);
		threeLineSeat.add(Box.createHorizontalGlue());	
		threeLineSeat.add(E3);
		threeLineSeat.add(Box.createHorizontalGlue());
		threeLineSeat.add(F3);
		threeLineSeat.add(Box.createHorizontalGlue());
		threeLineSeat.add(G3);
		threeLineSeat.add(Box.createHorizontalGlue());
		threeLineSeat.add(H3);
		threeLineSeat.add(Box.createHorizontalGlue());
		threeLineSeat.add(I3);
		
		//첫번째줄과 동일. 
		Box midLineSeat= Box.createHorizontalBox();		
		JButton A4 = new JButton("A4");
		A4.addActionListener(select);
		A4.setBackground(Color.lightGray);
		JButton B4 = new JButton("B4");
		B4.addActionListener(select);
		B4.setBackground(Color.lightGray);
		JButton C4 = new JButton("C4");
		C4.addActionListener(select);
		C4.setBackground(Color.lightGray);
		JButton D4 = new JButton("D4");
		D4.addActionListener(select);
		D4.setBackground(Color.lightGray);
		JButton E4 = new JButton("E4");
		E4.addActionListener(select);
		E4.setBackground(Color.lightGray);
		JButton F4 = new JButton("F4");
		F4.addActionListener(select);
		F4.setBackground(Color.lightGray);
		JButton G4 = new JButton("G4");
		G4.addActionListener(select);
		G4.setBackground(Color.lightGray);
		JButton H4 = new JButton("H4");
		H4.addActionListener(select);
		H4.setBackground(Color.lightGray);
		JButton I4 = new JButton("I4");
		I4.addActionListener(select);
		I4.setBackground(Color.lightGray);
		
		midLineSeat.add(A4);
		midLineSeat.add(Box.createHorizontalGlue());
		midLineSeat.add(B4);
		midLineSeat.add(Box.createHorizontalGlue());
		midLineSeat.add(C4);
		midLineSeat.add(Box.createHorizontalGlue());
		midLineSeat.add(D4);
		midLineSeat.add(Box.createHorizontalGlue());	
		midLineSeat.add(E4);
		midLineSeat.add(Box.createHorizontalGlue());
		midLineSeat.add(F4);
		midLineSeat.add(Box.createHorizontalGlue());
		midLineSeat.add(G4);
		midLineSeat.add(Box.createHorizontalGlue());
		midLineSeat.add(H4);
		midLineSeat.add(Box.createHorizontalGlue());
		midLineSeat.add(I4);		
		
		seatMain.add(oneLineSeat);
		seatMain.add(Box.createVerticalGlue());
		seatMain.add(twoLineSeat);	
		seatMain.add(Box.createVerticalStrut(20));//통로를 표현하기 위한 공백. 
		seatMain.add(midLineSeat);
		seatMain.add(Box.createVerticalGlue());
		seatMain.add(threeLineSeat);
		
		//패널에 메인박스를 추가 해 준다. 
		add(seatMain);		
	}
	
	//액션 리스너 생성. 
	private class SelectActionListener implements ActionListener
	{
		//선택한 좌석을 지정한 벡터 생성. 
		Vector<String> seats = new Vector<String>();
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//JButton을 하나 생성해서 클릭된 JButton의 값을 넣어준다.			
			JButton b=(JButton)e.getSource();
			
			//버튼 색깔이 lightGray일 경우에 
			if(b.getBackground()==Color.lightGray)
			{
				//선택된 좌석이 5자리 이상일 경우. 
				if(seats.size()>=5)
				{	
					//메세지 박스 출력. 
					JOptionPane.showMessageDialog(null, "예약은 5자리까지 가능합니다. ");
				}
				else
				{
					//버튼 색깔을 빨간색으로 바꿈.
					b.setBackground(Color.red);
					
					//선택된 버튼의 text를 벡터에 넣음. 
					seats.add(new String(b.getText()));
					sd.seats = seats;
				}
			}
			else
			{
				//버튼 색깔이 레드일 경우엔(클릭해제)
				//벡터에 저장된 값에서 text값을 없애줌
				seats.remove(new String(b.getText()));
				sd.seats = seats;
				
				//원래의 색깔인 lightGray로 변경함.
				b.setBackground(Color.lightGray);
			}
		}
	}

}