package bus.db;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;



public class ProgressTableModel extends AbstractTableModel {

	/* 기본컬럼명을 저장하는 배열 생성 */
	private final static String[] TABLE_COLUMNS = { "Boolean","예약", "회원", "배차", "예약상태",
			"결제방법", "여행자보험","좌석","왕복","예약날짜","취소날짜","결제가격","등급","장애인","비고" };

	/* progress 테이블에 데이터를 저장하는 Vector선언 */
	private Vector<Progress> progressList;

	/* progress 테이블의 컬럼명을 저장하는 Vector선언 */
	private Vector<String> columnNames;

	public ProgressTableModel() {
		// 기본생성자는 외부에서 데이터를 넘겨받지 못하므로 진행사항 정보를 저장할수 있는 Vector<Progress>타입의 객체를
		// 직접생성(progress)하고
		// 컬럼명은 상수로 정의된 배열을 지정하여 인스턴스 변수에 저장하는 다른 생성자를 호출하고 있다.
		this(new Vector<Progress>(), TABLE_COLUMNS);
	}

	public ProgressTableModel(Vector<Progress> progressList) {
		this(progressList, TABLE_COLUMNS);
	}

	public ProgressTableModel(Vector<Progress> progressList, String[] columnNames) {
		setProgressTableModel(progressList, getColumnArrayToVector(columnNames));
	}

	public ProgressTableModel(Vector<Progress> progressList,
			Vector<String> columnNames) {
		setProgressTableModel(progressList, columnNames);
	}

	/*
	 * Vector<Progress>타입의 테이블 데이터와 Vector<String>타입의 컬럼명을 매개 변수로 받아 인스턴스 변수에
	 * 저장하는 메소드
	 */
	private void setProgressTableModel(Vector<Progress> progressList,
			Vector<String> columNames) {
		this.progressList = progressList;
		this.columnNames = columNames;
		fireTableStructureChanged();
	}

	private Vector<String> getColumnArrayToVector(String[] columnNames) {
		Vector<String> tableHeaders = new Vector<String>();
		for (int i = 0; i < columnNames.length; i++) {
			tableHeaders.add(TABLE_COLUMNS[i]);
		}
		return tableHeaders;
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return progressList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	
	Progress progress = progressList.get(rowIndex);	
		switch(columnIndex){
		case 0:
			return false ;
		case 1:
			return progress.getRSVID();
		case 2:
		return progress.getUSERID();
		case 3:
			return progress.getBUSTIMEID();
		case 4:
			return progress.getRSVSEAT();
		case 5:
			return progress.getPAYMENTID();
		case 6:
			return progress.getISRID();
		case 7:
			return progress.getRSVTRIP();
		case 8:
			return progress.getRSVDATE();
		case 9:
			return progress.getCANCELDATE();
		case 10:
			return progress.getPAYMONEY();
		case 11:
			return progress.getUSERGRADE();
		case 12:
			return progress.getHANDYCAP();
		
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int columnIndex) {

		return columnNames.get(columnIndex);
	}
	
	/* 체크박스를 만들기 위해 소스수정 */
	 Class[] dataType = new Class[] { Boolean.class, String.class,
	 String.class, String.class, String.class, String.class,
	 String.class, String.class, String.class, String.class,
	 String.class, String.class, String.class, String.class,
	 String.class };
	
	 @Override
	 public Class<?> getColumnClass(int columnIndex) {
	 return dataType[columnIndex];
	
	 }


}
