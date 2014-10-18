package bus.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PbusSeatPnl extends JPanel
{
	RsvPnl sd;
	
	String[] arrSeats = {"A1","A2","A3","B1","B2","B3","C1","C2","C3",
			"D1","D2","D3","E1","E2","E3","F1","F2","F3",
			"G1","G2","G3","H1","H2","H3","I1","I2","I3","I4"};
	JButton button[]=new JButton[28];
		
	public void setSeat(Vector<String> seats)
	{
		for(int i = 0; i<arrSeats.length;i++)
		{
			if(seats.contains(arrSeats[i].toString()))
			{
				for(int j = 0; j<button.length;j++)
				{
					if(button[i].getText()==arrSeats[i].toString())
					{
						button[i].setBackground(Color.orange);
						button[i].setEnabled(false);
					}
					else
					{
						button[i].setBackground(Color.lightGray);
						button[i].setEnabled(true);
					}
				}								
			}
		}		
	}
	
	PbusSeatPnl(RsvPnl rp)
	{
		sd = rp;
		
		SelectMouseListener mouse = new SelectMouseListener();
		Box seatMain = Box.createVerticalBox();
		Box oneLineSeat= Box.createHorizontalBox();
		Box twoLineSeat= Box.createHorizontalBox();
		twoLineSeat.setBackground(Color.black);
		Box threeLineSeat= Box.createHorizontalBox();
		threeLineSeat.setBackground(Color.green);
		JPanel midLineSeat= new JPanel();
		
		for(int i=0;i<button.length;i++)
		{
			button[i] = new JButton();
			int x = i;
			int rsint = x/3;
			int mod = x%3;
			String y = new String();
			if(i!=27)
			{				
				switch(rsint)
				{
					case 0:	if(mod ==0)	y = "A"+3;else y = "A"+mod;
							break;				
					case 1:	if(mod ==0)	y = "B"+3;else y = "B"+mod;
							break;				
					case 2:	if(mod ==0)	y = "C"+3;else y = "C"+mod;
							break;				
					case 3:	if(mod ==0)	y = "D"+3;else y = "D"+mod;
							break;
					case 4:	if(mod ==0) y = "E"+3;else y = "E"+mod;
							break;
					case 5:	if(mod ==0)	y = "F"+3;else y = "F"+mod;
							break;				
					case 6:	if(mod ==0) y = "G"+3;else y = "G"+mod;
							break;				
					case 7: if(mod ==0) y = "H"+3;else y = "H"+mod;
							break;			
					case 8:	if(mod ==0) y = "I"+3;else y = "I"+mod;
							break;		
				}
			}
			else
			{
				y="I4";
			}
			
			button[i].setText(y);
			//button[i].addActionListener(select);
			button[i].addMouseListener(mouse);
			button[i].setBackground(Color.lightGray);
			
			if(button[i].getText().endsWith("1"))
			{
				oneLineSeat.add(button[i]);
				oneLineSeat.add(Box.createHorizontalGlue());
			}
			else if(button[i].getText().endsWith("2"))
			{
				twoLineSeat.add(button[i]);
				twoLineSeat.add(Box.createHorizontalGlue());
			}
				
			else if(button[i].getText().endsWith("3"))
			{
				threeLineSeat.add(button[i]);
				threeLineSeat.add(Box.createHorizontalGlue());
				
			}
			else if(button[i].getText().endsWith("4"))
			{
				midLineSeat.add(button[i]);
				midLineSeat.add(Box.createHorizontalGlue());
			}
		}
				/*
		 A3 = new JButton("A3");
		A3.addActionListener(select);
		A3.setBackground(Color.lightGray);
		 B3 = new JButton("B3");
		B3.addActionListener(select);
		B3.setBackground(Color.lightGray);
		 C3 = new JButton("C3");
		C3.addActionListener(select);
		C3.setBackground(Color.lightGray);
		 D3 = new JButton("D3");
		D3.addActionListener(select);
		D3.setBackground(Color.lightGray);
		 E3= new JButton("E3");
		E3.addActionListener(select);
		E3.setBackground(Color.lightGray);		
		 F3 = new JButton("F3");
		F3.addActionListener(select);
		F3.setBackground(Color.lightGray);
		 G3 = new JButton("G3");
		G3.addActionListener(select);
		G3.setBackground(Color.lightGray);
		 H3 = new JButton("H3");
		H3.addActionListener(select);
		H3.setBackground(Color.lightGray);
		 I4 = new JButton("I4");
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
		threeLineSeat.add(I4);*/
		
		//Obus랑 다른 점..
		//한줄이 없고 맨뒤 좌석이 4칸이라서 
		//통로를 추가 한것이 아니라 3번째 줄에서
		//맨 뒤 좌석만 남기고 나머진 공백으로 처리.
		//박스가 아닌 JPanel로 생성. Flowlayout을 쓸 예정. 
		
		/*
		 I3 = new JButton("I3");
		I3.addActionListener(select);
		I3.setBackground(Color.lightGray);
		*/
		//Flowlayout에 속성을 셋팅하기 위해 객체 생성.  
		FlowLayout fw = new FlowLayout();
		fw.setAlignment(fw.RIGHT);
		fw.setHgap(0);
		fw.setVgap(0);
		
		//midLineSeat 패널에 레이아웃 적용. 
		midLineSeat.setLayout(fw);
		
		//midLineSeat.add(I3);		
		
		seatMain.add(oneLineSeat);
		seatMain.add(Box.createVerticalGlue());
		seatMain.add(twoLineSeat);	
		seatMain.add(Box.createVerticalGlue());
		seatMain.add(midLineSeat);
		seatMain.add(Box.createVerticalGlue());
		seatMain.add(threeLineSeat);
		add(seatMain);		
	}
	
	private class SelectMouseListener extends MouseAdapter
	{
		Vector<String> seats = new Vector<String>();
		Vector<String> adseats = new Vector<String>();
		Vector<String> chseats = new Vector<String>();
		Vector<String> hdseats = new Vector<String>();
		
		@Override
		public void mouseClicked(MouseEvent e) 
		{
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
					if(e.getButton()==1 && e.getClickCount() == 1)
					{
						b.setBackground(Color.RED);
						seats.add(new String(b.getText()));
						adseats.add(new String(b.getText()));
						sd.seats = seats;
						sd.adultSeats = adseats;
					}
					else if(e.getButton() == 3)
					{
						b.setBackground(Color.YELLOW);
						seats.add(new String(b.getText()));
						hdseats.add(new String(b.getText()));
						sd.seats = seats;
						sd.handySeats = hdseats;						
					}					
				}
			}
			else if(b.getBackground()==Color.RED)
			{
				b.setBackground(Color.PINK);
				chseats.add(new String(b.getText()));
				sd.childSeats = chseats;
				
				adseats.remove(new String(b.getText()));
				sd.adultSeats = adseats;
			}
			else
			{
				//버튼 색깔이 레드일 경우엔(클릭해제)
				//벡터에 저장된 값에서 text값을 없애줌
				seats.remove(new String(b.getText()));
				adseats.remove(new String(b.getText()));
				chseats.remove(new String(b.getText()));
				hdseats.remove(new String(b.getText()));
				
				sd.seats = seats;
				sd.adultSeats = adseats;
				sd.childSeats = chseats;
				sd.handySeats = hdseats;
				
				//원래의 색깔인 lightGray로 변경함.
				b.setBackground(Color.lightGray);
			}
			
			System.out.println("전체좌석"+ seats);
			System.out.println("어른좌석"+ adseats);
			System.out.println("아이좌석"+ chseats);
			System.out.println("장애좌석"+ hdseats);
			
		}
	}
}


