package bus.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bus.db.BusColumn;
import bus.db.BusTimesTableModel;
import bus.db.DbQuery;


public class testpnl extends JPanel
{
	JTable bustable;
	BusTimesTableModel busModel;
	Vector<BusColumn> busList;
	
	testpnl()
	{
		DbQuery as= new DbQuery();
		busList =as.getBusList("2014-10-16", "BS", "SL");
		busModel = new BusTimesTableModel(busList);
		bustable = new JTable(busModel);
		
		bustable.addMouseListener(new MouseAdapter(){
			/* 더블 클릭이 발생한 friendsTable의 한 행의 데이터를 
			 * 가져오기 위해 mouseClicked() 메소드를 오버라이딩 하였다.
			 **/
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// 마우스 왼쪽 버튼이 더블 클릭되면
				if(e.getButton() == 1 && e.getClickCount() == 2) {
					
					/* friendsTable에서 현재 선택된 행의 index를 얻어 온다.
					 * 테이블의 행이 선택되지 않았으면 -1이 리턴 된다.
					 **/
					int rowIndex = bustable.getSelectedRow();

					/* 친구정보 수정하기 다이얼로그 창을 띄우면서 부모 프레임의
					 * 인스턴스와 friendsTable에서 선택된 친구 데이터를
					 * TableModle로 부터 가져와 지정하고 다이얼로그 창의  
					 * ModalityType을 MODAL로 설정하고 있다.
					 * MODAL 설정은 다이얼로그 창을 닫기 전까지 부모 창으로 
					 * 포커스를 옮겨 갈 수 없으며 MODELESS 설정은 부모 창과
					 * 다이얼로그 창이 서로 독립적으로 동작할 수 있게 설정한다. 
					 **/
					BusColumn friend = busModel.getRowData(rowIndex);
					
					
					JOptionPane.showMessageDialog(null,rowIndex+"-"+friend.getBUSTIMEID());
					/*FriendModifyFrame sub = new FriendModifyFrame(
							FriendsManagerMain.this, friend, rowIndex, true);*/
				}
			}
		});
		
		/*
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(
				new String[] { " ", " ", " "," ","출발시간", " ", " ", "boolean" } );
		
		model.addRow( new String[] {  " "," "," "," ", "08:00", " ", " ", "false" }  );
		bustable.setModel( model );
		*/
		
		
		bustable.revalidate();
		bustable.repaint();
				
		JScrollPane tableScroll = new JScrollPane(bustable,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(tableScroll);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
