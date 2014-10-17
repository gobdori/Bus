package bus.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PbusSeatPnl extends JPanel
{
	RsvPnl sd;
	
	JButton A1,A2,A3,A4,B1,B2,B3,B4,C1,C2,C3,C4,D1,D2,D3,D4,E1,E2,E3,E4,
	F1,F2,F3,F4,G1,G2,G3,G4,H1,H2,H3,H4,I1,I2,I3,I4;

	//전체적으로 ObusSeatPnl과 동일. 
	PbusSeatPnl(RsvPnl rs, Vector<String> seats)
	{
		this(rs);
		HashMap<String, JButton> buttonMap = new HashMap<String, JButton>();
		for(int i=0; i<seats.size();i++)
		{	    
		    JButton btn = new JButton(seats.get(i));
		    buttonMap.put(seats.get(i), btn);
		    		    
		    JButton myButton = buttonMap.get(seats.get(i));
		    myButton.setBackground(Color.orange);
		    myButton.setEnabled(false);
		}
	}
	
	public void setSeat(Vector<String> seats)
	{
		if(seats.contains("A1"))
		{A1.setBackground(Color.orange);A1.setEnabled(false);}
		else{A1.setBackground(Color.lightGray);A1.setEnabled(true);}
		if(seats.contains("A2"))
		{A2.setBackground(Color.orange);A2.setEnabled(false);}
		else{A2.setBackground(Color.lightGray);A2.setEnabled(true);}
		if(seats.contains("A3"))
		{A3.setBackground(Color.orange);A3.setEnabled(false);}
		else{A3.setBackground(Color.lightGray);A3.setEnabled(true);}
		
		if(seats.contains("B1"))
		{B1.setBackground(Color.orange);B1.setEnabled(false);}
		else{B1.setBackground(Color.lightGray);B1.setEnabled(true);}
		if(seats.contains("B2"))
		{B2.setBackground(Color.orange);B2.setEnabled(false);}
		else{B2.setBackground(Color.lightGray);B2.setEnabled(true);}
		if(seats.contains("B3"))
		{B3.setBackground(Color.orange);B3.setEnabled(false);}
		else{B3.setBackground(Color.lightGray);B3.setEnabled(true);}
		
		if(seats.contains("C1"))
		{C1.setBackground(Color.orange);C1.setEnabled(false);}
		else{C1.setBackground(Color.lightGray);C1.setEnabled(true);}
		if(seats.contains("C2"))
		{C2.setBackground(Color.orange);C2.setEnabled(false);}
		else{C2.setBackground(Color.lightGray);C2.setEnabled(true);}
		if(seats.contains("C3"))
		{C3.setBackground(Color.orange);C3.setEnabled(false);}		
		else{C3.setBackground(Color.lightGray);C3.setEnabled(true);}
		
		if(seats.contains("D1"))
		{D1.setBackground(Color.orange);D1.setEnabled(false);}
		else{D1.setBackground(Color.lightGray);D1.setEnabled(true);}
		if(seats.contains("D2"))
		{D2.setBackground(Color.orange);D2.setEnabled(false);}
		else{D2.setBackground(Color.lightGray);D2.setEnabled(true);}
		if(seats.contains("D3"))
		{D3.setBackground(Color.orange);D3.setEnabled(false);}
		else{D3.setBackground(Color.lightGray);D3.setEnabled(true);}
		
		if(seats.contains("E1"))
		{E1.setBackground(Color.orange);E1.setEnabled(false);}
		else{E1.setBackground(Color.lightGray);E1.setEnabled(true);}
		if(seats.contains("E2"))
		{E2.setBackground(Color.orange);E2.setEnabled(false);}
		else{E2.setBackground(Color.lightGray);E2.setEnabled(true);}
		if(seats.contains("E3"))
		{E3.setBackground(Color.orange);E3.setEnabled(false);}
		else{E3.setBackground(Color.lightGray);E3.setEnabled(true);}
		
		if(seats.contains("F1"))
		{F1.setBackground(Color.orange);F1.setEnabled(false);}
		else{F1.setBackground(Color.lightGray);F1.setEnabled(true);}
		if(seats.contains("F2"))
		{F2.setBackground(Color.orange);F2.setEnabled(false);}
		else{F2.setBackground(Color.lightGray);F2.setEnabled(true);}
		if(seats.contains("F3"))
		{F3.setBackground(Color.orange);F3.setEnabled(false);}
		else{F3.setBackground(Color.lightGray);F3.setEnabled(true);}
		
		if(seats.contains("G1"))
		{G1.setBackground(Color.orange);G1.setEnabled(false);}
		else{G1.setBackground(Color.lightGray);G1.setEnabled(true);}
		if(seats.contains("G2"))
		{G2.setBackground(Color.orange);G2.setEnabled(false);}
		else{G2.setBackground(Color.lightGray);G2.setEnabled(true);}
		if(seats.contains("G3"))
		{G3.setBackground(Color.orange);G3.setEnabled(false);}
		else{G3.setBackground(Color.lightGray);G3.setEnabled(true);}
		
		if(seats.contains("H1"))
		{H1.setBackground(Color.orange);H1.setEnabled(false);}
		else{H1.setBackground(Color.lightGray);H1.setEnabled(true);}
		if(seats.contains("H2"))
		{H2.setBackground(Color.orange);H2.setEnabled(false);}
		else{H2.setBackground(Color.lightGray);H2.setEnabled(true);}
		if(seats.contains("H3"))
		{H3.setBackground(Color.orange);H3.setEnabled(false);}
		else{H3.setBackground(Color.lightGray);H3.setEnabled(true);}
		
		if(seats.contains("I1"))
		{I1.setBackground(Color.orange);I1.setEnabled(false);}
		else{I1.setBackground(Color.lightGray);I1.setEnabled(true);}
		if(seats.contains("I2"))
		{I2.setBackground(Color.orange);I2.setEnabled(false);}
		else{I2.setBackground(Color.lightGray);I2.setEnabled(true);}
		if(seats.contains("I3"))
		{I3.setBackground(Color.orange);I3.setEnabled(false);}
		else{I3.setBackground(Color.lightGray);I3.setEnabled(true);}
		if(seats.contains("I4"))
		{I4.setBackground(Color.orange);I4.setEnabled(false);}		
		else{I4.setBackground(Color.lightGray);I4.setEnabled(true);}		
	}
	
	PbusSeatPnl(RsvPnl rp)
	{
		sd = rp; 
		SelectActionListener select = new SelectActionListener();
		Box seatMain = Box.createVerticalBox();
		Box oneLineSeat= Box.createHorizontalBox();
		
		 A1 = new JButton("A1");
		A1.addActionListener(select);
		A1.setBackground(Color.lightGray);
		 B1 = new JButton("B1");
		B1.addActionListener(select);
		B1.setBackground(Color.lightGray);
		 C1 = new JButton("C1");
		C1.addActionListener(select);
		C1.setBackground(Color.lightGray);
		 D1 = new JButton("D1");
		D1.addActionListener(select);
		D1.setBackground(Color.lightGray);
		 E1 = new JButton("E1");
		E1.addActionListener(select);
		E1.setBackground(Color.lightGray);
		 F1 = new JButton("F1");
		F1.addActionListener(select);
		F1.setBackground(Color.lightGray);
		 G1 = new JButton("G1");
		G1.addActionListener(select);
		G1.setBackground(Color.lightGray);
		 H1 = new JButton("H1");
		H1.addActionListener(select);
		H1.setBackground(Color.lightGray);
		 I1 = new JButton("I1");
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
		 A2 = new JButton("A2");
		A2.addActionListener(select);
		A2.setBackground(Color.lightGray);
		 B2 = new JButton("B2");
		B2.addActionListener(select);
		B2.setBackground(Color.lightGray);
		 C2 = new JButton("C2");
		C2.addActionListener(select);
		C2.setBackground(Color.lightGray);
		 D2 = new JButton("D2");
		D2.addActionListener(select);
		D2.setBackground(Color.lightGray);
		 E2= new JButton("E2");
		E2.addActionListener(select);
		E2.setBackground(Color.lightGray);
		 F2 = new JButton("F2");
		F2.addActionListener(select);
		F2.setBackground(Color.lightGray);
		 G2 = new JButton("G2");
		G2.addActionListener(select);
		G2.setBackground(Color.lightGray);
		 H2 = new JButton("H2");
		H2.addActionListener(select);
		H2.setBackground(Color.lightGray);
		 I2 = new JButton("I2");
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
		threeLineSeat.add(I4);
		
		//Obus랑 다른 점..
		//한줄이 없고 맨뒤 좌석이 4칸이라서 
		//통로를 추가 한것이 아니라 3번째 줄에서
		//맨 뒤 좌석만 남기고 나머진 공백으로 처리.
		//박스가 아닌 JPanel로 생성. Flowlayout을 쓸 예정. 
		JPanel midLineSeat= new JPanel();
		
		 I3 = new JButton("I3");
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


