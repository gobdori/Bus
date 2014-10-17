package bus.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

public class BusThmPnl extends JPanel
{
	//downbox 안에 들어갈 패널 생성자.
	public BusThmPnl()
	{
		//패널 크기 
		this.setPreferredSize(new Dimension(620,410));		
		this.setMaximumSize(new Dimension(620,410));
		this.setMaximumSize(new Dimension(620,410));		
		
		//탭 패널을 추가.혹시나 몰라서 스크롤 되도록 넣음. 
		this.add(new JScrollPane(new pnl()));		
	}
}

//BusThmPnl에 tab을 넣으니 크기가 늘어남
//그래서 tab을 가질 패널(pnl)을 따로 생성
class pnl extends JPanel
{
	public pnl()
	{
		//패널에 탭으로 꽉 차야 하므로 Borderlayout
		this.setLayout(new BorderLayout());
		
		//tab을 생성
		JTabbedPane tap = new JTabbedPane();
		
		//tab에 4개의 tab을 생성해 다른 패널을 넣어줌
		tap.addTab("축제", new tabpnl("축제"));		
		tap.addTab("명승지",new tabpnl("명승지"));
		tap.addTab("숙소",new tabpnl("숙소"));
		tap.addTab("특산물",new tabpnl("특산물"));
		
		//탭이 스크롤이 가능하게 해줌. 
		JScrollPane scrollPane = new JScrollPane(tap);
		
		//스크롤이 가능하게 된 tab(scrollPane)을 pnl 패널에 넣음
		this.add(scrollPane,"Center");
	}
}

//tab 안에 있는 패널에 넣어야 하는 패널
class tabpnl extends JPanel
{
   public tabpnl(String tabname)
   {
	   //탭 이름에 따라서 이미지가 달리 보임. 
	   //이건 나중에 DB에서 받아오도록 만들어야 할듯. 
	   setLayout(new BorderLayout());
	   
	   //이미지를 넣을 JLabel을 만듬. 
	   JLabel imageLabel = new JLabel();
	   
	   //JLabel에 넣을 이미지 아이콘을 만듬. 
	   ImageIcon icon;
	   
	   //탭 이름에 따라 다른 이미지를 이미지아이콘에 넣음
	   if(tabname=="축제")
		   icon = new ImageIcon("images/THM/BS01/PUSANFTV.jpg");
	   else if(tabname=="명승지")
		   icon = new ImageIcon("images/THM/BS01/PUSANLCL.jpg");
	   else if(tabname=="숙소")
		   icon = new ImageIcon("images/THM/BS01/PUSANHTL.jpg");
	   else
		   icon = new ImageIcon("images/THM/BS01/PUSANLCP.jpg");
			  
	   //이미지가 저장된 이미지아이콘을 JLabel에 넣음. 
	   imageLabel.setIcon(icon);
	   
	   //이미지아이콘을 가진 JLabel을 패널에 추가
	   this.add(imageLabel);	   
   }
}