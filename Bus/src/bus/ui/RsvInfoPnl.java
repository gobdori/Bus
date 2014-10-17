package bus.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bus.db.DbQuery;
import bus.db.RsvTableModel;
import bus.db.RsvColumn;

public class RsvInfoPnl extends JPanel {

	Vector<RsvColumn> progressList;
	DbQuery dao;
	JTable rsvTable;
	RsvTableModel progressModel;
	JButton btnSign;
	String userid = DbQuery.getUserid();

	RsvInfoPnl() {

		/* 메인프레임 north */
		JLabel titleLabel = new JLabel("버스리스트");
		titleLabel.setFont(new Font("굴림체", Font.BOLD, 15));

		JPanel northJPanel = new JPanel();
		northJPanel.setPreferredSize(new Dimension(550, 30));
		// northJPanel.setBackground(Color.darkGray);
		northJPanel.add(titleLabel);
		add(northJPanel, BorderLayout.NORTH);

		/* 메인프레임 center */
		// ProgressMangerDao 객체를 생성하고 progress테이블에 출력할 데이터를 db로 부터 읽어온다.

		dao = new DbQuery();
		progressList = dao.getRsvList(userid);

		/*
		 * progress 리스트를 출력할 JTable 객체 생성 AbstractTableModel을 상속받아 추상메소드를 구현한
		 * ProgressTableModel의 생성자 인수로 db에서 읽어온 progress 리스트를 지정하여
		 * ProgressTableModel 객체를 생성하고 progressTable의 생성자의인수로 지정하여
		 * progressTable의 객체를 생성한다.
		 */
		progressModel = new RsvTableModel(progressList);
		rsvTable = new JTable(progressModel);

		/*
		 * progress 테이블이 생성되면서 컬럼크기를 자동으로 조절하지 못하게 설정하고 테이블의 각 컬럼의 너비를 사용자가 조정할수
		 * 없도록 설정한다.
		 */
		//progressTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//progressTable.getTableHeader().setResizingAllowed(false);

		/*
		 * 사용자가 테이블의 Header위치를 바꿀수 없도록 설정하고 선택되는 행의 배경색과 전경색을 설정한다.
		 
		progressTable.getTableHeader().setReorderingAllowed(false);
		progressTable.setSelectionBackground(new Color(255, 125, 125, 70));
		progressTable.setSelectionForeground(new Color(0, 0, 255));
		 */
		/* progress테이블의 ColumnModel을 이용해 각 컬럼의 너비를 지정한다. 
		progressTable.getColumnModel().getColumn(0).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(1).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(2).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(3).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(4).setPreferredWidth(55);
		progressTable.getColumnModel().getColumn(5).setPreferredWidth(55);
		progressTable.getColumnModel().getColumn(6).setPreferredWidth(55);
		progressTable.getColumnModel().getColumn(7).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(8).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(9).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(10).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(11).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(12).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(13).setPreferredWidth(35);
		progressTable.getColumnModel().getColumn(14).setPreferredWidth(55);
		 */
		/*
		 * 테이블의 세로 스크롤 지원을 위해 JScrollPane으로 감싼다. JScrollPane으로 감싸지 않으면 테이블 헤더가
		 * 보이지 않는다.
		 */
		JScrollPane tableScroll = new JScrollPane(rsvTable,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		/* 테이블의 크기를 지정하고 있다. */
		rsvTable
				.setPreferredScrollableViewportSize(new Dimension(575, 210));
		

		/* 테이블의 header와 행의 높이를 설정한다. 
		progressTable.getTableHeader().setPreferredSize(new Dimension(0, 30));
		progressTable.setRowHeight(26);
		 */
		
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(580, 250));
		centerPanel.setBackground(Color.BLUE);
		centerPanel.add(tableScroll);
		add(centerPanel);

		/* 메인프레임 South 버튼 Sign */
		JPanel southPanel = new JPanel();
		southPanel.setPreferredSize(new Dimension(585, 50));
		southPanel.setBackground(Color.yellow);
		btnSign = new JButton("결제하기");
		southPanel.add(btnSign);
		add(southPanel, BorderLayout.SOUTH);
		}	
}
