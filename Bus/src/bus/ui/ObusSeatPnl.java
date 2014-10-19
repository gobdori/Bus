package bus.ui;

import java.awt.Color;
import java.awt.Component;
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

public class ObusSeatPnl extends JPanel
{	
	RsvPnl sd;
	
	String[] arrSeats = {"A1","A2","A3","A4","B1","B2","B3","B4","C1","C2","C3","C4",
			"D1","D2","D3","D4","E1","E2","E3","E4","F1","F2","F3","F4",
			"G1","G2","G3","G4","H1","H2","H3","H4","I1","I2","I3","I4"};
	JButton button[]=new JButton[36];
	
	//선택된 좌석에 색깔을 주고 비활성화 시킴
	public void setSeat(Vector<String> seats)
	{	
		for(int i = 0; i<button.length;i++)
		{
			button[i].setBackground(Color.lightGray);
			button[i].setEnabled(true);
			for(int j=0;j<seats.size();j++)
			{			
				if(button[i].getText().equals(seats.get(j).toString()))
				{
					button[i].setBackground(Color.orange);					
					button[i].setEnabled(false);					
				}								
			}			
		}		
	}
	
	ObusSeatPnl(RsvPnl rs)
	{	
		sd = rs;
		//버튼 리스너 생성
		SelectMouseListener mouse = new SelectMouseListener();
		
		//우등버스 좌석이 들어갈 메인 박스
		Box seatMain = Box.createVerticalBox();
		
		//첫째줄이 들어간 박스
		Box oneLineSeat= Box.createHorizontalBox();
		Box twoLineSeat= Box.createHorizontalBox();			
		Box threeLineSeat= Box.createHorizontalBox();			
		Box midLineSeat= Box.createHorizontalBox();
		
		for(int i=0;i<button.length;i++)
		{
			button[i] = new JButton();
			int x = i;
			int rsint = x/4;
			int mod = x%4;
			String y = new String();
			
			switch(rsint)
			{
				case 0:	if(mod ==0)	y = "A"+4;else y = "A"+mod;
						break;				
				case 1:	if(mod ==0)	y = "B"+4;else y = "B"+mod;
						break;				
				case 2:	if(mod ==0)	y = "C"+4;else y = "C"+mod;
						break;				
				case 3:	if(mod ==0)	y = "D"+4;else y = "D"+mod;
						break;
				case 4:	if(mod ==0) y = "E"+4;else y = "E"+mod;
						break;
				case 5:	if(mod ==0)	y = "F"+4;else y = "F"+mod;
						break;				
				case 6:	if(mod ==0) y = "G"+4;else y = "G"+mod;
						break;				
				case 7: if(mod ==0) y = "H"+4;else y = "H"+mod;
						break;			
				case 8:	if(mod ==0) y = "I"+4;else y = "I"+mod;
						break;		
			}			
			
			button[i].setText(y);
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
	
	private class SelectMouseListener extends MouseAdapter
	{
		Vector<String> seats = new Vector<String>();
		Vector<String> adseats = new Vector<String>();
		Vector<String> chseats = new Vector<String>();
		Vector<String> hdseats = new Vector<String>();
		
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			JButton btn=(JButton)e.getSource();
			
			//버튼 색깔이 lightGray일 경우에 
			if(btn.getBackground()==Color.lightGray)
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
						btn.setBackground(Color.RED);
						seats.add(new String(btn.getText()));
						adseats.add(new String(btn.getText()));
						sd.seats = seats;
						sd.adultSeats = adseats;
					}
					else if(e.getButton() == 3)
					{
						btn.setBackground(Color.YELLOW);
						seats.add(new String(btn.getText()));
						hdseats.add(new String(btn.getText()));
						sd.seats = seats;
						sd.handySeats = hdseats;						
					}					
				}
			}
			else if(btn.getBackground()==Color.RED)
			{
				btn.setBackground(Color.PINK);
				chseats.add(new String(btn.getText()));
				sd.childSeats = chseats;
				
				adseats.remove(new String(btn.getText()));
				sd.adultSeats = adseats;
			}
			else if(btn.getBackground()==Color.ORANGE){}
			else
			{
				//버튼 색깔이 레드일 경우엔(클릭해제)
				//벡터에 저장된 값에서 text값을 없애줌
				seats.remove(new String(btn.getText()));
				adseats.remove(new String(btn.getText()));
				chseats.remove(new String(btn.getText()));
				hdseats.remove(new String(btn.getText()));
				
				sd.seats = seats;
				sd.adultSeats = adseats;
				sd.childSeats = chseats;
				sd.handySeats = hdseats;
				
				//원래의 색깔인 lightGray로 변경함.
				btn.setBackground(Color.lightGray);
			}
			
			sd.ComboAdult.setSelectedItem(String.valueOf(adseats.size()));
			sd.ComboChild.setSelectedItem(String.valueOf(chseats.size()));
			sd.ComboDisabled.setSelectedItem(String.valueOf(hdseats.size()));
			System.out.println("전체좌석"+ seats);
			System.out.println("어른좌석"+ adseats);
			System.out.println("아이좌석"+ chseats);
			System.out.println("장애좌석"+ hdseats);
			
		}
	}
}
