package bus.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPnl extends JPanel
{
	JButton btnCardLayout = new JButton("카드레이아웃");
	JButton btnBoxLayout  = new JButton("박스레이아웃");
	
	JButton btnCard1  = new JButton("예약");
	JButton btnCard2  = new JButton("예매정보");
	JButton btnCard3  = new JButton("여정지정보");
	JButton btnCard4  = new JButton("여행후기");
		
	JPanel pnlFirstMenu = new JPanel();
	JPanel pnlCardMenu = new JPanel();
	JPanel bxCardMenu = new JPanel();
	JPanel pnlBoxMenu = new JPanel();
	
	BusMainFrm busMainFrm;
	MenuPnl menupnl;
	
	MenuPnl(BusMainFrm busMainFrm)
	{
		menupnl = this;
		this.busMainFrm = busMainFrm;
		this.setPreferredSize(new Dimension(800,50));
		this.setMaximumSize(new Dimension(800,50));
		this.setMinimumSize(new Dimension(800,50));
		this.setLayout(new BorderLayout());
		
		btnCardLayout.addActionListener(new ButtonActionListener());
		btnBoxLayout.addActionListener(new ButtonActionListener());
		btnCard1.addActionListener(new ButtonActionListener());
		btnCard2.addActionListener(new ButtonActionListener());
		btnCard3.addActionListener(new ButtonActionListener());
		btnCard4.addActionListener(new ButtonActionListener());		
				
		pnlFirstMenu.setLayout(new GridLayout());
		pnlFirstMenu.add(btnCardLayout);
		pnlFirstMenu.add(btnBoxLayout);
		
		pnlCardMenu.setLayout(new GridLayout());
		pnlCardMenu.add(btnCard1);		
		pnlCardMenu.add(btnCard2);
		pnlCardMenu.add(btnCard3);		
		pnlCardMenu.add(btnCard4);		
		
		FlowLayout fw = new FlowLayout();
		fw.setAlignment(fw.RIGHT);
		pnlBoxMenu.setLayout(fw);
		JLabel lblUserName = new JLabel("유져네임");
		pnlBoxMenu.add(lblUserName);
		
		add(pnlFirstMenu);
	}
	
	public void removeFristPnl()
	{
		this.remove(pnlFirstMenu);		
	}
		
	private class ButtonActionListener implements ActionListener 
	{		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton btn = (JButton) e.getSource();
			
			if(btn ==btnCardLayout)
			{				
				//busMainFrm.removeMainbx();				
				menupnl.add(pnlCardMenu);
				busMainFrm.CardLayoutFrm(menupnl);				
			}
			else if(btn == btnBoxLayout)
			{		
				//busMainFrm.removeMainbx();				
				menupnl.add(pnlBoxMenu);
				busMainFrm.BoxLayoutFrm(menupnl);				
			}
			else if(btn == btnCard1)
			{
				busMainFrm.cardPnl.cardLayout.show(busMainFrm.cardPnl, "예약");
			}
			else if(btn == btnCard2)
			{
				busMainFrm.cardPnl.cardLayout.show(busMainFrm.cardPnl, "예약정보");
			}
			else if(btn == btnCard3)
			{
				busMainFrm.cardPnl.cardLayout.show(busMainFrm.cardPnl, "여정지정보");
			}
			else if(btn == btnCard4)
			{
				busMainFrm.cardPnl.cardLayout.show(busMainFrm.cardPnl, "여행후기");
			}			
		}
	}
}