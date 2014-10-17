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

public class PbusSeatPnl extends JPanel
{
	RsvPnl sd;
	//전체적으로 ObusSeatPnl과 동일. 
	PbusSeatPnl(RsvPnl rp)
	{
		sd = rp; 
		SelectActionListener select = new SelectActionListener();
		Box seatMain = Box.createVerticalBox();
		Box oneLineSeat= Box.createHorizontalBox();
		
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
		oneLineSeat.setBackground(Color.red);
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
		JButton I4 = new JButton("I4");
		I4.addActionListener(select);
		I4.setBackground(Color.lightGray);
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
		threeLineSeat.add(I4);
		
		//Obus랑 다른 점..
		//한줄이 없고 맨뒤 좌석이 4칸이라서 
		//통로를 추가 한것이 아니라 3번째 줄에서
		//맨 뒤 좌석만 남기고 나머진 공백으로 처리.
		//박스가 아닌 JPanel로 생성. Flowlayout을 쓸 예정. 
		JPanel midLineSeat= new JPanel();
		
		JButton I3 = new JButton("I3");
		I3.addActionListener(select);
		I3.setBackground(Color.lightGray);
		
		//Flowlayout에 속성을 셋팅하기 위해 객체 생성.  
		FlowLayout fw = new FlowLayout();
		fw.setAlignment(fw.RIGHT);
		fw.setHgap(0);
		fw.setVgap(0);
		
		//midLineSeat 패널에 레이아웃 적용. 
		midLineSeat.setLayout(fw);
		
		midLineSeat.add(I3);		
		
		seatMain.add(oneLineSeat);
		seatMain.add(Box.createVerticalGlue());
		seatMain.add(twoLineSeat);	
		seatMain.add(Box.createVerticalGlue());
		seatMain.add(midLineSeat);
		seatMain.add(Box.createVerticalGlue());
		seatMain.add(threeLineSeat);
		add(seatMain);		
	}
	
	private class SelectActionListener implements ActionListener 
	{		
		Vector<String> seats = new Vector<String>();
		@Override
		public void actionPerformed(ActionEvent e) 
		{	
			JButton b=(JButton)e.getSource();
			
			if(b.getBackground()==Color.lightGray)
			{
				if(seats.size()>=5)
				{	
					JOptionPane.showMessageDialog(null, "예약은 5자리까지 가능합니다. ");
				}
				else
				{					
					b.setBackground(Color.red);				
					seats.add(new String(b.getText()));
					sd.seats = seats;
				}
			}
			else
			{
				seats.remove(new String(b.getText()));
				sd.seats = seats;
				b.setBackground(Color.lightGray);
			}
		}
	}
}


