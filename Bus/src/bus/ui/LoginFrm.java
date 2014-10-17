package bus.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bus.db.BusDbConnect;
import bus.db.DbQuery;

public class LoginFrm extends JFrame 
{
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub				
		new LoginFrm();
	}	
	
	//ID를 적는 텍스트박스(초기값 ID를 가짐. 15는 텍스트박스크기)
	JTextField txtId = new JTextField("BBBB", 15);	
	
	//패스워드를 적는 텍스트 박스(초기값 P/W를 가짐. 15는 텍스트박스크기) 
	JPasswordField txtPassword = new JPasswordField("BBBB",15);
	
	//로그인 버튼
	JButton btnLogin = new JButton("LOGIN");
	
	LoginFrm()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Login 프레임의 레이아웃 = FlowLayout
		this.setLayout(new FlowLayout());
		
		//텍스트박스랑 버튼이 들어갈 lg패널 생성
		JPanel lgPnl = new JPanel();
		
		lgPnl.add(txtId);//lg패널에 텍스트박스 추가
		lgPnl.add(txtPassword);//lg패널에 텍스트박스 추가
		lgPnl.add(btnLogin);//lg패널에 버튼 추가		
		
		//lg패널을 투명하게 설정(불투명을 flase로 설정함으로)  
		//이유는 다음에 추가할 bg패널의 그림이 보여야하기 때문
		lgPnl.setOpaque(false);
		
		//그림으로 입혀진 bg 패널을 생성
		//bgPanel 이라는 클래스를 추가해놨으니 볼것!!
		JPanel bg = new BgPanel();
		
		//화면에 꽉 채워야 하므로 보더레이아웃 설정.
		bg.setLayout(new BorderLayout());
				
		//bg패널에 lg 패널을 추가하여 텍스트박스랑 버튼이 보이도록함 
		bg.add(lgPnl, BorderLayout.CENTER );
		
		//버튼이랑 텍스트 박스에서 사용할 리스너 생성.
		LoginActionListener loginListener = new LoginActionListener();
		
		//ID&Password텍스트박스와 버튼에 액션리스너 추가
		txtId.addActionListener(loginListener);
		btnLogin.addActionListener(loginListener);
		txtPassword.addActionListener(loginListener);
		
		this.setContentPane(bg);		
		this.setSize(500,460);
		setVisible(true);
		
    	//this.pack();	
	}

	//ID&Password텍스트박스와 버튼에 사용 될 액션리스너 생성
	private class LoginActionListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{	
			// 로그인 버튼이 클릭되면
			if(e.getSource().equals(btnLogin))
			{					
				// 아이디, 비밀번호 입력 텍스트 필드에 입력된 문자열을 읽어온다.
				String uId=txtId.getText();
				String uPass=txtPassword.getText();
				
				//ID텍스트박스가 비었으면 uId = ""로 지정
				if(txtId.getText().isEmpty())
					uId = "";			
				
				//P/W텍스트박스가 비었으면 uPass = ""로 지정
				if(txtPassword.getText().isEmpty())
					uPass="";					

				
				//아이디가 입력되지 않았거나 기본 문자열 이면 메소드 종료
				if(uId.equals("")) 
				{
					JOptionPane.showMessageDialog(null, "아이디가 입력되지 않았습니다. ");
					return;
				
				// 비밀번호가 입력되지 않았거나 기본 문자열 이면 메소드 종료
				} 
				else if(uPass.equals(""))
				{
					JOptionPane.showMessageDialog(null, "비밀번호가 입력되지 않았습니다. ");
					return;
				}
				
				DbQuery select = new DbQuery();
				select.DbLogin(uId, uPass);				
				
				if(select.isLog())
				{
					new BusMainFrm();
					LoginFrm.this.dispose();			
				}
				else
				{
					JOptionPane.showMessageDialog(null,"아이디랑 비밀번호가 맞지 않습니다.");
				}
				
				
			// 아이디 입력 텍스트 필드에서 Enter 키가 입력되면
			} 
			else if(e.getSource().equals(txtId)) 
			{
				/* 아이디가 입력되지 않은 상태에서 Enter 키가 
				 * 입력되면 안내 메시지를 띄우고 메소드를 종료한다.
				 **/ 
				if(txtId.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(null,"아이디가 입력되지 않았습니다. ");
					return;
				}
				
				// 비밀번호 텍스트 필드로 포커스를 이동
				txtPassword.requestFocus();		
			}
			else if(e.getSource().equals(txtPassword))
			{				
				btnLogin.doClick();
			}
		}
	}
}

class BgPanel extends JPanel
{
	Image bg = new ImageIcon("images/sixtank002.jpg").getImage();
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
	}
}

	
	





