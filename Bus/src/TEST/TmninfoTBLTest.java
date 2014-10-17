package TEST;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bus.db.BusDbConnect;

public class TmninfoTBLTest extends JFrame {
		
	
	JComboBox combo;
	DefaultComboBoxModel model;
	JLabel lblSelect;
	static Connection conn;
	static PreparedStatement pstmt;
	static ResultSet rs;
	static Vector<Tmn> tmnIdList;
	
	public TmninfoTBLTest() {
		
		setTitle("디비에서 가져오기 0테스트");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		System.out.println(" aaa : " +getTmnId());
		model = new DefaultComboBoxModel();
		combo = new JComboBox(model);
		combo.setPreferredSize(new Dimension(100, 26));
		
		
		combo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JComboBox com = (JComboBox) e.getSource();				
				String item = (String) com.getSelectedItem();			
				
				System.out.println(tmnIdList.get(com.getSelectedIndex()));
				
				lblSelect.setText(item + "가 선택 되었습니다.");
							
			}
		});
		
		JPanel centerPanel = new JPanel();
		centerPanel.add(combo);
		add(centerPanel);
		
		lblSelect = new JLabel();
		lblSelect.setPreferredSize(new Dimension(300, 30));
		lblSelect.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel southPanel = new JPanel();
		southPanel.add(lblSelect);
		southPanel.setPreferredSize(new Dimension(300, 100));
		add(southPanel, BorderLayout.SOUTH);
		
		setSize(300, 300);
		setVisible(true);
	}
	
	
	
	
	public static Vector<Tmn> getTmnId() 
	{		
		String selectTmnId = "SELECT tmnname, tmnid FROM TMNINFOTBL";
		
		tmnIdList = new Vector<Tmn>();
		try {
			conn = BusDbConnect.getConnect();
			pstmt = conn.prepareStatement(selectTmnId);
			
			rs = pstmt.executeQuery();
			
					
			
			while(rs.next()) 
			{
				Tmn tmn = new Tmn();
				tmn.setTmnid(rs.getString(1));
				tmn.setTmnname(rs.getString(2));
				System.out.println(rs.getString(1));
				tmnIdList.add(tmn);
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if(rs != null) rs.close();
				if(pstmt != null) rs.close();
				if(conn != null) conn.close();
				
			} catch(SQLException e) {}
		}
		
		return tmnIdList;	
	}	
		
	public static void main(String[] args) {
		
		new TmninfoTBLTest();
		
	}

}

class Tmn
{
	static String tmnid;
	static String tmnname;
	
	public static String getTmnid() {
		return tmnid;
	}
	public static void setTmnid(String tmnid) {
		tmnid = tmnid;
	}
	public static String getTmnname() {
		return tmnname;
	}
	public static void setTmnname(String tmnname) {
		tmnname = tmnname;
	}	
}